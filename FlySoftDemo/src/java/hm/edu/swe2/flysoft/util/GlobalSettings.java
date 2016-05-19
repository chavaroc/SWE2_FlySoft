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

}
