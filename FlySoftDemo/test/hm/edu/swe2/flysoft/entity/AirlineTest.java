package hm.edu.swe2.flysoft.entity;

import hm.edu.swe2.flysoft.entity.controller.AirlineEntityController;
import hm.edu.swe2.flysoft.entity.exceptions.NonexistentEntityException;
import hm.edu.swe2.flysoft.interfaces.IAirline;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the airline entity
 * @author Betina Hientz
 */
public class AirlineTest {
    
    public AirlineTest() {
    }
     
    @Test
    public void TestCreateAirline() throws NonexistentEntityException{
        final AirlineEntityController airlineController = new AirlineEntityController();
        int airlineCount = airlineController.getAirlineCount();
        // Create airline
        IAirline airline = new Airline();
        int airlineId = 19978; // NON auto increment field
        airline.setAirlineId(airlineId);
        airline.setName("Lambada");
        airline.setShortName("LD");
        airlineController.create(airline);
    
        // Check if count has increased by one
        assertTrue(airlineController.getAirlineCount() == (++airlineCount));      
        final Optional<IAirline> dbAirline = airlineController.findAirline(airlineId);
        // Test
        assertEquals("Airline that should be created and created airline are not equal!", airline, dbAirline.get());
        // Clear up
        airlineController.destroy(airlineId);
    }
    
    @Test
    public void TestFindAirline(){
        final AirlineEntityController airlineController = new AirlineEntityController();
        
        // Check if count of airline and airline entites are the same count.
        List<IAirline> airlines = airlineController.findAirlineEntities();
        assertEquals("Number of requested airline and airline count should be equal ",
            airlineController.getAirlineCount(), airlines.size());
        
        // Check if limit request works.
        airlines = airlineController.findAirlineEntities(10, 0);
        assertEquals("Number of returned airline are not equal with limited requested ones",
            10, airlines.size());
    }
    
    @Test(expected = NonexistentEntityException.class)
    public void TestAirlineNotExist() throws NonexistentEntityException{
        final AirlineEntityController airlineController = new AirlineEntityController();
        IAirline airline = new Airline();
        int airlineId = 9999999; // NON auto increment field
        airline.setAirlineId(airlineId);
        airline.setName("Lambada");
        airline.setShortName("LD");
        
        final Optional<IAirline> dbAirline = airlineController.findAirline(airlineId);
        if(!dbAirline.isPresent()){
            // Try to destroy non existing airline
            airlineController.destroy(airlineId);
        }
        else{
            assertTrue("Could not perform test, because an airline with id 9999999 exist", false);
        }
    }
    
}
