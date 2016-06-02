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
    
    /**
     * The path to the file, that contains all airlines
     * (lookup table from trans stats).
     */
    public static final String AIRLINE_FILE_NAME = "resource/L_AIRLINE_ID.csv";
    
    public static final String CRAWLER_CONFIG_FILE_NAME = "crawler_config.txt";
    public static final String CRAWLER_DOWNLOAD_DIR = "downloaded" + File.separatorChar;
    
    // obsolete (fall back solution?)
    public static final String DB_PROD_FLIGHT_COUNT_WEEK = "fly_analytics.FlightCountPerWeek";
    
    
    // ================ Query Builder constants ================
    /**
     * The base query, to request all flights (based on the on time table).
     * This query needs two parameter:
     *  - selected columns
     *  - where clausal
     */
    public static final String BASE_QUERY_ON_TIME = 
        "SELECT \n" +
        "%s\n" +
        "FROM flight F\n" +
        "JOIN flightendpoint FE ON FE.flightendpoint_id = F.flightendpoint_id\n" +
        "JOIN airline AIR ON AIR.airline_id = F.airline_id\n" +
        "JOIN airport ORIG ON ORIG.shortname = FE.originairportshortname\n" +
        "JOIN airport DEST ON DEST.shortname = FE.destairportshortname\n" +
        "JOIN city ORIGC ON ORIGC.city_id = ORIG.city_id\n" +
        "JOIN city DESTC ON DESTC.city_id = DEST.city_id\n" + 
        "%s";
    public static final String BASE_QUERY_MONTHLY_STAT =
        "SELECT \n" + 
        "%s\n" +
        "FROM monthlystat MS\n" +
        "JOIN airline AIR ON AIR.airline_id = MS.airline_id\n" +
        "JOIN airport ORIG ON ORIG.shortname = MS.orginairportsn\n" +
        "JOIN airport DEST ON DEST.shortname = MS.destairportsn\n" +
        "JOIN city ORIGC ON ORIGC.city_id = ORIG.city_id\n" +
        "JOIN city DESTC ON DESTC.city_id = DEST.city_id\n" +
        "%s";
    public static final String FREQUENCIES = "frequencies";
    public static final String DELAY_DURATION = "delay durations";
    public static final String DELAY_FREQ = "delay frequencies";
    public static final String CANCELLATIONS = "cancellations";
    public static final String PASSENGER_COUNT = "Count of passengers";
    public static final String TIME = "time";
    public static final String DESTINATION = "destination";
    public static final String ORIGIN = "origin";
    public static final String AIRLINE = "airline";
    
    


}
