
package hm.edu.swe2.flysoft.entity;

import hm.edu.swe2.flysoft.controller.CityEntityController;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author xYrs
 */
public class TestCity {
    
    public TestCity() {
    }
    
    @Test
    public void TestCreateCity(){
        // TODO -> does not work correctly. UnitTests use other "settings" for the entity controller.
        CityEntityController controller = new CityEntityController();
        int currentCityCount = controller.getCityCount();
        City city = new City();
        city.setCityId(2); // auto increment!
        city.setName("BlaBla");
        city.setShortNameState("bla");
        System.out.println(city.getCityId());
        controller.create(city);
        System.out.println(city.getCityId());
        assertTrue(controller.getCityCount() == (++currentCityCount));
        city = controller.findCity(2);
        System.out.println(city.getCityId());
    }
}
