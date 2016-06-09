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
        final DataCategorySelector selector = new DataCategorySelector();
        // Check which x-axis is given
        if(TIME.equalsIgnoreCase(settings.getXaxis())){
            final String timeDim = parseTimeDimension(settings);
            selectToken = timeDim + "(FE.departuretime) as Week\n" +
            ",Count("+timeDim+"(FE.departuretime)) as Flights";
            whereToken = calcWhereThirdDimToken(settings, selector) + 
                "AND FE.departuretime BETWEEN ?1 and ?2\n" +
                "GROUP BY "+timeDim+"(FE.departuretime)";
            selector.setEndpointsNeeded(true);
        }
        else if (AIRLINE.equalsIgnoreCase(settings.getXaxis())){
            selectToken = "AIR.name\n" +
                ",Count(AIR.name)-1";
            whereToken = calcWhereThirdDimToken(settings, selector) + 
                "AND AIR.name IN " + 
                generatePlaceholderList(settings.getAirlines().length,
                    nextFreeParaIndex) +"\n"+
                "GROUP BY AIR.name";
            selector.setAirlineNeeded(true);
        }
        else if (DESTINATION.equalsIgnoreCase(settings.getXaxis())){
            selectToken = "DESTC.name\n" +
                ",Count(DESTC.name)";
            whereToken = calcWhereThirdDimToken(settings, selector) + 
                "AND DESTC.name IN " + 
                generatePlaceholderList(settings.getDestinations().length,
                    nextFreeParaIndex) +"\n"+
                "GROUP BY DESTC.name";
            selector.setDestNeeded(true);
        }
        else{
            throw new UnsupportedOperationException("Not supported yet.");
        }
        query = createParamizedQuery(buildBaseQuery(true, selector),
            selectToken, whereToken, settings, entityManager);
        return query;
    }    
}
