package hm.edu.swe2.flysoft.entity;

import hm.edu.swe2.flysoft.entity.controller.FlightEndPointEntityController;
import hm.edu.swe2.flysoft.entity.controller.FlightEntityController;
import hm.edu.swe2.flysoft.entity.exceptions.NonexistentEntityException;
import hm.edu.swe2.flysoft.interfaces.IFlight;
import hm.edu.swe2.flysoft.interfaces.IFlightEndPoints;
import java.util.Date;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Tests for the flight entity
 * @author Betina Hientz
 */
public class TestFlight {
    
    public TestFlight() {
    }
    
    @Test
    public void TestCreateFlight() throws NonexistentEntityException{
        FlightEndPointEntityController flightEndPointController = new FlightEndPointEntityController();
        int currentFlightEndPointCount = flightEndPointController.getFlightEndPointCount();
        IFlightEndPoints  flightEndPoint = new FlightEndPoint();
        int flightEndPointId = 1344;
        flightEndPoint.setFlightEndPointId(flightEndPointId);
        flightEndPoint.setArrivalDelay(2);
        flightEndPoint.setArrivalTime(new Date());
        flightEndPoint.setDepartureDelay(4);
        flightEndPoint.setDepartureTime(new Date());
        flightEndPoint.setDestAirportShortName("AKA");
        flightEndPoint.setOriginAirportShortName("BK");
        
        flightEndPointController.create(flightEndPoint);
        
        
        FlightEntityController flightController = new FlightEntityController(flightEndPointController);
        int currentFlightCount = flightController.getFlightCount();
        IFlight flight = new Flight();
        int flightId = 19978;
        flight.setFlightId(flightId);
        flight.setAirlineId(45345);
        flight.setFlightEndPointId(5345);
        flight.setCancelled(true);
        flightController.create(flight, flightEndPoint);
    
        assertTrue(flightController.getFlightCount() == (++currentFlightCount));
        Optional<IFlight> dbFlight = flightController.findFlight(flightId);
        assertEquals("Flight that should be created and created flight are not equal!", flight, dbFlight.get());
        flightController.destroy(flightId);
        flightEndPointController.destroy(flightEndPointId);
    }
}
