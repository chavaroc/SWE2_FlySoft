package hm.edu.swe2.flysoft.entity.querybuilder;

import hm.edu.swe2.flysoft.ui.FilterSetting;
import hm.edu.swe2.flysoft.util.GlobalSettings;
import static hm.edu.swe2.flysoft.util.GlobalSettings.*;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import java.util.List;
import java.util.ArrayList;

/**
 * Build all querys, that need flight frequency at the y-axis.
 * @author Philipp Chavaroche
 * @version 17.05.2016
 */
public class FrequencyQueryBuilder{
    
    /**
     * Contains all valid time dimensions.
     */
    private static List<String> validTimeDimensions;

    /**
     * Construct a new frequceny query builder.
     */
    public FrequencyQueryBuilder() {
        if(validTimeDimensions == null){
            validTimeDimensions = new ArrayList<>();
            validTimeDimensions.add("day");
            validTimeDimensions.add("week");
            validTimeDimensions.add("month");
            validTimeDimensions.add("year");
        }
    }
    
    /**
     * Build a sql query, to request the data descript by the given settings.
     * @param settings The current filter settings.
     * @param em 
     * @return The query to request the data, descriped by the filter settings.
     */
    public Query build(final FilterSetting settings, final EntityManager em){
        Query query = null;
        // Check which x-axis is given
        if(TIME.equalsIgnoreCase(settings.getXaxis())){
            String timeDim = settings.getTimeDimension();
            // This if condition is a security condition (avoid sql injection).
            // Use only valid values.
            if(validTimeDimensions.contains(timeDim.toLowerCase())){
                String selectToken = timeDim + "(FE.departuretime) as Week\n" +
                ",Count("+timeDim+"(FE.departuretime)) as Flights";
                String whereToken = calcWhereThirdDimToken(settings) + 
                    "AND FE.departuretime BETWEEN ?1 and ?2\n" +
                    "GROUP BY "+timeDim+"(FE.departuretime)";
                final String fullQuery = String.format(
                    GlobalSettings.BASE_QUERY, selectToken, whereToken);
                query = createParamizedQuery(fullQuery, settings, em);
            }
            else{
                //Invalid time dimension
                throw new IllegalArgumentException("Unknown time dimension '"+timeDim+"'");
            }
        }
        else if (ARLINE.equalsIgnoreCase(settings.getXaxis())){
            String selectToken = "AIR.name\n" +
                ",Count(AIR.name)";
            String whereToken = calcWhereThirdDimToken(settings) + 
                "GROUP BY AIR.name";
            final String fullQuery = String.format(
                GlobalSettings.BASE_QUERY, selectToken, whereToken);
            query = createParamizedQuery(fullQuery, settings, em);
        }
        else if (DESTINATION.equalsIgnoreCase(settings.getXaxis())){
            throw new UnsupportedOperationException("Not supported yet.");
        }
        else{
            throw new UnsupportedOperationException("Not supported yet.");
        }   
        return query;
    }    
    
    /**
     * Create the where query token for the third dimension.
     * @param settings The filter settings that are currently used.
     * @return A part of a sql query token, that contains "WHERE <3rd dim> = <x>"
     */
    private String calcWhereThirdDimToken(final FilterSetting settings){
        String thirdDimColumn;
        // Check if we have a 3rd dimension setting and if yes,
        // which setting it is.
        switch(settings.getThirdDimension().toLowerCase()){
            case TIME:
                thirdDimColumn = "WHERE FE.departuretime BETWEEN ?1 and ?2\n";
                break;
            case ARLINE:
                thirdDimColumn = "WHERE AIR.name IN (?3)\n";
                break;
            case DESTINATION:
                thirdDimColumn = "WHERE DESTC.name IN (?4)\n";
                break;
            case ORIGIN:
                thirdDimColumn = "WHERE ORIGC.name IN (?5)\n";
                break;
            default:
                // handle as no 3rd dim was selected.
                thirdDimColumn = "wHERE 1=1\n";
        }
        return thirdDimColumn;
    }
    
    /**
     * Create the query with its parameters itelf.
     * @param strQuery The full query in string format.
     * @param settings The current filter settings (used for values).
     * @param em The entity manager, that will create the query.
     * @return A query object with all its parameters
     */
    private Query createParamizedQuery(final String strQuery,
        final FilterSetting settings, final EntityManager em){
        Query query = null;
        query = em.createNativeQuery(strQuery);
        query.setParameter(1, settings.getTimeFrom(), TemporalType.DATE);
        query.setParameter(2, settings.getTimeTo(), TemporalType.DATE);
        query.setParameter(3, String.join(",", settings.getAirlines()));
        query.setParameter(4, String.join(",", settings.getDestinations()));
        query.setParameter(5, String.join(",", settings.getOrigins()));
        return query;
    }

}
