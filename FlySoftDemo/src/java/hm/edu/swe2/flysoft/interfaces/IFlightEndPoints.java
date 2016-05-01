package hm.edu.swe2.flysoft.interfaces;

import java.util.Date;

/**
 * Defines the methods of the flight end points.
 * @author Philipp Chavaroche
 * @version 01.05.2016
 */
public interface IFlightEndPoints {
    
    int getOriginAirportId();
    void setOriginAirportId(int originAirportId);
    String getOriginAirportShortName();
    void setOriginAirportShortName(String shortName);
    int getOriginCityId();
    void setOriginCityId(int originCityId);
    String getOriginCityName();
    void setOriginCityName(String originCityName);
    /*String getOriginStateShortName();
    void setOriginStateShortName(String originStateNumber);
    String getOriginStateName();
    void setOriginStateName(String originStateName);*/
    double getDepartureDelay();
    void setDepartureDelay(double departureDelay);
    Date getDepartureDateTime();
    void setDepartureDateTime(Date date);
    
    int getDestAirportId();
    void setDestAirportId(int destAirportId);
    String getDestAirportShortName();
    void setDestAirportShortName(String shortName);
    int getDestCityId();
    void setDestCityId(int destCityId);
    String getDestCityName();
    void setDestCityName(String destCityName);
    /*String getDestStateShortName();
    void setDestStateShortName(String destStateNumber);
    String getDestStateName();
    void setDestStateName(String destStateName);*/
    double getArrivalDelay();
    void setArrivalDelay(double arrivalDelay);
    Date getArrivalDateTime();
    void setArrivalDateTime(Date date);    
}

