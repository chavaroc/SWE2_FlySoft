package hm.edu.swe2.flysoft.entity.controller;

import hm.edu.swe2.flysoft.parser.model.ParsedFlight;
import java.util.List;

/**
 * Represents a controller, that handle 
 * the parsed flight objects to write them into database.
 * @author Philipp Chavaroche
 * @version 05.05.2016
 */
public class ParsedFlightController extends AbstractEntityController{

    public ParsedFlightController() {
    }
    
    public void createAll(List<ParsedFlight> flights) {
        flights.stream()
            .forEach(flight -> create(flight));
    }
    
    public void create(ParsedFlight flight) {
       FlightEndPointEntityController endpointController = new FlightEndPointEntityController();
       FlightEntityController flightController = new FlightEntityController(endpointController);
       
       CityEntityController cityController = new CityEntityController();
       AirlineEntityController airlineController = new AirlineEntityController();
       AirportEntityController airportController = new AirportEntityController();
       
       // fill lookup tables
       airlineController.createIfNotExist(flight.getAirline());
       cityController.createIfNotExist(flight.getOriginCity());
       cityController.createIfNotExist(flight.getDestCity());
       airportController.createIfNotExist(flight.getOriginAirport());
       airportController.createIfNotExist(flight.getDestAirport());
       
       // fill data tables
       flightController.create(flight.getFlight(), flight.getEndpoints());
    }
}
