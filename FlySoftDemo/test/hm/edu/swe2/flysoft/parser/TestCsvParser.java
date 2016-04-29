package hm.edu.swe2.flysoft.parser;


import hm.edu.swe2.flysoft.parser.model.Flight;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.util.Assert;

/**
 * Test the csv parser.
 * @author Philipp Chavaroche
 */
public class TestCsvParser {
    
    /**
     * Read out all
     * @throws Exception 
     */
    //@Test
    public void TestCsvParser() throws Exception {
        String path = "C:\\Users\\xYrs\\Documents\\Hm\\6_Semester\\SWE2\\Transtats_Data\\Dometic\\968296915_T_ONTIME.csv";
        MapTable config = MapTable.getInstance();
        CsvParser<Flight> parser = new CsvParser<Flight>(path, config,
            ',', Flight.class);
        List<Flight> result = parser.parse(); // TODO, already parse errors
        Assert.isTrue(true); // TODO dummy
    }
    
    @Test
    public void ParseTestCase1() throws InstantiationException
        , IllegalAccessException, NoSuchMethodException
        , IllegalArgumentException, InvocationTargetException, IOException, Exception{         
                
        File testFile = new File("test/hm/edu/swe2/flysoft/parser/testdata/TestCsvParser_TestCase1.csv");
        System.out.println("Test with " + testFile.getAbsolutePath());
        
        MapTable config = MapTable.getInstance();
        CsvParser<Flight> parser = new CsvParser<>(testFile.getAbsolutePath(), config,
            ',', Flight.class);
        Flight parsedFlight = parser.parse().get(0);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        assertEquals("Flight date is not correctly parsed.", parsedFlight.getFlightDate(), dateFormat.parse("2016-01-29"));
        assertEquals("Airline id is not correctly parsed.", parsedFlight.getAirlineId(), 19393);
        assertEquals("Flight number is not correctly parsed.", parsedFlight.getFlightNumber(), 557);
        
        assertEquals("Origin airport id is not correctly parsed.", parsedFlight.getOriginAirportId(), 10279);
        assertEquals("Origin airport short name is not correctly parsed.", parsedFlight.getOriginAirportShortName(), "AMA");
        assertEquals("Origin city id is not correctly parsed.", parsedFlight.getOriginCityId(), 30279);
        assertEquals("Origin city name is not correctly parsed.", parsedFlight.getOriginCityName(), "Amarillo, TX");
        assertEquals("Origin state short name is not correctly parsed.", parsedFlight.getOriginStateShortName(), "TX");
        assertEquals("Origin state name is not correctly parsed.", parsedFlight.getOriginStateName(), "Texas");
        
        assertEquals("Destination airport id is not correctly parsed.", parsedFlight.getDestAirportId(), 11259);
        assertEquals("Destination airport short name is not correctly parsed.", parsedFlight.getDestAirportShortName(), "DAL");
        assertEquals("Destination city id is not correctly parsed.", parsedFlight.getDestCityId(), 30194);
        assertEquals("Destination city name is not correctly parsed.", parsedFlight.getDestCityName(), "Dallas, TX");
        assertEquals("Destination state short name is not correctly parsed.", parsedFlight.getDestStateShortName(), "TX");
        assertEquals("Destination state name is not correctly parsed.", parsedFlight.getDestStateName(), "Texas");
        
        assertEquals("Depature time is not correctly parsed.", parsedFlight.getDepartureTime(), 1112);
        assertEquals("Arrival time is not correctly parsed.", parsedFlight.getArrivalTime(), 1214);
        assertEquals("Depature delay is not correctly parsed.", parsedFlight.getDepartureDelay(), 17.00, 0.001);
        assertEquals("Arrival delay is not correctly parsed.", parsedFlight.getArrivalDelay(), 9.00, 0.001);
        
        assertEquals("Cancelled is not correctly parsed.", parsedFlight.isCancelled(), false);
    }
    
    @Test
    public void ParseTestCase2() throws InstantiationException
        , IllegalAccessException, NoSuchMethodException
        , IllegalArgumentException, InvocationTargetException, IOException, Exception{         
        
        File testFile = new File("test/hm/edu/swe2/flysoft/parser/testdata/TestCsvParser_TestCase2.csv");
        System.out.println("Test with " + testFile.getAbsolutePath());
        
        MapTable config = MapTable.getInstance();
        CsvParser<Flight> parser = new CsvParser<>(testFile.getAbsolutePath(), config,
            ',', Flight.class);
        Flight parsedFlight = parser.parse().get(0);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        //TODO set correct expected data
        /*assertEquals("Flight date is not correctly parsed.", parsedFlight.getFlightDate(), dateFormat.parse("2016-01-29"));
        assertEquals("Airline id is not correctly parsed.", parsedFlight.getAirlineId(), 19393);
        assertEquals("Flight number is not correctly parsed.", parsedFlight.getFlightNumber(), 557);
        
        assertEquals("Origin airport id is not correctly parsed.", parsedFlight.getOriginAirportId(), 10279);
        assertEquals("Origin airport short name is not correctly parsed.", parsedFlight.getOriginAirportShortName(), "AMA");
        assertEquals("Origin city id is not correctly parsed.", parsedFlight.getOriginCityId(), 30279);
        assertEquals("Origin city name is not correctly parsed.", parsedFlight.getOriginCityName(), "Amarillo, TX");
        assertEquals("Origin state short name is not correctly parsed.", parsedFlight.getOriginStateShortName(), "TX");
        assertEquals("Origin state name is not correctly parsed.", parsedFlight.getOriginStateName(), "Texas");
        
        assertEquals("Destination airport id is not correctly parsed.", parsedFlight.getDestAirportId(), 11259);
        assertEquals("Destination airport short name is not correctly parsed.", parsedFlight.getDestAirportShortName(), "DAL");
        assertEquals("Destination city id is not correctly parsed.", parsedFlight.getDestCityId(), 30194);
        assertEquals("Destination city name is not correctly parsed.", parsedFlight.getDestCityName(), "Dallas, TX");
        assertEquals("Destination state short name is not correctly parsed.", parsedFlight.getDestStateShortName(), "TX");
        assertEquals("Destination state name is not correctly parsed.", parsedFlight.getDestStateName(), "Texas");
        
        assertEquals("Depature time is not correctly parsed.", parsedFlight.getDepatureTime(), 1112);
        assertEquals("Arrival time is not correctly parsed.", parsedFlight.getArrivaltime(), 1214);
        assertEquals("Depature delay is not correctly parsed.", parsedFlight.getDepatureDelay(), 17.00, 0.001);
        assertEquals("Arrival delay is not correctly parsed.", parsedFlight.getArrivalDelay(), 9.00, 0.001);
        
        assertEquals("Cancelled is not correctly parsed.", parsedFlight.isCancelled(), false);*/
    }
}
