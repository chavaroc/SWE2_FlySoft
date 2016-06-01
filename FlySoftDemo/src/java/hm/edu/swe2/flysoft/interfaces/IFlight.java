package hm.edu.swe2.flysoft.interfaces;

/**
 * Represents a interface for an flight object.
 * @author Philipp Chavaroche
 * @version 05.05.2016
 */
public interface IFlight {
    
    Integer getFlightendpointId();
    void setFlightendpointId(Integer flightendpointId);

    Integer getAirlineId();

    boolean getCancelled();

    Integer getFlightId();

    void setAirlineId(Integer airlineId);

    void setCancelled(boolean cancelled);

    void setFlightId(Integer flightId);
    
}
