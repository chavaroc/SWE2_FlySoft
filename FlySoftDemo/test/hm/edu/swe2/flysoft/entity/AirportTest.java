package hm.edu.swe2.flysoft.entity;

import hm.edu.swe2.flysoft.entity.controller.AirportEntityController;
import hm.edu.swe2.flysoft.entity.exceptions.NonexistentEntityException;
import hm.edu.swe2.flysoft.interfaces.IAirport;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Tests for the airport entity
 * @author Zwen
 */
public class AirportTest {
    
    public AirportTest(){
    }
 
    @Test
    public void testCreateAirport() throws NonexistentEntityException{
        AirportEntityController controller = new AirportEntityController();
        int currentCountAirports = controller.findAirportEntities().size();
        Airport airport = new Airport("muc2", 0);
        Airport airport2 = new Airport("jfk2", 1);
        controller.create(airport);
        controller.create(airport2);
        assertTrue(currentCountAirports+2 == controller.findAirportEntities().size());
        
        // try to create aiport with same short name (not allow, should do nothing)
        Airport airport4 = new Airport("jfk2", 1);
        controller.createIfNotExist(airport4);
        assertTrue(currentCountAirports+2 == controller.findAirportEntities().size());
        
        Airport airport3 = new Airport("tel2", 2);
        controller.create(airport3);
        assertTrue(currentCountAirports+3 == controller.findAirportEntities().size());
        Optional<IAirport> search = controller.findAirport("jfk2");
        assertTrue(airport2.equals(search.get()));
        
        controller.destroy("jfk2");
        search = controller.findAirport("jfk2");
        assertTrue(!search.isPresent());
        assertTrue(currentCountAirports+2 == controller.findAirportEntities().size());               
        controller.destroy("muc2");
        controller.destroy("tel2");
        assertTrue(currentCountAirports == controller.findAirportEntities().size());               
    }
    
    @Test
    public void testAirportEqual(){
        // Note: The id is irrelevant for the compare (can diver from database and csv import)
        Airport airport = new Airport("JFK", 0);
        Airport airport2 = new Airport("LGA", 1);
        
        Assert.assertFalse("Airports should not be equal", airport.equals(airport2));
        Assert.assertFalse("Airports should not be equal", airport2.equals(airport));
        Assert.assertFalse("Airports should not be equal", airport.equals(null));
        Assert.assertTrue("Airports should be equal to itself", airport.equals(airport));
        
        airport.setShortName(null);
        
        Assert.assertFalse("Airports should not be equal", airport.equals(airport2));
        Assert.assertFalse("Airports should not be equal", airport2.equals(airport));
        Assert.assertFalse("Airports should not be equal", airport.equals(null));
        Assert.assertTrue("Airports should be equal to itself", airport.equals(airport));
        
        airport.setShortName("LGA");
        
        Assert.assertTrue("Airports should be equal", airport.equals(airport2));
        Assert.assertTrue("Airports should be equal", airport2.equals(airport));
        Assert.assertFalse("Airports should not be equal", airport.equals(null));
        Assert.assertTrue("Airports should be equal to itself", airport.equals(airport));
    }
    
    @Test
    public void TestFindAirport() throws NonexistentEntityException{
        final AirportEntityController airportController = new AirportEntityController();
        
        // Check if count of airport and airline entites are the same count.
        List<IAirport> airport = airportController.findAirportEntities();
        
        Airport airport2 = new Airport("jfk2", 1);
        airportController.create(airport2);
        
        assertEquals("Count of airports should be increased by 1",
            airport.size()+1, airportController.findAirportEntities().size());
        airportController.destroy(airport2.getShortName());
        
        // Check if limit request works.
        airport = airportController.findAirportEntities(10, 0);
        assertEquals("Number of returned airline are not equal with limited requested ones",
            10, airport.size());
    }
    
    @Test(expected = NonexistentEntityException.class)
    public void TestAirportNotExist() throws NonexistentEntityException{
        final AirportEntityController airportController = new AirportEntityController();
        IAirport airport = new Airport();
        airport.setShortName("muc2");
        
        final Optional<IAirport> optDbAirport = airportController.findAirport(airport.getShortName());
        if(!optDbAirport.isPresent()){
            // Try to destroy non existing airline
            airportController.destroy(airport.getShortName());
        }
        else{
            assertTrue("Could not perform test, because an airline with id 9999999 exist", false);
        }
    }
}

