package hm.edu.swe2.flysoft.interfaces;

/**
 * Represents a interface for an airport object.
 * @author Philipp Chavaroche
 * @version 05.05.2016
 */
public interface IAirport {

    int getAirportId();

    Integer getCityId();

    String getShortName();

    void setAirportId(int airportId);

    void setCityId(Integer cityId);

    void setShortName(String shortName);
    
}
