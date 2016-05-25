package hm.edu.swe2.flysoft.interfaces;

/**
 * Represents a interface for an city object.
 * @author Philipp Chavaroche
 * @version 05.05.2016
 */
public interface ICity {

    Integer getCityId();

    String getName();

    String getShortNameState();

    void setCityId(Integer cityId);

    void setName(String name);

    void setShortNameState(String shortnamestate);
    
}
