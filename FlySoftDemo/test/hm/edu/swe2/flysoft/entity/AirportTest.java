package hm.edu.swe2.flysoft.entity;

import hm.edu.swe2.flysoft.entity.controller.AirportEntityController;
import hm.edu.swe2.flysoft.entity.exceptions.NonexistentEntityException;
import hm.edu.swe2.flysoft.interfaces.IAirport;
import java.util.Optional;
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
        int currentCountAirports = controller.findAirlineEntities().size();
        Airport airport = new Airport("muc2", 0);
        Airport airport2 = new Airport("jfk2", 1);
        controller.create(airport);
        controller.create(airport2);
        assertTrue(currentCountAirports+2 == controller.findAirlineEntities().size());
        
        // try to create aiport with same short name (not allow, should do nothing)
        Airport airport4 = new Airport("jfk2", 1);
        controller.createIfNotExist(airport4);
        assertTrue(currentCountAirports+2 == controller.findAirlineEntities().size());
        
        Airport airport3 = new Airport("tel2", 2);
        controller.create(airport3);
        assertTrue(currentCountAirports+3 == controller.findAirlineEntities().size());
        Optional<IAirport> search = controller.findAirport("jfk2");
        assertTrue(airport2.equals(search.get()));
        
        controller.destroy("jfk2");
        search = controller.findAirport("jfk2");
        assertTrue(!search.isPresent());
        assertTrue(currentCountAirports+2 == controller.findAirlineEntities().size());               
        controller.destroy("muc2");
        controller.destroy("tel2");
        assertTrue(currentCountAirports == controller.findAirlineEntities().size());               
    }   
}
