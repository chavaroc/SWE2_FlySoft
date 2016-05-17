package hm.edu.swe2.flysoft.entity.querybuilder;

import hm.edu.swe2.flysoft.ui.FilterSetting;
import hm.edu.swe2.flysoft.util.GlobalSettings;
import static hm.edu.swe2.flysoft.util.GlobalSettings.*;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 * Build all querys, that need flight frequency at the y-axis.
 * @author Philipp Chavaroche
 * @version 17.05.2016
 */
public class FrequencyQueryBuilder{
    
    /**
     * Build a sql query, to request the data descript by the given settings.
     * @param settings The current filter settings.
     * @param em 
     * @return The query to request the data, descriped by the filter settings.
     */
    public Query build(final FilterSetting settings, final EntityManager em){
        Query query = null;
        boolean isThirdDimGiven;
        // Check which x-axis is given
        if(TIME.equalsIgnoreCase(settings.getXaxis())){
            String thirdDimValueList;
            String thirdDimColumn;
            String thridDimQueryToken;
            // Check if we have a 3rd dimension setting and if yes,
            // which setting it is.
            if(DESTINATION.equalsIgnoreCase(settings.getThirdDimension())){
                thirdDimColumn = "DESTC.name";
                thirdDimValueList = String.join(",", settings.getDestinations());
                isThirdDimGiven = true;
            }
            else if(ORIGIN.equalsIgnoreCase(settings.getThirdDimension())){
                thirdDimColumn = "ORIGC.name";
                thirdDimValueList = String.join(",", settings.getOrigins());
                isThirdDimGiven = true;
            }
            else if(ARLINE.equalsIgnoreCase(settings.getThirdDimension())){
                thirdDimColumn = "AIR.name";
                thirdDimValueList = String.join(",", settings.getAirlines());
                isThirdDimGiven = true;
            }
            else{
                // handle as no 3rd dim was selected.
                thirdDimColumn = "";
                thirdDimValueList = "";
                isThirdDimGiven = false;
            }
            thridDimQueryToken = (isThirdDimGiven) ?
                 "AND "+thirdDimColumn+" IN (?3)\n"
                : "";
            
            String timeDim = settings.getTimeDimension();
            if("day".equalsIgnoreCase(timeDim) 
                || "week".equalsIgnoreCase(timeDim)
                || "month".equalsIgnoreCase(timeDim)
                || "year".equalsIgnoreCase(timeDim))
            {
                String selectToken = timeDim + "(FE.departuretime) as Week\n" +
                ",Count("+timeDim+"(FE.departuretime)) as Flights";
                String whereToken = 
                    "WHERE FE.departuretime BETWEEN ?1 and ?2\n" +
                    thridDimQueryToken + 
                    "GROUP BY "+timeDim+"(FE.departuretime)";
                final String fullQuery = String.format(
                    GlobalSettings.BASE_QUERY, selectToken, whereToken);
                query = em.createNativeQuery(fullQuery);
                query.setParameter(1, settings.getTimeFrom(), TemporalType.DATE);
                query.setParameter(2, settings.getTimeTo(), TemporalType.DATE);
                if(isThirdDimGiven) {
                    query.setParameter(3, thirdDimValueList);
                }
            }
            else{
                //Invalid time dimension
                throw new IllegalArgumentException("Unknown time dimension '"+timeDim+"'");
            }
        }
        else if (ARLINE.equalsIgnoreCase(settings.getXaxis())){
            
        }
        else{
            throw new UnsupportedOperationException("Not supported yet.");
        }   
        return query;
    }    

}
