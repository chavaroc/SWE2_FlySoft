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
     * Contains all valid time dimensions (input value).
     */
    protected static List<String> validTimeDimensions;

    protected int nextFreeParaIndex = GlobalSettings.FIRST_DYN_PARA_INDEX;

    /**
     * Construct a new abstract query builder.
     */
    public AbstractQueryBuilder() {
        if(validTimeDimensions == null){
            validTimeDimensions = new ArrayList<>();
            validTimeDimensions.add(GlobalSettings.TIME_DIM_DAY.toLowerCase());
            validTimeDimensions.add(GlobalSettings.TIME_DIM_WEEKDAYS.toLowerCase());
            validTimeDimensions.add(GlobalSettings.TIME_DIM_WEEK.toLowerCase());
            validTimeDimensions.add(GlobalSettings.TIME_DIM_MONTH.toLowerCase());
            validTimeDimensions.add(GlobalSettings.TIME_DIM_YEAR.toLowerCase());
        }
    }

    /**
     * Create the where query token for the third dimension.
     * @param settings The filter settings that are currently used.
     * @param selector 
     * @return A part of a sql query token, that contains "WHERE <3rd dim> = <x>"
     */
    protected String calcWhereThirdDimToken(final FilterSetting settings,
        DataCategorySelector selector) {
        /* set default value to 'WHERE 1=1'
         handle as no 3rd dim was selected.
         1=1 is a dummy expression, maybe the query have some
         AND conditions after the WHERE. So we need always the WHERE. */
        String thirdDimColumn = "WHERE 1=1\n";
        /* Check if we have a 3rd dimension setting and if yes,
         which setting it is.
         Attension: The parameter number must be equal with the order number
         in method 'createParamizedQuery' */
        if(settings.getThirdDimension() != null){
            switch (settings.getThirdDimension().toLowerCase()) {
                case GlobalSettings.TIME:
                    // Passenger count have an other field for time
                    if(PASSENGER_COUNT.equalsIgnoreCase(settings.getYaxis())){
                        thirdDimColumn = "WHERE MS.yearmonth BETWEEN ?1 and ?2\n";
                    }
                    else{
                        thirdDimColumn = "WHERE FE.departuretime BETWEEN ?1 and ?2\n";
                    }
                    selector.setEndpointsNeeded(true);
                    break;
                case GlobalSettings.AIRLINE:
                    thirdDimColumn = "WHERE AIR.name IN " +
                    generatePlaceholderList(settings.getAirlines().length,
                        nextFreeParaIndex) +"\n";
                    selector.setAirlineNeeded(true);
                    break;
                case GlobalSettings.DESTINATION:
                    thirdDimColumn = "WHERE DESTC.name IN " + 
                    generatePlaceholderList(settings.getDestinations().length,
                        nextFreeParaIndex) +"\n";
                    selector.setEndpointsNeeded(true);
                    selector.setDestNeeded(true);
                    break;
                default:
                    //Use default value
            }
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
        query = entityManager.createNativeQuery(fullQuery);
        query.setParameter(1, settings.getTimeFrom(), TemporalType.DATE);
        query.setParameter(2, settings.getTimeTo(), TemporalType.DATE);
        int currentParaNumber = GlobalSettings.FIRST_DYN_PARA_INDEX;
        /* The order of the parameter types must match with the parameter numbers.
         * Otherwise we mix airlines and destinations.
         * We know, that the WHERE clausel of the third dimension will
         * always stand before the x-axis clausel within the query.
         * So add airlines first, if they are set for the first dimension.
         * Otherwise add them after the destinations.
         * Not a so fine solution, but it works for know.
         */
        if(GlobalSettings.AIRLINE.equalsIgnoreCase(settings.getThirdDimension())){
            currentParaNumber = setDynamicQueryParameter(query, settings.getAirlines(), currentParaNumber);
        }
        if(GlobalSettings.DESTINATION.equalsIgnoreCase(settings.getThirdDimension())
            || GlobalSettings.DESTINATION.equalsIgnoreCase(settings.getXaxis())){
            currentParaNumber = setDynamicQueryParameter(query, settings.getDestinations(), currentParaNumber);
        }
        if(GlobalSettings.AIRLINE.equalsIgnoreCase(settings.getXaxis())){
            setDynamicQueryParameter(query, settings.getAirlines(), currentParaNumber);
        }
        return query;
    }
    
    /**
     * Add a list of string parameters to the query.
     * @param query The query, that get the parameter from the list.
     * @param valueList The values that should be added as parameter to the query.
     * @param currentParaNumber The actual parameter number.
     * @return The new current parameter number.
     */
    private int setDynamicQueryParameter(Query query, String[] valueList, int currentParaNumber){
        for(int listIndex = 0; listIndex < valueList.length; listIndex++){
            query.setParameter(currentParaNumber, valueList[listIndex]);
            currentParaNumber++;
        }
        return currentParaNumber;
    }
    
    /**
     * Parse the time dimension and deliver a valid time dim back.
     * The time dimension is equal with the sql functions (week, month, ...).
     * @param settings The current filter settings.
     * @return A valid time dimension.
     */
    protected String parseTimeDimension(final FilterSetting settings){
        String timeDim = settings.getTimeDimension();
        // This if condition is a security condition (avoid sql injection).
        // Use only valid values.
        if(!validTimeDimensions.contains(timeDim.toLowerCase(Locale.US))){
            throw new IllegalArgumentException(
                    "Unknown time dimension '"+timeDim +"'");
        }
        if(GlobalSettings.TIME_DIM_DAY.equalsIgnoreCase(timeDim)){
            // If we got the time dimension 'day' we need no sql function.
            // The sql function 'day' add all month days together 
            // for example: group 22 = (22.01 + 22.02 + ...).
            // There will be ~ 30 groups.
            timeDim = GlobalSettings.SQL_FUNC_DAY_OF_YEAR; 
        }
        if(GlobalSettings.TIME_DIM_WEEKDAYS.equalsIgnoreCase(timeDim)) {
            timeDim = GlobalSettings.SQL_FUNC_DAY_NAME; 
        }
        return timeDim;
    }
    
    /**
     * Build a string in following format:
     * ((?7), (?8), (?9))
     * Numbers depends on offset and list size.
     * @param size The count of placeholders.
     * @param offset The first placeholder index.
     * @return A parameter string.
     */
    protected String generatePlaceholderList(int size, int offset){
        StringBuilder builder = new StringBuilder();
        int max = size+offset;
        builder.append("(");
        for(int index = offset; index < max; index++)
        {
            builder.append("(?" + index + ")");
            nextFreeParaIndex++;
            if(index != max-1){
                 builder.append(",");
            }
        }
        builder.append(")");
        return builder.toString();
    }
    
    /**
     * 
     * @param isOnTime
     * @param selector 
     * @return 
     */
    protected String buildBaseQuery(boolean isOnTime, DataCategorySelector selector){
        StringBuilder queryStrBuilder = new StringBuilder();
        final String aliasMainTable;
        queryStrBuilder.append("SELECT \n" +
                               "%s\n");
        if(isOnTime){
            aliasMainTable = "F";
            queryStrBuilder.append("FROM flight "+aliasMainTable+"\n");
        }
        else{ //Market base query
            aliasMainTable = "MS";
            queryStrBuilder.append("FROM monthlystat "+aliasMainTable+"\n");
        }
        if(selector.isEndpointsNeeded()){
            queryStrBuilder.append("LEFT JOIN flightendpoint FE ON FE.flightendpoint_id = F.flightendpoint_id\n");
        }
        if(selector.isAirlineNeeded()){
            queryStrBuilder.append("RIGHT JOIN airline AIR ON AIR.airline_id = "+aliasMainTable+".airline_id\n");
        }
        if(selector.isDestNeeded()){
            final String equalColumn = ((isOnTime)? "FE.destairportshortname" : "MS.destairportsn") + "\n";
            queryStrBuilder.append("LEFT JOIN airport DEST ON DEST.shortname = ");
            queryStrBuilder.append(equalColumn);
            queryStrBuilder.append("LEFT JOIN city DESTC ON DESTC.city_id = DEST.city_id\n");
        }
        queryStrBuilder.append("%s");
        return queryStrBuilder.toString();
    }
}
