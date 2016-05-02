package hm.edu.swe2.flysoft.interfaces;

import java.util.Date;

/**
 * Defines the required method for a monthly statisic.
 * @author Philipp Chavaroche
 * @version 02.05.2016
 */
public interface IMonthlyStat {

    int getAirlineId();

    String getDestAirportShortName();

    String getOriginAirportShortName();

    int getPassengerCount();
    
    /**
     * Get the time aligment of the monthly stat.
     * The day is always set to the 1st of the month.
     * @return The date of the mothly stat, or null if not set.
     */
    Date getYearMonth();

    void setAirlineId(int airlineId);

    void setDestAirportShortName(String destination);

    void setOriginAirportShortName(String origin);

    void setPassengerCount(int passengerCount);
    
}
