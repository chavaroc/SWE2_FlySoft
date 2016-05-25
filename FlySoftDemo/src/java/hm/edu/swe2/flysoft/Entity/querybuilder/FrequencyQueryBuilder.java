package hm.edu.swe2.flysoft.entity.querybuilder;

import hm.edu.swe2.flysoft.interfaces.IQueryBuilder;
import hm.edu.swe2.flysoft.ui.FilterSetting;
import static hm.edu.swe2.flysoft.util.GlobalSettings.*;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Build all querys, that need flight frequency at the y-axis.
 * @author Philipp Chavaroche
 * @version 17.05.2016
 */
public class FrequencyQueryBuilder extends AbstractQueryBuilder implements IQueryBuilder{

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
                "GROUP BY AIR.name";
        }
        else if (DESTINATION.equalsIgnoreCase(settings.getXaxis())){
            selectToken = "DESTC.name\n" +
                ",Count(DESTC.name)";
            whereToken = calcWhereThirdDimToken(settings) + 
                "GROUP BY DESTC.name";
        }
        else if (ORIGIN.equalsIgnoreCase(settings.getXaxis())){
            selectToken = "ORIGC.name\n" +
                ",Count(ORIGC.name)";
            whereToken = calcWhereThirdDimToken(settings) + 
                "GROUP BY ORIGC.name";
        }
        else{
            throw new UnsupportedOperationException("Not supported yet.");
        }   
        query = createParamizedQuery(selectToken, whereToken, settings, entityManager);
        return query;
    }    
}
