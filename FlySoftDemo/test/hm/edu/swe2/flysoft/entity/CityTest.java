package hm.edu.swe2.flysoft.entity;

import hm.edu.swe2.flysoft.entity.controller.CityEntityController;
import hm.edu.swe2.flysoft.entity.exceptions.NonexistentEntityException;
import hm.edu.swe2.flysoft.interfaces.ICity;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the city entity
 * @author Philipp Chavaroche
 */
public class CityTest {
    
    public CityTest() {
    }
    
    @Test
    public void TestCreateCity() throws NonexistentEntityException{
        CityEntityController cityController = new CityEntityController();
        int currentCityCount = cityController.getCityCount();
        ICity newCity = new City();
        int cityId = 342343; // NON auto increment field
        newCity.setCityId(342343);
        newCity.setName("New York");
        cityController.create(newCity);
        assertTrue(cityController.getCityCount() == (++currentCityCount));
        Optional<ICity> optDbCity = cityController.findCity(cityId);
        assertEquals("City that should be created and created city are not equal!", newCity, optDbCity.get());
        cityController.destroy(cityId);
    }
    
    @Test
    public void TestFindCity() throws NonexistentEntityException{
        final CityEntityController cityController = new CityEntityController();
        
        List<ICity> cities = cityController.findCityEntities();
        
        final ICity city2 = new City(-1, "Test Neverland");
        cityController.create(city2);
        
        assertEquals("Count of airports should be increased by 1",
            cities.size()+1, cityController.findCityEntities().size());
        cityController.destroy(city2.getCityId());
        
        // Check if limit request works.
        cities = cityController.findCityEntities(10, 0);
        assertEquals("Number of returned cities are not equal with limited requested ones",
            10, cities.size());
    }
    
    @Test(expected = NonexistentEntityException.class)
    public void TestCityNotExist() throws NonexistentEntityException{
        final CityEntityController cityController = new CityEntityController();
        ICity city = new City();
        city.setCityId(9999999);
        city.setName("Test Atlantis");
        
        final Optional<ICity> optDbCity = cityController.findCity(city.getCityId());
        if(!optDbCity.isPresent()){
            // Try to destroy non existing airline
            cityController.destroy(city.getCityId());
        }
        else{
            assertTrue("Could not perform test, because an city with id 9999999 exist", false);
        }
    }
}
