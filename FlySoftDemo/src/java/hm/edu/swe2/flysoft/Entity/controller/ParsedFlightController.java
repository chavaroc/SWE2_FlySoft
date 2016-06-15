package hm.edu.swe2.flysoft.entity.controller;

import hm.edu.swe2.flysoft.Entity.FlightIntoDB;
import hm.edu.swe2.flysoft.entity.Flight;
import hm.edu.swe2.flysoft.interfaces.IFlight;
import hm.edu.swe2.flysoft.interfaces.IFlightEndPoints;
import hm.edu.swe2.flysoft.parser.model.ParsedFlight;
import static hm.edu.swe2.flysoft.util.GlobalSettings.DB_PROD_DELETE_FLIGHTS;
import static hm.edu.swe2.flysoft.util.GlobalSettings.FLIGHT_INSERTION_BUFFER_SIZE;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import static java.util.Locale.filter;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TemporalType;


/**
 * Represents a controller, that handle 
 * the parsed flight objects to write them into database.
 * @author Philipp Chavaroche
 * @version 05.05.2016
 */
public class ParsedFlightController extends AbstractEntityController{
    private final FlightEndPointEntityController endpointController;
    private final FlightEntityController flightController;
    private final CityEntityController cityController;
    private final AirlineEntityController airlineController;
    private final AirportEntityController airportController;
    private ArrayList<IFlight> flights; 
    

    public ParsedFlightController() {
       endpointController = new FlightEndPointEntityController();
       flightController = new FlightEntityController(endpointController);
       
       cityController = new CityEntityController();
       airlineController = new AirlineEntityController();
       airportController = new AirportEntityController();
       
      flights = new ArrayList();
    }
    
    public void createAll(List<ParsedFlight> flights) {
       // flights.stream()
       //     .forEach(flight -> create(flight));
       boolean finish = false;
       for(int i = 0; i < flights.size(); i++){
           create(flights.get(i), finish);
           if(i == flights.size()-1){
               finish = true;
           }
       }
    }
    
    public void create(ParsedFlight flight, boolean finish) {
       // fill lookup tables
       airlineController.createIfNotExist(flight.getAirline());
       cityController.createIfNotExist(flight.getOriginCity());
       cityController.createIfNotExist(flight.getDestCity());
       airportController.createIfNotExist(flight.getOriginAirport());
       airportController.createIfNotExist(flight.getDestAirport());
       
       flights.add(flight.getFlight());
       if(flights.size()>= FLIGHT_INSERTION_BUFFER_SIZE){
           flightController.createAll(flights);
           flights.clear();
       }
       
       if(finish){
           flightController.createAll(flights);
       }
       // fill data tables
       //flightController.create(flight.getFlight(), flight.getEndpoints());
    }
    
    /**
     * Delete all flight and flight endpoint data entries in the database
     * that are within given time range.
     * @param fromRange Start of the time range.
     * @param tillRange Stop of the time range.
     */
    public void destroy(final Date fromRange, final Date tillRange){
        final EntityManager entity = getEntityManager();
        final StoredProcedureQuery query = entity.createStoredProcedureQuery(DB_PROD_DELETE_FLIGHTS);

        query.registerStoredProcedureParameter(1, Date.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, Date.class, ParameterMode.IN);

        query.setParameter(1, fromRange, TemporalType.DATE);
        query.setParameter(2, tillRange, TemporalType.DATE);

        query.execute();
    }
}
