package hm.edu.swe2.flysoft.util;

import java.io.File;

/**
 * Contains all gobal settings of the application.
 * @author Philipp Chavaroche
 * @version 05.05.2016
 */
public final class GlobalSettings {

    private GlobalSettings() {
    }
    
    // ================ Parser ================    
    /**
     * The path to the file, that contains all airlines
     * (lookup table from trans stats).
     */
    public static final String AIRLINE_FILE_NAME = "resource/L_AIRLINE_ID.csv";
    
    
    // ================ Crawler ================    
    public static final String CRAWLER_CONFIG_FILE_NAME = "crawler_config.txt";
    public static final String CRAWLER_DOWNLOAD_DIR = "downloaded" + File.separatorChar;
    
    
    // ================ Database procedures ================    
    // Used for perfomance unnit tests.
    public static final String DB_PROD_DELETE_FLIGHTS = "fly_analytics.deleteFlights";
    
    
    // ================ UI selection ================
    // ---- Time Dimension ----
    public static final String TIME_DIM_DAY = "Day";
    public static final String TIME_DIM_WEEKDAYS = "Weekdays";
    public static final String TIME_DIM_WEEK = "Week";
    public static final String TIME_DIM_MONTH = "Month";
    public static final String TIME_DIM_YEAR = "Year";
    
    // ================ Query Builder constants ================
    /**
     * The constants that are used in the query builder to evaluate
     * what setting is choosen.
     */
    public static final String FREQUENCIES = "frequencies";
    public static final String DELAY_DURATION = "delay durations";
    public static final String DELAY_FREQ = "delay frequencies";
    public static final String CANCELLATIONS = "cancellations";
    public static final String PASSENGER_COUNT = "Count of passengers";
    public static final String TIME = "time";
    public static final String DESTINATION = "destination";
    public static final String ORIGIN = "origin";
    public static final String AIRLINE = "airline";
    
    /**
     * Sql time function names.
     */
    public static final String SQL_FUNC_DAY_NAME = "DAYNAME";
    public static final String SQL_FUNC_DAY_OF_YEAR = "DAYOFYEAR";
    public static final String SQL_FUNC_DAY_OF_WEEK = "DAYOFWEEK";
    
    /**
     * Defines the first dynamic parameter index for the query builder.
     * 1. parameter = from (time range)
     * 2. parameter = to (time range)
     * This is needed to build a list of placeholders for the parameters.
     * See AbstractQueryBuilder.generatePlaceholderList
     */
    public static final int FIRST_DYN_PARA_INDEX = 3;
    
    // ================ Entity controller constants ================
    /**
     * The size of the buffer, that is used while the ParsedFlightController
     * write on time flights into database.
     */
    public static final int FLIGHT_INSERTION_BUFFER_SIZE = 50;
    
    


}
