package hm.edu.swe2.flysoft.parser;

import hm.edu.swe2.flysoft.parser.model.ParsedFlight;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test the functinality of the class 'FlightOnTimePreparator'
 * @author Philipp Chavaroche
 * @version 29.04.2016
 */
public class FlightPreparatorTest {
    
    /**
     * Test if the date convertions works correct in common.
     * @throws ParseException 
     */
    @Test
    public void TestCombineTimeCase1() throws ParseException{
        FlightOnTimePreparator prep = new FlightOnTimePreparator();
        // Create a dummy flight
        ParsedFlight testFlight = new ParsedFlight();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
        testFlight.setFlightDate(dateFormat.parse("2015-06-15"));
        testFlight.setDepartureTime(1230);
        testFlight.setArrivalTime(1959);
        testFlight.setAirlineId(19977);
        
        assertTrue("DepartureDateTime should be empty after flight init", testFlight.getDepartureDateTime() == null);
        assertTrue("ArrivalDateTime should be empty after flight init", testFlight.getArrivalDateTime()== null);
        
        // Calc DepartureDateTime and ArrivalDateTime
        testFlight = prep.prepare(testFlight);
        
        // Check if calculation is correct
        assertTrue(testFlight.getDepartureDateTime() != null);
        assertTrue(testFlight.getArrivalDateTime() != null);
        assertEquals(dateTimeFormat.parse("2015-06-15 12:30"), testFlight.getDepartureDateTime());
        assertEquals(dateTimeFormat.parse("2015-06-15 19:59"), testFlight.getArrivalDateTime());
    }
    
    /**
     * Test if day is increased by one, if the flight flys over midnight.
     * @throws ParseException 
     */
    @Test
    public void TestCombineTimeNextDay() throws ParseException{
        FlightOnTimePreparator prep = new FlightOnTimePreparator();
        // Create a dummy flight
        ParsedFlight testFlight = new ParsedFlight();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
        testFlight.setFlightDate(dateFormat.parse("2015-06-15"));
        testFlight.setDepartureTime(2300);
        testFlight.setArrivalTime(100);
        testFlight.setAirlineId(19977);
        
        assertNull("DepartureDateTime should be empty after flight init",
            testFlight.getDepartureDateTime());
        assertNull("ArrivalDateTime should be empty after flight init",
            testFlight.getArrivalDateTime());
        
        // Calc DepartureDateTime and ArrivalDateTime
        testFlight = prep.prepare(testFlight);
        
        // Check if calculation is correct
        assertNotNull("Depature date should filled after preparation",
            testFlight.getDepartureDateTime());
        assertNotNull("Arrival date should filled after preparation",
            testFlight.getArrivalDateTime());
        assertEquals("Depature date time conversion failed",
            dateTimeFormat.parse("2015-06-15 23:00"), testFlight.getDepartureDateTime());
        assertEquals("Arrival date time conversion failed",
            dateTimeFormat.parse("2015-06-16 01:00"), testFlight.getArrivalDateTime());
    }
    
    /**
     * Test if the preparator find the given airline id.
     * @throws ParseException 
     */
    @Test
    public void TestSolveAirlineName() throws ParseException{
        FlightOnTimePreparator prep = new FlightOnTimePreparator();
        // Create a dummy flight
        ParsedFlight testFlight = new ParsedFlight();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
        testFlight.setFlightDate(dateFormat.parse("2015-06-15"));
        testFlight.setDepartureTime(2300);
        testFlight.setArrivalTime(100);
        testFlight.setAirlineId(19977);
        
        assertNull("Airline it set but shoudnt", testFlight.getAirlineName());
        
        // Calc DepartureDateTime and ArrivalDateTime
        testFlight = prep.prepare(testFlight);
        
        // Check if calculation is correct
        assertEquals("Airline name was not found",
            "United Air Lines Inc.", testFlight.getAirlineName());
    }
    
    /**
     * Test if the preparator find the given airline id.
     * Expand test to solve airline name cutting (only use name till ":" and "d/b/a"
     * @throws ParseException 
     */
    @Test
    public void TestSolveAirlineName2() throws ParseException{
        FlightOnTimePreparator prep = new FlightOnTimePreparator();
        // Create a dummy flight
        ParsedFlight testFlight = new ParsedFlight();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
        testFlight.setFlightDate(dateFormat.parse("2015-06-15"));
        testFlight.setDepartureTime(2300);
        testFlight.setArrivalTime(100);
        testFlight.setAirlineId(19533);
        
        assertNull("Airline it set but shoudnt", testFlight.getAirlineName());
        
        // Calc DepartureDateTime and ArrivalDateTime
        testFlight = prep.prepare(testFlight);
        
        // Check if calculation is correct
        assertEquals("Airline name was not found or name is cut false",
            "National Aviation Company of India Limited", testFlight.getAirlineName());
    }
}
