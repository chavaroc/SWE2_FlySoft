package hm.edu.swe2.flysoft.entity.querybuilder;

import hm.edu.swe2.flysoft.interfaces.IQueryBuilder;
import hm.edu.swe2.flysoft.ui.FilterSetting;
import static hm.edu.swe2.flysoft.util.GlobalSettings.*;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Build all querys, that need flight frequency at the y-axis.
 * @author Philipp Chavaroche
 * @version 17.05.2016
 */
public class FrequencyQueryBuilder extends AbstractQueryBuilder implements IQueryBuilder{
    
    /**
     * Build a sql query, to request the data descript by the given settings.
     * @param settings The current filter settings.
     * @param em 
     * @return The query to request the data, descriped by the filter settings.
     */
    public Query build(final FilterSetting settings, final EntityManager em){
        Query query;
        String selectToken;
        String whereToken;
        // Check which x-axis is given
        if(TIME.equalsIgnoreCase(settings.getXaxis())){
            final String timeDim = settings.getTimeDimension();
            // This if condition is a security condition (avoid sql injection).
            // Use only valid values.
            if(validTimeDimensions.contains(timeDim.toLowerCase(Locale.US))){
                selectToken = timeDim + "(FE.departuretime) as Week\n" +
                ",Count("+timeDim+"(FE.departuretime)) as Flights";
                whereToken = calcWhereThirdDimToken(settings) + 
                    "AND FE.departuretime BETWEEN ?1 and ?2\n" +
                    "GROUP BY "+timeDim+"(FE.departuretime)";
                query = createParamizedQuery(selectToken, whereToken, settings, em);
            }
            else{
                //Invalid time dimension
                throw new IllegalArgumentException("Unknown time dimension '"+timeDim+"'");
            }
        }
        else if (AIRLINE.equalsIgnoreCase(settings.getXaxis())){
            selectToken = "AIR.name\n" +
                ",Count(AIR.name)";
            whereToken = calcWhereThirdDimToken(settings) + 
                "GROUP BY AIR.name";
            query = createParamizedQuery(selectToken, whereToken, settings, em);
        }
        else if (DESTINATION.equalsIgnoreCase(settings.getXaxis())){
            selectToken = "DESTC.name\n" +
                ",Count(DESTC.name)";
            whereToken = calcWhereThirdDimToken(settings) + 
                "GROUP BY DESTC.name";
            query = createParamizedQuery(selectToken, whereToken, settings, em);
        }
        else if (ORIGIN.equalsIgnoreCase(settings.getXaxis())){
            selectToken = "ORIGC.name\n" +
                ",Count(ORIGC.name)";
            whereToken = calcWhereThirdDimToken(settings) + 
                "GROUP BY ORIGC.name";
            query = createParamizedQuery(selectToken, whereToken, settings, em);
        }
        else{
            throw new UnsupportedOperationException("Not supported yet.");
        }   
        return query;
    }    
}
