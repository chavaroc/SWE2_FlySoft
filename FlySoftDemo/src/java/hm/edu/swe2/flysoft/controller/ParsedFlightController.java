package hm.edu.swe2.flysoft.controller;

import hm.edu.swe2.flysoft.entity.Airline;
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
       FlightEntityController flightController = new FlightEntityController();
       AirlineEntityController airlineController = new AirlineEntityController();
       airlineController.createIfNotExist((Airline)flight.getAirline());
       //flightController.create(flight.getFlight());
       // TODO continue impl...
       // check if already exist?
    }

}
