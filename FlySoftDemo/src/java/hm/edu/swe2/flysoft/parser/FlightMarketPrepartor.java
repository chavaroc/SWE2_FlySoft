package hm.edu.swe2.flysoft.parser;

import hm.edu.swe2.flysoft.entity.MonthlyStat;
import java.util.List;

/**
 * This class preparing monthly stat objects
 * and evaulate advanced data of the flight like 
 * - name of the airline.
 * @author Sven Schulz
 */
public class FlightMarketPrepartor extends AbstractFlightPrepartor{
    
    /**
     * Performs the preparation for the all given flights.
     * @param stats A list of flights
     * @return  A list of preparated flights.
     */
    public List<MonthlyStat> prepareAll(List<MonthlyStat> stats){
        stats.stream()
            .forEach(flight -> prepare(flight));
        return stats;
    }
    
    /**
     * Performs a preparation for the given flight.
     * @param stat
     * @return 
     */
    public MonthlyStat prepare(MonthlyStat stat){
        stat.setCarrierName(solveAirlineName(stat.getAirlineId()));
        return stat;
    }      
}
