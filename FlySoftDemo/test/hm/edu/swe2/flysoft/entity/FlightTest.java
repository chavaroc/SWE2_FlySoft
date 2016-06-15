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
public class FlightTest {
    
    public FlightTest() {
    }
    
    @Test
    public void TestCreateFlight() throws NonexistentEntityException{
        FlightEndPointEntityController flightEndPointController = new FlightEndPointEntityController();
        int currentFlightEndPointCount = flightEndPointController.getFlightEndPointCount();
        IFlightEndPoints  flightEndPoint = new FlightEndPoint();
        int flightEndPointId = 1344; // temp id, database each entry a auto increment id
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
        int flightId = 19978; // temp id, database each entry a auto increment id
        Date arrivalDate = new Date();
        flight.setFlightId(flightId);
        flight.setAirlineId(45345);
        flight.getFlightEndPoint().setFlightEndPointId(5345);
        flight.getFlightEndPoint().setArrivalTime(new Date());
        flight.getFlightEndPoint().setDepartureTime(new Date());
        flight.setCancelled(true);
        flightController.create(flight);
        
        assertTrue(flightController.getFlightCount() == (++currentFlightCount));
        Optional<IFlight> dbFlight = flightController.findFlight(flight.getFlightId());
        assertEquals("Flight that should be created and created flight are not equal!", flight, dbFlight.get());
        flightController.destroy(flight.getFlightId());
        flightEndPointController.destroy(flightEndPoint.getFlightEndPointId());
    }
}
