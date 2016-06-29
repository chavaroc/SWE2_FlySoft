package hm.edu.swe2.flysoft.parser;

import hm.edu.swe2.flysoft.parser.model.ParsedFlight;
import java.util.Calendar;
import java.util.List;

/**
 * This class preparing flight objects
 * and evaulate advanced data of the flight like 
 * - name of the airline
 * - convert / combined arrival time and departure time to date time
 * @author Philipp Chavaroche
 * @version 29.04.2916
 */
public class FlightOnTimePreparator extends AbstractFlightPrepartor{
    
    /**
     * Performs the preparation for the all given flights.
     * @param flights A list of flights
     * @return  A list of preparated flights.
     */
    public List<ParsedFlight> prepareAll(List<ParsedFlight> flights){
        flights.stream()
            .forEach(flight -> prepare(flight));
        return flights;
    }
    
    /**
     * Performs a preparation for the given flight.
     * @param flight
     * @return 
     */
    public ParsedFlight prepare(ParsedFlight flight){
        flight = solveArrivalDepature(flight);
        flight.setAirlineName(solveAirlineName(flight.getAirlineId()));
        return flight;
    }    
    
    
    
    /**
     * Combine the date and the time for arrival and departure 
     * of the flight to one field.
     * @param flight The flight that dates should be converted.
     * @return The flight with combined dates.
     */
    private ParsedFlight solveArrivalDepature(final ParsedFlight flight){
        flight.setDepartureDateTime(combineDateTime(flight.getFlightDate(), flight.getDepartureTime()));
        flight.setArrivalDateTime(combineDateTime(flight.getFlightDate(), flight.getArrivalTime()));
        
        // In the data inport, we have only one date for arrival and depature.
        // So, if the arrival time is earlier than the depature time,
        // that the next day is reached -> increase day by 1.
        if(flight.getArrivalDateTime().before(flight.getDepartureDateTime())){
            final Calendar calendar = Calendar.getInstance(); 
            calendar.setTime(flight.getArrivalDateTime()); 
            calendar.add(Calendar.DATE, 1);
            flight.setArrivalDateTime(calendar.getTime());
        }
        return flight;
    }
}
