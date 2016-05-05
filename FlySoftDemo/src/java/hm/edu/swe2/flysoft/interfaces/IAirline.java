package hm.edu.swe2.flysoft.interfaces;

/**
 *
 * @author Philipp Chavaroche
 * @version
 */
public interface IAirline {

    Integer getAirlineId();

    String getName();

    String getShortname();

    void setAirlineId(Integer airlineId);

    void setName(String name);

    void setShortname(String shortname);
    
}
