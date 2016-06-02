package hm.edu.swe2.flysoft.entity;

import hm.edu.swe2.flysoft.entity.controller.AirlineEntityController;
import hm.edu.swe2.flysoft.entity.exceptions.NonexistentEntityException;
import hm.edu.swe2.flysoft.interfaces.IAirline;
import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the airline entity
 * @author Betina Hientz
 */
public class TestAirline {
    
    public TestAirline() {
    }
     
    @Test
    public void TestCreateAirline() throws NonexistentEntityException{
        AirlineEntityController airlineController = new AirlineEntityController();
        int currentAirlineCount = airlineController.getAirlineCount();
        IAirline airline = new Airline();
        int airlineId = 19978;
        airline.setAirlineId(airlineId);
        airline.setName("Lambada");
        airline.setShortName("LD");
        airlineController.create(airline);
    
        assertTrue(airlineController.getAirlineCount() == (++currentAirlineCount));
        Optional<IAirline> dbAirline = airlineController.findAirline(airlineId);
        assertEquals("Airline that should be created and created airline are not equal!", airline, dbAirline.get());
        airlineController.destroy(airlineId);
    }
}
