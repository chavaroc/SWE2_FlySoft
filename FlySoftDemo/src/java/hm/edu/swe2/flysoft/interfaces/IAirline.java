package hm.edu.swe2.flysoft.interfaces;

/**
 * Represents a interface for an airline object.
 * @author Philipp Chavaroche
 * @version 05.05.2016
 */
public interface IAirline {

    Integer getAirlineId();

    String getName();

    String getShortName();

    void setAirlineId(Integer airlineId);

    void setName(String name);

    void setShortName(String shortName);
    
}
