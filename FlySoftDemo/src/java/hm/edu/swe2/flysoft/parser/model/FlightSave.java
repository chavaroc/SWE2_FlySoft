package hm.edu.swe2.flysoft.parser.model;

import hm.edu.swe2.flysoft.entity.Airline;
import hm.edu.swe2.flysoft.entity.City;
import hm.edu.swe2.flysoft.entity.Flight;
import hm.edu.swe2.flysoft.entity.Flightendpoint;

/**
 * Repesents a flight (parsed from CSV).
 * @author Philipp Chavaroche
 * @version 27.04.2016
 */
public class FlightSave {
    
    private Flight flight = new Flight();
    private Airline airline = new Airline();
    private Flightendpoint endPoint = new Flightendpoint();
    private City city = new City();
    
    //private int flightNumber;
   // private Date flightDate;
    //private int airlineId;
    //private String uniqueCarrierName;
    
   // private int passengerCount;
    
//    private int originAirportId;
//    private String originAirportShortName;
//    private int originCityId;
//    private String originCityShortName;
//    private String originCityName;
//    private String originStateShortName;
//    private String originStateName;
//    private int depatureDelay;
    
//    private int destAirportId;
//    private String destAirportShortName;
//    private int destCityId;
//    private String destCityShortName;
//    private String destCityName;
//    private String destStateShortName;
//    private String destStateName;
//    private int arrivalDelay;
    
    //private boolean cancelled;

    public int getFlightNumber() {
        return flight.getFlightId();
    }

    public void setFlightNumber(int flightNumber) {
        flight.setFlightId(flightNumber);
    }

//    public Date getFlightDate() {
//        return flightDate;
//    }
//
//    public void setFlightDate(Date flightDate) {
//        this.flightDate = flightDate;
//    }

    public int getAirlineId() {
        return flight.getAirlineId();
    }

    public void setAirlineId(int airlineId) {
        flight.setAirlineId(airlineId);
    }

    public String getUniqueCarrierName() {
        return airline.getName();
    }

    public void setUniqueCarrierName(String uniqueCarrierName) {
        airline.setName(uniqueCarrierName);
    }



    public int getOriginCityId() {
        return city.getCityId();
    }

    public void setCityId(int cityId) {
        city.setCityId(cityId);
    }

    public String getCityName() {
        return city.getName();
    }

    public void setCityName(String cityName) {
        city.setName(cityName);
    }



  

    public boolean isCancelled() {
        return flight.getCancelled(); 
    }

    public void setCancelled(boolean cancelled) {
        flight.setCancelled(cancelled);
    }

    
    
}
