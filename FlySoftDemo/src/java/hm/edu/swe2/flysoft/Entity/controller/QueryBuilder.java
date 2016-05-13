package hm.edu.swe2.flysoft.entity.controller;

import hm.edu.swe2.flysoft.ui.FilterSetting;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 * The job of the QueryBuilder class is to convert given filter settings
 * into a sql query, that requests the matching data.
 * @author Philipp Chavaroche
 * @version 
 */
public class QueryBuilder extends AbstractEntityController{
    
    /**
     * The base query, to request all data.
     * This query needs two parameter:
     *  - selected columns
     *  - where clausal
     */
    private final String baseQuery = 
        "SELECT \n" +
        "%s\n" +
        "FROM\n" +
        "        flight F\n" +
        "        JOIN flightendpoint FE ON FE.flightendpoint_id = F.flightendpoint_id\n" +
        "        JOIN airline AIR ON AIR.airline_id = F.airline_id\n" +
        "        JOIN airport ORIG ON ORIG.shortname = FE.originairportshortname\n" +
        "        JOIN airport DEST ON DEST.shortname = FE.destairportshortname\n" +
        "        JOIN city ORIGC ON ORIGC.city_id = ORIG.city_id\n" +
        "        JOIN city DESTC ON DESTC.city_id = DEST.city_id\n" + 
        "%s";
    
    /**
     * Build a sql query, to request the data descript by the given settings.
     * @param settings The current filter settings.
     * @return The query to request the data, descriped by the filter settings.
     */
    private Query buildQuery(final FilterSetting settings){
        final EntityManager em = getEntityManager();
        Query query = null;
        // TODO evaluate tokens via settings
        if("Frequencies".equals(settings.getYaxis()) && "Time".equals(settings.getXaxis())){
            String timeDim = settings.getTimeDimension();
            if("day".equalsIgnoreCase(timeDim) 
                || "week".equalsIgnoreCase(timeDim)
                || "month".equalsIgnoreCase(timeDim)
                || "year".equalsIgnoreCase(timeDim))
            {
                String destinations = String.join(",", settings.getDestinations());
                String selectToken = timeDim + "(FE.departuretime) as Week\n" +
                ",Count("+timeDim+"(FE.departuretime)) as Flights";
                String whereToken = 
                    "WHERE DESTC.name IN (?1)\n" +
                    "AND FE.departuretime BETWEEN ?2 and ?3\n" +
                    "GROUP BY "+timeDim+"(FE.departuretime)";
                String fullQuery = String.format(baseQuery, selectToken, whereToken);
                query = em.createNativeQuery(fullQuery);
                query.setParameter(1, destinations);
                query.setParameter(2, settings.getTimeFrom(), TemporalType.DATE);
                query.setParameter(3, settings.getTimeTo(), TemporalType.DATE);
            }
            else{
                //Invalid time dimension
                throw new IllegalArgumentException("Unknown time dimension '"+timeDim+"'");
            }
        }
        else{
            throw new UnsupportedOperationException("Not supported yet.");
        }   
        return query;
    }
    
    /**
     * Request data for the given filter settings.
     * @param filter The filter, that defines the data scope.
     * @return The data for the given filter.
     */
    public List<Object[]> processDataRequest(final FilterSetting filter){
        
        final Query query = buildQuery(filter);
        return query.getResultList();
    }
}
