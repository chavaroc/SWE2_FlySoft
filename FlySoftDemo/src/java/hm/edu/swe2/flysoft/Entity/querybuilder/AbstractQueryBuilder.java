package hm.edu.swe2.flysoft.entity.querybuilder;

import hm.edu.swe2.flysoft.ui.FilterSetting;
import hm.edu.swe2.flysoft.util.GlobalSettings;
import static hm.edu.swe2.flysoft.util.GlobalSettings.PASSENGER_COUNT;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 * ABC class for query builders.
 * @author Philipp Chavaroche
 * @version 19.05.2016
 */
public abstract class AbstractQueryBuilder {
    /**
     * Contains all valid time dimensions.
     */
    protected static List<String> validTimeDimensions;
    private static final String TIME_DIM_DAY = "day";

    /**
     * Construct a new abstract query builder.
     */
    public AbstractQueryBuilder() {
        if(validTimeDimensions == null){
            validTimeDimensions = new ArrayList<>();
            validTimeDimensions.add(TIME_DIM_DAY);
            validTimeDimensions.add("week");
            validTimeDimensions.add("month");
            validTimeDimensions.add("year");
        }
    }

    /**
     * Create the where query token for the third dimension.
     * @param settings The filter settings that are currently used.
     * @return A part of a sql query token, that contains "WHERE <3rd dim> = <x>"
     */
    protected String calcWhereThirdDimToken(final FilterSetting settings) {
        String thirdDimColumn;
        // Check if we have a 3rd dimension setting and if yes,
        // which setting it is.
        // Attension: The parameter number must be equal with the order number
        // in method 'createParamizedQuery'
        switch (settings.getThirdDimension().toLowerCase()) {
            case GlobalSettings.TIME:
                // Passenger count have an other field for time
                if(PASSENGER_COUNT.equalsIgnoreCase(settings.getYaxis())){
                    thirdDimColumn = "WHERE MS.yearmonth BETWEEN ?1 and ?2\n";
                }
                else{
                    thirdDimColumn = "WHERE FE.departuretime BETWEEN ?1 and ?2\n";
                }
                break;
            case GlobalSettings.AIRLINE:
                thirdDimColumn = "WHERE AIR.name IN (?3)\n";
                break;
            case GlobalSettings.DESTINATION:
                thirdDimColumn = "WHERE DESTC.name IN (?4)\n";
                break;
            case GlobalSettings.ORIGIN:
                thirdDimColumn = "WHERE ORIGC.name IN (?5)\n";
                break;
            default:
                // handle as no 3rd dim was selected.
                // 1=1 is a dummy expression, maybe the query have some
                // AND conditions after the where.
                thirdDimColumn = "WHERE 1=1\n";
        }
        return thirdDimColumn;
    }

    /**
     * Create the query with its parameters itelf.
     * @param baseQuery The base query (query part without select and where),
     *                  that should be used.
     * @param selectToken The select oken of the query.
     * @param whereToken The where token of the query.
     * @param settings The current filter settings (used for values).
     * @param entityManager The entity manager, that will create the query.
     * @return A query object with all its parameters
     */
    protected Query createParamizedQuery(final String baseQuery, 
        final String selectToken, final String whereToken,
        final FilterSetting settings, final EntityManager entityManager) {
        Query query;
        final String fullQuery = String.format(baseQuery, selectToken, whereToken);
        query = entityManager.createQuery(fullQuery);
        query.setParameter(1, settings.getTimeFrom(), TemporalType.DATE);
        query.setParameter(2, settings.getTimeTo(), TemporalType.DATE);
        query.setParameter(3, settings.getAirlines());
        query.setParameter(4, settings.getDestinations());
        query.setParameter(5, settings.getOrigins());
        return query;
    }
    
    /**
     * Parse the time dimension and deliver a valid time dim back.
     * The time dimension is equal with the sql functions (week, month, ...).
     * @param timeDim The time dimension that should be parsed. 
     * @return A valid time dimension.
     */
    protected String parseTimeDimension(final String timeDim){
        String parsedTimeDim = timeDim;
        // This if condition is a security condition (avoid sql injection).
        // Use only valid values.
        if(!validTimeDimensions.contains(timeDim.toLowerCase(Locale.US))){
            throw new IllegalArgumentException(
                    "Unknown time dimension '"+timeDim +"'");
        }
        if(TIME_DIM_DAY.equalsIgnoreCase(timeDim)){
            // If we got the time dimension 'day' we need no sql function.
            // The sql function 'day' add all month days together 
            // for example: group 22 = (22.01 + 22.02 + ...).
            // There will be ~ 30 groups.
            parsedTimeDim = "DAYOFYEAR"; 
        }
        return parsedTimeDim;
    }
    
    protected String sqlCountFunction(String columnName){
        return "FUNC('COUNT'," + columnName + ")";
    }
    
    /**
     * Build the JPQL string for the gvien time dimension sql function.
     * @param timeDimFunction The name of the sql function.
     * @param columnName The column name, that should be aggregated via the funciton.
     * @return The funciton in the JPQL syntax.
     */
    protected String sqlTimeDimFunction(String timeDimFunction, String columnName){
        final String timeDimFuncName = parseTimeDimension(timeDimFunction);
        return "FUNC('" + timeDimFuncName + "', " + columnName + ")";
    }

}
