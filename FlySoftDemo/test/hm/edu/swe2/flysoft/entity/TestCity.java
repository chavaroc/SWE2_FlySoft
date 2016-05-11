
package hm.edu.swe2.flysoft.entity;

import hm.edu.swe2.flysoft.controller.CityEntityController;
import hm.edu.swe2.flysoft.controller.exceptions.NonexistentEntityException;
import hm.edu.swe2.flysoft.interfaces.ICity;
import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests 
 * @author Philipp Chavaroche
 */
public class TestCity {
    
    public TestCity() {
    }
    
    @Test
    public void TestCreateCity() throws NonexistentEntityException{
        CityEntityController cityController = new CityEntityController();
        int currentCityCount = cityController.getCityCount();
        ICity newCity = new City();
        int cityId = 342343;
        newCity.setCityId(342343);
        newCity.setName("BlaBla");
        newCity.setShortNameState("bla");
        cityController.create(newCity);
        assertTrue(cityController.getCityCount() == (++currentCityCount));
        Optional<ICity> optDbCity = cityController.findCity(cityId);
        assertEquals("City that should be created and created city are not equal!", newCity, optDbCity.get());
        cityController.destroy(cityId);
    }
}