package hm.edu.swe2.flysoft.parser.model;

import hm.edu.swe2.flysoft.Entity.Airline;
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
    
    private int passengerCount;
    
    private int originAirportId;
    private String originAirportShortName;
    private int originCityId;
    private String originCityName;
    private String originStateShortName;
    private String originStateName;
    
    private double departureDelay;
    private int departureTime;      // Only the time
    private Date departureDateTime;
    
    private int destAirportId;
    private String destAirportShortName;
    private int destCityId;
    private String destCityName;
    private String destStateShortName;
    private String destStateName;
    private double arrivalDelay;
    
    private int arrivalTime;        // Only the time
    private Date arrivalDateTime;
    
    private boolean cancelled;
    
    public Flight(){
        airline = new Airline();
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
        return originAirportId;
    }

    public void setOriginAirportId(int originAirportId) {
        this.originAirportId = originAirportId;
    }

    public int getOriginCityId() {
        return originCityId;
    }

    public void setOriginCityId(int originCityId) {
        this.originCityId = originCityId;
    }

    public String getOriginCityName() {
        return originCityName;
    }

    public void setOriginCityName(String originCityName) {
        this.originCityName = originCityName;
    }

    public String getOriginStateShortName() {
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
    }

    public double getDepartureDelay() {
        return departureDelay;
    }

    public void setDepartureDelay(double departureDelay) {
        this.departureDelay = departureDelay;
    }

    public int getDestAirportId() {
        return destAirportId;
    }

    public void setDestAirportId(int destAirportId) {
        this.destAirportId = destAirportId;
    }

    public int getDestCityId() {
        return destCityId;
    }

    public void setDestCityId(int destCityId) {
        this.destCityId = destCityId;
    }

    public String getDestCityName() {
        return destCityName;
    }

    public void setDestCityName(String destCityName) {
        this.destCityName = destCityName;
    }

    public String getDestStateShortName() {
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
    }

    public double getArrivalDelay() {
        return arrivalDelay;
    }

    public void setArrivalDelay(double arrivalDelay) {
        this.arrivalDelay = arrivalDelay;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public String getOriginAirportShortName() {
        return originAirportShortName;
    }

    public void setOriginAirportShortName(String originAirportShortName) {
        this.originAirportShortName = originAirportShortName;
    }

    public String getDestAirportShortName() {
        return destAirportShortName;
    }

    public void setDestAirportShortName(String destAirportShortName) {
        this.destAirportShortName = destAirportShortName;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }

    public int getDepartureTime() {
        return departureTime;
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
        return departureDateTime;
    }

    public void setDepartureDateTime(Date departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public Date getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(Date arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }
    

    @Override
    public String toString() {
        return "Flight{" + "flightNumber=" + flightNumber 
            + ", flightDate=" + flightDate 
            + ", airlineId=" + airline.getAirlineId()
            + ", uniqueCarrierName=" + airline.getShortname()
            + ", passengerCount=" + passengerCount 
            + ", originAirportId=" + originAirportId 
            + ", originAirportShortName=" + originAirportShortName 
            + ", originCityId=" + originCityId 
            + ", originCityName=" + originCityName 
            + ", originStateShortName=" + originStateShortName 
            + ", originStateName=" + originStateName 
            + ", departureDelay=" + departureDelay 
            + ", destAirportId=" + destAirportId 
            + ", destAirportShortName=" + destAirportShortName 
            + ", destCityId=" + destCityId 
            + ", destCityName=" + destCityName 
            + ", destStateShortName=" + destStateShortName 
            + ", destStateName=" + destStateName 
            + ", arrivalDelay=" + arrivalDelay 
            + ", cancelled=" + cancelled + '}';
    }

    
    
    
}