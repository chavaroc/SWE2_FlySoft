package hm.edu.swe2.flysoft.entity.controller;

import hm.edu.swe2.flysoft.Entity.FlightIntoDB;
import hm.edu.swe2.flysoft.interfaces.IFlight;
import hm.edu.swe2.flysoft.interfaces.IFlightEndPoints;
import hm.edu.swe2.flysoft.parser.model.ParsedFlight;
import static hm.edu.swe2.flysoft.util.GlobalSettings.DB_PROD_DELETE_FLIGHTS;
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
    private ArrayList<FlightIntoDB> flights; 
    

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
       
       flights.add(new FlightIntoDB(flight.getFlight(),flight.getEndpoints()));
       if(flights.size()>= 200){
           flightController.createAll(flights);
           flights.clear();
       }
       
       if(finish){
           flightController.createAll(flights);
       }
       // fill data tables
       //flightController.create(flight.getFlight(), flight.getEndpoints());
    }
    
    public void destroy(Date fromRange, Date tillRange){
        EntityManager entity = getEntityManager();

        StoredProcedureQuery query = entity.createStoredProcedureQuery(DB_PROD_DELETE_FLIGHTS);

        query.registerStoredProcedureParameter(1, Date.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, Date.class, ParameterMode.IN);

        query.setParameter(1, fromRange, TemporalType.DATE);
        query.setParameter(2, tillRange, TemporalType.DATE);

        query.execute();
    }
}
