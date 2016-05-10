package hm.edu.swe2.flysoft.interfaces;

/**
 *
 * @author Philipp Chavaroche
 * @version
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
