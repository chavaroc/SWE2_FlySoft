/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hm.edu.swe2.flysoft.parser;

import hm.edu.swe2.flysoft.parser.model.ParsedFlight;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Philipp Chavaroche
 * @version 
 */
public class FlightFilter {

    private static final List<String> newYorkAirportSN = new ArrayList<>();
    
    public FlightFilter() {
        if(newYorkAirportSN.isEmpty()){
            newYorkAirportSN.add("JFK");
            newYorkAirportSN.add("LGA");
            newYorkAirportSN.add("EWR");
        }
    }
    
    public List<ParsedFlight> filter(List<ParsedFlight> flights){
        System.out.println("Filter out flights, having there origin NOT in New York.");
        return flights.stream()
            .filter(flight -> newYorkAirportSN.contains(flight.getOriginAirport().getShortName()))
            .collect(Collectors.toList());
    }

}
