package hm.edu.swe2.flysoft.parser;

import hm.edu.swe2.flysoft.parser.model.Flight;
import java.util.Date;

/**
 * This class preparing flight objects
 * and evaulate advanced data of the flight like 
 * - passenger count 
 * - name of the airline
 * - convert arrival time and departure time to date time
 * @author Philipp Chavaroche
 * @version 29.04.2916
 */
public class FlightPreparator {
    
    public Flight prepare(Flight flight){
        flight = solveArrivalDepature(flight);
        flight.setPassengerCount(evaluatePassengerCount(flight));
        flight.setAirlineName(solveAirlineName(flight));
        return flight;
    }
    
    private int evaluatePassengerCount(final Flight flight){
        return -1;
    }
    
    private String solveAirlineName(final Flight flight){
        return "";
    }
    
    private Flight solveArrivalDepature(Flight flight){
        flight.setDepartureDateTime(combineDateTime(flight.getFlightDate(), flight.getDepartureTime()));
        flight.setArrivalDateTime(combineDateTime(flight.getFlightDate(), flight.getArrivalTime()));
        return flight;
    }
    
    private Date combineDateTime(Date date, int Time){
        return null;
    }
}
