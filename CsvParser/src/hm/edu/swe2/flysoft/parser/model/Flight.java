package hm.edu.swe2.flysoft.parser.model;

import java.util.Date;

/**
 * Repesents a flight (parsed from CSV).
 * @author Philipp Chavaroche
 * @version 27.04.2016
 */
public class Flight {
    
    private int flightNumber;
    private Date flightDate;
    private int airlineId;
    private String uniqueCarrierName;
    
    private int passengerCount;
    
    private int originAirportId;
    private String originAirportShortName;
    private int originCityId;
    private String originCityShortName;
    private String originCityName;
    private String originStateShortName;
    private String originStateName;
    private int depatureDelay;
    
    private int destAirportId;
    private String destAirportShortName;
    private int destCityId;
    private String destCityShortName;
    private String destCityName;
    private String destStateShortName;
    private String destStateName;
    private int arrivalDelay;
    
    private boolean cancelled;

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
        return airlineId;
    }

    public void setAirlineId(int airlineId) {
        this.airlineId = airlineId;
    }

    public String getUniqueCarrierName() {
        return uniqueCarrierName;
    }

    public void setUniqueCarrierName(String uniqueCarrierName) {
        this.uniqueCarrierName = uniqueCarrierName;
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

    public String getOriginCityShortName() {
        return originCityShortName;
    }

    public void setOriginCityShortName(String originCityShortName) {
        this.originCityShortName = originCityShortName;
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

    public int getDepatureDelay() {
        return depatureDelay;
    }

    public void setDepatureDelay(int depatureDelay) {
        this.depatureDelay = depatureDelay;
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

    public String getDestCityShortName() {
        return destCityShortName;
    }

    public void setDestCityShortName(String destCityShortName) {
        this.destCityShortName = destCityShortName;
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

    public int getArrivalDelay() {
        return arrivalDelay;
    }

    public void setArrivalDelay(int arrivalDelay) {
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
    
}
