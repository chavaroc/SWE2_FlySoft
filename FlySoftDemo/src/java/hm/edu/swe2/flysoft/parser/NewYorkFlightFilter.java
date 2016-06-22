package hm.edu.swe2.flysoft.parser;

import hm.edu.swe2.flysoft.parser.model.ParsedFlight;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Filter all flights out, that are NOT start from a New York airport.
 * @author Philipp Chavaroche
 * @version 29.04.2916
 */
public class NewYorkFlightFilter {

    private static final List<String> newYorkAirportSN = new ArrayList<>();
    
    /**
     * Construct a new New York flight filter object.
     */
    public NewYorkFlightFilter() {
        if(newYorkAirportSN.isEmpty()){
            newYorkAirportSN.add("JFK");
            newYorkAirportSN.add("LGA");
            newYorkAirportSN.add("FLU");
            newYorkAirportSN.add("NYC");
        }
    }
    
    /**
     * Filter all flights out, that are NOT start from a New York airport.
     * @param flights The full list of flights.
     * @return A list of flights, that start only in New York airports.
     */
    public List<ParsedFlight> filter(List<ParsedFlight> flights){
        System.out.println("Filter out flights, having there origin NOT in New York.");
        return flights.stream()
            .filter(flight -> newYorkAirportSN.contains(flight.getOriginAirport().getShortName()))
            .collect(Collectors.toList());
    }

}
