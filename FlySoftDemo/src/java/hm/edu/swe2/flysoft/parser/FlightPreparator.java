package hm.edu.swe2.flysoft.parser;

import hm.edu.swe2.flysoft.Entity.Airline;
import hm.edu.swe2.flysoft.parser.mappings.AbstractMapTable;
import hm.edu.swe2.flysoft.parser.mappings.AirlineLookUpMapTable;
import hm.edu.swe2.flysoft.parser.model.Flight;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * This class preparing flight objects
 * and evaulate advanced data of the flight like 
 * - passenger count 
 * - name of the airline
 * - convert / combined arrival time and departure time to date time
 * @author Philipp Chavaroche
 * @version 29.04.2916
 */
public class FlightPreparator {
    
    private static List<Airline> lookupAirlines;
    
    /**
     * Performs a preparation for the given flight.
     * @param flight
     * @return 
     */
    public Flight prepare(Flight flight){
        flight = solveArrivalDepature(flight);
        flight.setPassengerCount(evaluatePassengerCount(flight));
        flight.setAirlineName(solveAirlineName(flight));
        return flight;
    }
    
    private int evaluatePassengerCount(final Flight flight){
        return -1; // TODO
    }
    
    /**
     * Solve the airline name of the flight via airline id.
     * @param flight The given flight.
     * @return The name of the airline of the flight.
     */
    private String solveAirlineName(final Flight flight){
        String airlineName ="";
        // check if the lookup table is loaded
        if(lookupAirlines == null){
            // Load loopup table
            File testFile = new File("resource/L_AIRLINE_ID.csv");
            AbstractMapTable config = AirlineLookUpMapTable.getInstance();
            CsvParser<Airline> parser = new CsvParser<>(testFile.getAbsolutePath(), config,
                ',', Airline.class);
            try {
                lookupAirlines = parser.parse();
            } catch (Exception ex) {
                System.out.println("Error while try to parse lookup table 'Airlines'");
                System.out.println(ex);
            }
        }
        // find matching airline for flight
        Optional<Airline> foundAirline = lookupAirlines.stream()
               .filter(airline -> airline.getAirlineId() == flight.getAirlineId())
               .findFirst();
           if(foundAirline.isPresent()){
               airlineName = foundAirline.get().getName();
           }
           else{
               airlineName = "";
           }
        return airlineName;
    }
    
    /**
     * Combine the date and the time for arrival and departure 
     * of the flight to one field.
     * @param flight The flight that dates should be converted.
     * @return The flight with combined dates.
     */
    private Flight solveArrivalDepature(final Flight flight){
        flight.setDepartureDateTime(combineDateTime(flight.getFlightDate(), flight.getDepartureTime()));
        flight.setArrivalDateTime(combineDateTime(flight.getFlightDate(), flight.getArrivalTime()));
        
        // if the arrival time is earlier than the depature time,
        // that the next day is reached -> increase day by 1.
        if(flight.getArrivalDateTime().before(flight.getDepartureDateTime())){
            final Calendar calendar = Calendar.getInstance(); 
            calendar.setTime(flight.getArrivalDateTime()); 
            calendar.add(Calendar.DATE, 1);
            flight.setArrivalDateTime(calendar.getTime());
        }
        return flight;
    }
    
    /**
     * Combine a date and a time (int fromat) to a date that contains both.
     * @param date the date without time.
     * @param time the time in format hhmm
     * @return Combined date.
     */
    private Date combineDateTime(final Date date, final int time){
        long milliseconds; // ms since 1.1.1970
        // date should be the same as before
        milliseconds = date.getTime();
        // cut up minutes
        final int hours = time / 100; 
        // subtract hours from original time -> get minutes
        int minues = time - (hours * 100); 
        // calc total minutes
        minues += hours*60;
        // add date and time together and build new date
        return new Date(milliseconds + minues*60*1000);
    }
}
