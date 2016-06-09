/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hm.edu.swe2.flysoft.parser;

import hm.edu.swe2.flysoft.entity.Airline;
import hm.edu.swe2.flysoft.entity.Monthlystat;
import hm.edu.swe2.flysoft.parser.mappings.AbstractMapTable;
import hm.edu.swe2.flysoft.parser.mappings.AirlineLookUpMapTable;
import hm.edu.swe2.flysoft.parser.model.ParsedFlight;
import hm.edu.swe2.flysoft.util.GlobalSettings;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Zwen
 */
public class FlightMarketPrepartor extends AbstractFlightPrepartor{
    
    public FlightMarketPrepartor(){
        
    }
    
    
    /**
     * Performs the preparation for the all given flights.
     * @param stats A list of flights
     * @return  A list of preparated flights.
     */
    public List<Monthlystat> prepareAll(List<Monthlystat> stats){
        stats.stream()
            .forEach(flight -> prepare(flight));
        return stats;
    }
    
    /**
     * Performs a preparation for the given flight.
     * @param flight
     * @return 
     */
    public Monthlystat prepare(Monthlystat stat){
        stat.setCarrierName(solveAirlineName(stat.getAirlineId()));
        return stat;
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
