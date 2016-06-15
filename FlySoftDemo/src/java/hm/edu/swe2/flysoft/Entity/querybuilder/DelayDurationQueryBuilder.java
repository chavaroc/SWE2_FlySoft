package hm.edu.swe2.flysoft.entity.querybuilder;

import hm.edu.swe2.flysoft.interfaces.IQueryBuilder;
import hm.edu.swe2.flysoft.ui.FilterSetting;
import hm.edu.swe2.flysoft.util.GlobalSettings;
import static hm.edu.swe2.flysoft.util.GlobalSettings.*;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Build all querys, that need delay duration at the y-axis.
 * @author Philipp Chavaroche
 * @version 18.05.2016
 */
public class DelayDurationQueryBuilder extends AbstractQueryBuilder implements IQueryBuilder{

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
            whereToken = calcWhereThirdDimToken(settings) + 
                "AND AIR.name IN " + 
                generatePlaceholderList(settings.getAirlines().length,
                    nextFreeParaIndex) +"\n";
            groupByToken = "GROUP BY AIR.name";
        }
        else if (DESTINATION.equalsIgnoreCase(settings.getXaxis())){
            selectToken = "DESTC.name\n";
            whereToken = calcWhereThirdDimToken(settings) + 
                "AND DESTC.name IN " + 
                generatePlaceholderList(settings.getDestinations().length,
                    nextFreeParaIndex) +"\n";
            groupByToken = "GROUP BY DESTC.name";
        }
        else{
            throw new UnsupportedOperationException("Not supported yet.");
        }   
        selectToken += ",SUM(FE.arrivaldelay) as SumDelay";
        whereToken = whereToken + groupByToken;
        query = createParamizedQuery(GlobalSettings.BASE_QUERY_ON_TIME,
            selectToken, whereToken, settings, entityManager);
        return query;
    }

}
