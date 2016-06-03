package hm.edu.swe2.flysoft.entity.querybuilder;

import hm.edu.swe2.flysoft.interfaces.IQueryBuilder;
import hm.edu.swe2.flysoft.ui.FilterSetting;
import hm.edu.swe2.flysoft.util.GlobalSettings;
import static hm.edu.swe2.flysoft.util.GlobalSettings.*;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Build all querys, that need flight frequency at the y-axis.
 * @author Philipp Chavaroche
 * @version 17.05.2016
 */
public class FrequencyQueryBuilder extends AbstractQueryBuilder
    implements IQueryBuilder{

    @Override
    public Query build(final FilterSetting settings,
        final EntityManager entityManager){
        Query query;
        String selectToken;
        String whereToken;
        // Check which x-axis is given
        if(TIME.equalsIgnoreCase(settings.getXaxis())){
            final String timeDim = parseTimeDimension(settings);
            selectToken = timeDim + "(FE.departuretime) as Week\n" +
            ",Count("+timeDim+"(FE.departuretime)) as Flights";
            whereToken = calcWhereThirdDimToken(settings) + 
                "AND FE.departuretime BETWEEN ?1 and ?2\n" +
                "GROUP BY "+timeDim+"(FE.departuretime)";
        }
        else if (AIRLINE.equalsIgnoreCase(settings.getXaxis())){
            selectToken = "AIR.name\n" +
                ",Count(AIR.name)";
            whereToken = calcWhereThirdDimToken(settings) + 
                "AND AIR.name IN " + 
                generatePlaceholderList(settings.getAirlines().length,
                    nextFreeParaIndex) +"\n"+
                "GROUP BY AIR.name";
        }
        else if (DESTINATION.equalsIgnoreCase(settings.getXaxis())){
            selectToken = "DESTC.name\n" +
                ",Count(DESTC.name)";
            whereToken = calcWhereThirdDimToken(settings) + 
                "AND DESTC.name IN " + 
                generatePlaceholderList(settings.getDestinations().length,
                    nextFreeParaIndex) +"\n"+
                "GROUP BY DESTC.name";
        }
        else{
            throw new UnsupportedOperationException("Not supported yet.");
        }   
        query = createParamizedQuery(GlobalSettings.BASE_QUERY_ON_TIME,
            selectToken, whereToken, settings, entityManager);
        return query;
    }    
}
