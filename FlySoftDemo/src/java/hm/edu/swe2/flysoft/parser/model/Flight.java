package hm.edu.swe2.flysoft.parser.model;

import hm.edu.swe2.flysoft.Entity.Airline;
import hm.edu.swe2.flysoft.interfaces.IFlightEndPoints;
import java.util.Date;

/**
 * Repesents a flight (parsed from CSV).
 * @author Philipp Chavaroche
 * @version 27.04.2016
 */
public class Flight {
    
    //TODO refacture to entity objects or use Flight save (and remove Flightendpoint?)
    
    private int flightNumber;
    private Date flightDate;
    private Airline airline;  
    
    private IFlightEndPoints endpoints;
    
    private int departureTime;      // Only the time
    private int arrivalTime;        // Only the time
    
    private boolean cancelled;
    
    public Flight(){
        airline = new Airline();
        //TODO init enpionts
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
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
        airline.setAirlineId(airlineId);
    }

    public String getUniqueCarrierName() {
        return airline.getShortname();
    }

    public void setUniqueCarrierName(String uniqueCarrierName) {
        airline.setShortname(uniqueCarrierName);
    }
    
    public String getAirlineName() {
        return airline.getName();
    }

    public void setAirlineName(String airlineName) {
        airline.setName(airlineName);;
    }

    public int getOriginAirportId() {
        return endpoints.getOriginAirportId();
    }

    public void setOriginAirportId(int originAirportId) {
        endpoints.setOriginAirportId(originAirportId);
    }
    
    public String getOriginAirportShortName() {
        return endpoints.getOriginAirportShortName();
    }

    public void setOriginAirportShortName(String originAirportShortName) {
        endpoints.setOriginAirportShortName(originAirportShortName);
    }


    public int getOriginCityId() {
        return endpoints.getOriginCityId();
    }

    public void setOriginCityId(int originCityId) {
        endpoints.setOriginCityId(originCityId);
    }

    public String getOriginCityName() {
        return endpoints.getOriginCityName();
    }

    public void setOriginCityName(String originCityName) {
        endpoints.setOriginCityName(originCityName);
    }

    /*public String getOriginStateShortName() {
        return originStateShortName;
    }

    public void setOriginStateShortName(String originStateNumber) {
        this.originStateShortName = originStateNumber;
    }

    public String getOriginStateName() {
        return originStateName;
    }

    public void setOriginStateName(String originStateName) {
        this.originStateName = originStateName;
    }*/

    public double getDepartureDelay() {
        return endpoints.getDepartureDelay();
    }

    public void setDepartureDelay(double departureDelay) {
        endpoints.setDepartureDelay(departureDelay);
    }

    public int getDestAirportId() {
        return endpoints.getDestAirportId();
    }

    public void setDestAirportId(int destAirportId) {
        endpoints.setDestAirportId(destAirportId);
    }
    
    public String getDestAirportShortName() {
        return endpoints.getDestAirportShortName();
    }

    public void setDestAirportShortName(String destAirportShortName) {
        endpoints.setDestAirportShortName(destAirportShortName);
    }

    public int getDestCityId() {
        return endpoints.getDestCityId();        
    }

    public void setDestCityId(int destCityId) {
        endpoints.setDestCityId(destCityId);
    }

    public String getDestCityName() {
        return endpoints.getDestCityName();
    }

    public void setDestCityName(String destCityName) {
        endpoints.setDestCityName(destCityName);
    }

    /*public String getDestStateShortName() {
        return destStateShortName;
    }

    public void setDestStateShortName(String destStateNumber) {
        this.destStateShortName = destStateNumber;
    }

    public String getDestStateName() {
        return destStateName;
    }

    public void setDestStateName(String destStateName) {
        this.destStateName = destStateName;
    }*/

    public double getArrivalDelay() {
        return endpoints.getArrivalDelay();
    }

    public void setArrivalDelay(double arrivalDelay) {
        endpoints.setArrivalDelay(arrivalDelay);
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
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
        return endpoints.getDepartureDateTime();
    }

    public void setDepartureDateTime(Date departureDateTime) {
        endpoints.setDepartureDateTime(departureDateTime);
    }

    public Date getArrivalDateTime() {
        return endpoints.getArrivalDateTime();
    }

    public void setArrivalDateTime(Date arrivalDateTime) {
        endpoints.setArrivalDateTime(arrivalDateTime);
    }
    
    @Override
    public String toString() {
        return "Flight{" + "flightNumber=" + flightNumber 
            + ", flightDate=" + flightDate 
            + ", airlineId=" + airline.getAirlineId()
            + ", uniqueCarrierName=" + airline.getShortname()
            + ", endpoints=" + endpoints.toString()
            + ", cancelled=" + cancelled + '}';
    }  
}
