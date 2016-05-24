package hm.edu.swe2.flysoft.entity.querybuilder;

import hm.edu.swe2.flysoft.interfaces.IQueryBuilder;
import hm.edu.swe2.flysoft.ui.FilterSetting;
import static hm.edu.swe2.flysoft.util.GlobalSettings.*;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Build all querys, that need delay duration at the y-axis.
 * @author Philipp Chavaroche
 * @version 18.05.2016
 */
public class DelayDurationQueryBuilder extends AbstractQueryBuilder implements IQueryBuilder{

    @Override
    public Query build(FilterSetting settings, EntityManager em) {
        Query query;
        String selectToken;
        String whereToken;
        // Check which x-axis is given
        if(TIME.equalsIgnoreCase(settings.getXaxis())){
            final String timeDim = parseTimeDimension(settings);
            selectToken = timeDim + "(FE.departuretime) as Week\n";
            whereToken = calcWhereThirdDimToken(settings) + 
                "AND FE.departuretime BETWEEN ?1 and ?2\n" +
                "GROUP BY "+timeDim+"(FE.departuretime)";
        }
        else if (AIRLINE.equalsIgnoreCase(settings.getXaxis())){
            selectToken = "AIR.name\n";
            whereToken = calcWhereThirdDimToken(settings) + 
                "GROUP BY AIR.name";
        }
        else if (DESTINATION.equalsIgnoreCase(settings.getXaxis())){
            selectToken = "DESTC.name\n";
            whereToken = calcWhereThirdDimToken(settings) + 
                "GROUP BY DESTC.name";
        }
        else if (ORIGIN.equalsIgnoreCase(settings.getXaxis())){
            selectToken = "ORIGC.name\n";
            whereToken = calcWhereThirdDimToken(settings) + 
                "GROUP BY ORIGC.name";
        }
        else{
            throw new UnsupportedOperationException("Not supported yet.");
        }   
        selectToken += ",SUM(FE.arrivaldelay) as SumDelay";
        query = createParamizedQuery(selectToken, whereToken, settings, em);
        return query;
    }

}
