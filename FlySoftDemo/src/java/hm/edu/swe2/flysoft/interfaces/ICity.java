package hm.edu.swe2.flysoft.interfaces;

/**
 *
 * @author Philipp Chavaroche
 * @version
 */
public interface ICity {

    Integer getCityId();

    String getName();

    String getShortNameState();

    void setCityId(Integer cityId);

    void setName(String name);

    void setShortNameState(String shortnamestate);
    
}
