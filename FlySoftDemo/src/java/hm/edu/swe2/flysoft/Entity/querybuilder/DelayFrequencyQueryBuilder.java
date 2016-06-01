package hm.edu.swe2.flysoft.entity.querybuilder;

import hm.edu.swe2.flysoft.interfaces.IQueryBuilder;
import hm.edu.swe2.flysoft.ui.FilterSetting;
import hm.edu.swe2.flysoft.util.GlobalSettings;
import static hm.edu.swe2.flysoft.util.GlobalSettings.*;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Build all querys, that need delay frequency at the y-axis.
 * @author Philipp Chavaroche
 * @version 19.05.2016
 */
public class DelayFrequencyQueryBuilder extends AbstractQueryBuilder
        implements IQueryBuilder{

    @Override
    public Query build(final FilterSetting settings,
        final EntityManager entityManager) {
        Query query;
        String selectToken;
        String whereToken;
        String groupByToken;
        // Check which x-axis is given
        if(TIME.equalsIgnoreCase(settings.getXaxis())){
            final String timeDim = parseTimeDimension(settings);
            selectToken = timeDim + "(FE.departuretime) as Week\n";
            whereToken = calcWhereThirdDimToken(settings) + 
                "AND FE.departuretime BETWEEN ?1 and ?2\n";
            groupByToken = "GROUP BY "+timeDim+"(FE.departuretime)";
        }
        else if (AIRLINE.equalsIgnoreCase(settings.getXaxis())){
            selectToken = "AIR.name\n";
            whereToken = calcWhereThirdDimToken(settings);
            groupByToken = "GROUP BY AIR.name";
        }
        else if (DESTINATION.equalsIgnoreCase(settings.getXaxis())){
            selectToken = "DESTC.name\n";
            whereToken = calcWhereThirdDimToken(settings);
            groupByToken = "GROUP BY DESTC.name";
        }
        else if (ORIGIN.equalsIgnoreCase(settings.getXaxis())){
            selectToken = "ORIGC.name\n";
            whereToken = calcWhereThirdDimToken(settings);
            groupByToken = "GROUP BY ORIGC.name";
        }
        else{
            throw new UnsupportedOperationException("Not supported yet.");
        }   
        selectToken += ",COUNT(FE.arrivaldelay) as DelayCount";
        whereToken = whereToken + "AND FE.arrivaldelay > 0\n" + groupByToken;
        query = createParamizedQuery(GlobalSettings.BASE_QUERY_ON_TIME,
            selectToken, whereToken, settings, entityManager);
        return query;
    }
    
}
