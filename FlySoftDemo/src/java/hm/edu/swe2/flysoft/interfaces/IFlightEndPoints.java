package hm.edu.swe2.flysoft.interfaces;

import java.util.Date;

/**
 * Defines the methods of the flight end points.
 * @author Philipp Chavaroche
 * @version 01.05.2016
 */
public interface IFlightEndPoints {
    
    String getOriginAirportShortName();
    void setOriginAirportShortName(String shortName);
    
    double getDeparturedelay();
    void setDeparturedelay(double departureDelay);
    Date getDepartureTime();
    void setDepartureTime(Date date);
    
    String getDestAirportShortName();
    void setDestAirportShortName(String shortName);
    
    double getArrivalDelay();
    void setArrivalDelay(double arrivalDelay);
    Date getArrivalTime();
    void setArrivalTime(Date date);    
}

