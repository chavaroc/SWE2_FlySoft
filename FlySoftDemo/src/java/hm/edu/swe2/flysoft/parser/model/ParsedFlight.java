package hm.edu.swe2.flysoft.parser.model;

import hm.edu.swe2.flysoft.entity.Airline;
import hm.edu.swe2.flysoft.entity.Airport;
import hm.edu.swe2.flysoft.entity.City;
import hm.edu.swe2.flysoft.entity.Flight;
import hm.edu.swe2.flysoft.entity.FlightEndPoint;
import hm.edu.swe2.flysoft.interfaces.IAirline;
import hm.edu.swe2.flysoft.interfaces.IAirport;
import hm.edu.swe2.flysoft.interfaces.ICity;
import hm.edu.swe2.flysoft.interfaces.IFlight;
import hm.edu.swe2.flysoft.interfaces.IFlightEndPoints;
import java.util.Date;

/**
 * Repesents a flight (parsed from CSV).
 * @author Philipp Chavaroche
 * @version 27.04.2016
 */
public class ParsedFlight {
    
    private IFlight flight;
    private IAirline airline;  
    private IAirport originAirport;
    private IAirport destAirport;
    private ICity originCity;
    private ICity destCity;
    
    private Date flightDate;
    /**
     * The time of the depature.
     * Contains only the time in format (e.g. 1330 for 1:30 pm).
     */
    private int departureTime;      
    
    /**
     * The time of the arrival.
     * Contains only the time in format (e.g. 1330 for 1:30 pm).
     */
    private int arrivalTime;        
    
    public ParsedFlight(){
        flight = new Flight();
        airline = new Airline();
        originAirport = new Airport();
        destAirport = new Airport();
        originCity = new City();
        destCity = new City();
    }

    public int getFlightNumber() {
        return flight.getFlightId(); // Flight number is not unique, use auto increment in db.
    }

    public void setFlightNumber(int flightNumber) {
        flight.setFlightId(flightNumber);
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public int getAirlineId() {
        return airline.getAirlineId();
    }

    public void setAirlineId(int airlineId) {
        flight.setAirlineId(airlineId);
        airline.setAirlineId(airlineId);
    }

    public String getUniqueCarrierName() {
        return airline.getShortName();
    }

    public void setUniqueCarrierName(String uniqueCarrierName) {
        airline.setShortName(uniqueCarrierName);
    }
    
    public String getAirlineName() {
        return airline.getName();
    }

    public void setAirlineName(String airlineName) {
        airline.setName(airlineName);;
    }

    public int getOriginAirportId() {
        return originAirport.getAirportId();
    }

    public void setOriginAirportId(int originAirportId) {
        originAirport.setAirportId(originAirportId);
    }
    
    public String getOriginAirportShortName() {
        return originAirport.getShortName();
    }

    public void setOriginAirportShortName(String originAirportShortName) {
        flight.getFlightEndPoint().setOriginAirportShortName(originAirportShortName);
        originAirport.setShortName(originAirportShortName);
    }


    public int getOriginCityId() {
        return originCity.getCityId();
    }

    public void setOriginCityId(int originCityId) {
        originAirport.setCityId(originCityId);
        originCity.setCityId(originCityId);
    }

    public String getOriginCityName() {
        return originCity.getName();
    }

    public void setOriginCityName(String originCityName) {
        originCity.setName(originCityName);
    }

    public double getDepartureDelay() {
        return flight.getFlightEndPoint().getDepartureDelay();
    }

    public void setDepartureDelay(double departureDelay) {
        flight.getFlightEndPoint().setDepartureDelay(departureDelay);
    }

    public int getDestAirportId() {
        return destAirport.getAirportId();
    }

    public void setDestAirportId(int destAirportId) {
        destAirport.setAirportId(destAirportId);
    }
    
    public String getDestAirportShortName() {
        return destAirport.getShortName();
    }

    public void setDestAirportShortName(String destAirportShortName) {
        flight.getFlightEndPoint().setDestAirportShortName(destAirportShortName);
        destAirport.setShortName(destAirportShortName);
    }

    public int getDestCityId() {
        return destCity.getCityId();
    }

    public void setDestCityId(int destCityId) {
        destAirport.setCityId(destCityId);
        destCity.setCityId(destCityId);
    }

    public String getDestCityName() {
        return destCity.getName();
    }

    public void setDestCityName(String destCityName) {
        destCity.setName(destCityName);
    }

    public double getArrivalDelay() {
        return flight.getFlightEndPoint().getArrivalDelay();
    }

    public void setArrivalDelay(double arrivalDelay) {
        flight.getFlightEndPoint().setArrivalDelay(arrivalDelay);
    }

    public boolean isCancelled() {
        return flight.getCancelled();
    }

    public void setCancelled(boolean cancelled) {
        flight.setCancelled(cancelled);
    }

    public int getDepartureTime() {
        return this.departureTime;
    }

    public void setDepartureTime(int departureTime) {
        this.departureTime = departureTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getDepartureDateTime() {
        return flight.getFlightEndPoint().getDepartureTime();
    }

    public void setDepartureDateTime(Date departureDateTime) {
        flight.getFlightEndPoint().setDepartureTime(departureDateTime);
    }

    public Date getArrivalDateTime() {
        return flight.getFlightEndPoint().getArrivalTime();
    }

    public void setArrivalDateTime(Date arrivalDateTime) {
        flight.getFlightEndPoint().setArrivalTime(arrivalDateTime);
    }

    public IFlight getFlight() {
        return flight;
    }

    public IAirline getAirline() {
        return airline;
    }

    public IFlightEndPoints getEndpoints() {
        return flight.getFlightEndPoint();
    }

    public IAirport getOriginAirport() {
        return originAirport;
    }

    public IAirport getDestAirport() {
        return destAirport;
    }

    public ICity getOriginCity() {
        return originCity;
    }

    public ICity getDestCity() {
        return destCity;
    }    
    
    @Override
    public String toString() {
        return "Flight{" + "flightNumber=" + flight.getFlightId()
            + ", flightDate=" + flightDate 
            + ", airlineId=" + airline.getAirlineId()
            + ", uniqueCarrierName=" + airline.getShortName()
            + ", endpoints=" + flight.getFlightEndPoint().toString()
            + ", cancelled=" + flight.getCancelled() + '}';
    }  
}
