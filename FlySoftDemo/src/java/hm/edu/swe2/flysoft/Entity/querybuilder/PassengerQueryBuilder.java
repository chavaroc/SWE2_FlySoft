package hm.edu.swe2.flysoft.entity.querybuilder;

import hm.edu.swe2.flysoft.interfaces.IQueryBuilder;
import hm.edu.swe2.flysoft.ui.FilterSetting;
import static hm.edu.swe2.flysoft.util.GlobalSettings.*;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Build all querys, that need passenger at the y-axis.
 * @author Philipp Chavaroche
 * @version 17.05.2016
 */
public class PassengerQueryBuilder extends AbstractQueryBuilder
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
            selectToken = timeDim + "(MS.yearmonth)\n" +
            ",SUM(MS.passengercount) as PassengerCount";
            whereToken = calcWhereThirdDimToken(settings, selector) + 
                "AND MS.yearmonth BETWEEN ?1 and ?2\n" +
                "GROUP BY "+timeDim+"(MS.yearmonth)";
        }
        else if (AIRLINE.equalsIgnoreCase(settings.getXaxis())){
            selectToken = "AIR.name\n" +
                ",SUM(MS.passengercount) as PassengerCount";
            whereToken = calcWhereThirdDimToken(settings, selector) + 
                "AND AIR.name IN " + 
                generatePlaceholderList(settings.getAirlines().length,
                    nextFreeParaIndex) +"\n"+
                "GROUP BY AIR.name";
            selector.setAirlineNeeded(true);
        }
        else if (DESTINATION.equalsIgnoreCase(settings.getXaxis())){
            selectToken = "DESTC.name\n" +
                ",SUM(MS.passengercount) as PassengerCount";
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
        selector.setEndpointsNeeded(false);
        query = createParamizedQuery(buildBaseQuery(false, selector),
            selectToken, whereToken, settings, entityManager);
        return query;
    }    
}
