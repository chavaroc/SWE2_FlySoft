/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hm.edu.swe2.flysoft.parser;

import hm.edu.swe2.flysoft.entity.Monthlystat;
import hm.edu.swe2.flysoft.parser.model.ParsedFlight;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Zwen
 */
public class NewYorkMonthlyStatFilter {
    private static final List<String> newYorkAirportSN = new ArrayList<>();
    
    /**
     * Construct a new New York flight filter object.
     */
    public NewYorkMonthlyStatFilter() {
        if(newYorkAirportSN.isEmpty()){
            newYorkAirportSN.add("JFK");
            newYorkAirportSN.add("LGA");
            newYorkAirportSN.add("EWR");
        }
    }
    
        /**
     * Filter all flights out, that are NOT start from a New York airport.
     * @param stats The full list of flights.
     * @return A list of flights, that start only in New York airports.
     */
    public List<Monthlystat> filter(List<Monthlystat> stats){
        System.out.println("Filter out flights, having there origin NOT in New York.");
        return stats.stream()
            .filter(stat -> newYorkAirportSN.contains(stat.getOriginAirportShortName()))
            .filter(stat -> stat.getPassengerCount() > 0)
            .collect(Collectors.toList());
    }

}
