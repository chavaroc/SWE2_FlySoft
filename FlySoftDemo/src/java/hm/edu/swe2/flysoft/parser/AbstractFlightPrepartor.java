/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hm.edu.swe2.flysoft.parser;

import hm.edu.swe2.flysoft.entity.Airline;
import hm.edu.swe2.flysoft.parser.mappings.AbstractMapTable;
import hm.edu.swe2.flysoft.parser.mappings.AirlineLookUpMapTable;
import hm.edu.swe2.flysoft.parser.model.ParsedFlight;
import hm.edu.swe2.flysoft.util.GlobalSettings;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Zwen
 */
public class AbstractFlightPrepartor {
    
    protected static List<Airline> lookupAirlines;

    public AbstractFlightPrepartor() {
    }

    /**
     * Solve the airline name of the airline id.
     * @param airlineId The given airline id.
     * @return The name of the airline of the flight.
     */
    protected String solveAirlineName(final int airlineId) {
        String airlineName = "";
        // check if the lookup table is loaded
        if (lookupAirlines == null) {
            // Load loopup table
            File testFile = new File(GlobalSettings.AIRLINE_FILE_NAME);
            AbstractMapTable config = AirlineLookUpMapTable.getInstance();
            CsvParser<Airline> parser = new CsvParser<>(testFile.getAbsolutePath(), config, ',', Airline.class);
            try {
                lookupAirlines = parser.parse();
            } catch (Exception ex) {
                System.out.println("Error while try to parse lookup table 'Airlines'");
                System.out.println(ex);
            }
        }
        // find matching airline for flight
        Optional<Airline> foundAirline = lookupAirlines.stream().filter((Airline airline) -> airline.getAirlineId() == airlineId).findFirst();
        if (foundAirline.isPresent()) {
            airlineName = foundAirline.get().getName();
        } else {
            airlineName = "";
        }
        return airlineName;
    }

    /**
     * Combine a date and a time (int fromat) to a date that contains both.
     * @param date the date without time.
     * @param time the time in format hhmm
     * @return Combined date.
     */
    protected Date combineDateTime(final Date date, final int time) {
        long milliseconds; // ms since 1.1.1970
        // date should be the same as before
        milliseconds = date.getTime();
        // cut up minutes
        final int hours = time / 100;
        // subtract hours from original time -> get minutes
        int minues = time - (hours * 100);
        // calc total minutes
        minues += hours * 60;
        // add date and time together and build new date
        return new Date(milliseconds + minues * 60 * 1000);
    }
    
}
