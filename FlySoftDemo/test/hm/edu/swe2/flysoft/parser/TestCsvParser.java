package hm.edu.swe2.flysoft.parser;


import hm.edu.swe2.flysoft.parser.mappings.AbstractMapTable;
import hm.edu.swe2.flysoft.parser.mappings.OnTimeMapTable;
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
    // Acativate @Test to run as UnitTest
    //@Test
    public void TestCsvParser() throws Exception {
        String path = "C:\\Users\\xYrs\\Documents\\Hm\\6_Semester\\SWE2\\Transtats_Data\\Dometic\\968296915_T_ONTIME.csv";
        AbstractMapTable config = OnTimeMapTable.getInstance();
        CsvParser<Flight> parser = new CsvParser<>(path, config,
            ',', Flight.class);
        List<Flight> result = parser.parse();
        Assert.isTrue(true);
    }
    
    @Test
    public void ParseTestCase1() throws InstantiationException
        , IllegalAccessException, NoSuchMethodException
        , IllegalArgumentException, InvocationTargetException, IOException, Exception{         
                
        File testFile = new File("test/hm/edu/swe2/flysoft/parser/testdata/TestCsvParser_TestCase1.csv");
        System.out.println("Test with " + testFile.getAbsolutePath());
        
        AbstractMapTable config = OnTimeMapTable.getInstance();
        CsvParser<Flight> parser = new CsvParser<>(testFile.getAbsolutePath(), config,
            ',', Flight.class);
        Flight parsedFlight = parser.parse().get(0);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        assertEquals("Flight date is not correctly parsed.", dateFormat.parse("2016-01-29"), parsedFlight.getFlightDate());
        assertEquals("Airline id is not correctly parsed.", 19393, parsedFlight.getAirlineId());
        assertEquals("Carrier short name is not correclty parsed.", "WN", parsedFlight.getUniqueCarrierName());
        assertEquals("Flight number is not correctly parsed.", 557, parsedFlight.getFlightNumber());
        
        assertEquals("Origin airport id is not correctly parsed.", 10279, parsedFlight.getOriginAirportId());
        assertEquals("Origin airport short name is not correctly parsed.", "AMA", parsedFlight.getOriginAirportShortName());
        assertEquals("Origin city id is not correctly parsed.", 30279, parsedFlight.getOriginCityId() );
        assertEquals("Origin city name is not correctly parsed.", "Amarillo, TX", parsedFlight.getOriginCityName());
        assertEquals("Origin state short name is not correctly parsed.", "TX", parsedFlight.getOriginStateShortName());
        assertEquals("Origin state name is not correctly parsed.", "Texas", parsedFlight.getOriginStateName());
        
        assertEquals("Destination airport id is not correctly parsed.", 11259, parsedFlight.getDestAirportId());
        assertEquals("Destination airport short name is not correctly parsed.", "DAL", parsedFlight.getDestAirportShortName());
        assertEquals("Destination city id is not correctly parsed.", 30194, parsedFlight.getDestCityId());
        assertEquals("Destination city name is not correctly parsed.", "Dallas, TX", parsedFlight.getDestCityName());
        assertEquals("Destination state short name is not correctly parsed.", "TX", parsedFlight.getDestStateShortName());
        assertEquals("Destination state name is not correctly parsed.", "Texas", parsedFlight.getDestStateName());
        
        assertEquals("Depature time is not correctly parsed.", 1112, parsedFlight.getDepartureTime());
        assertEquals("Arrival time is not correctly parsed.", 1214, parsedFlight.getArrivalTime() );
        assertEquals("Depature delay is not correctly parsed.", 17.00, parsedFlight.getDepartureDelay(), 0.001);
        assertEquals("Arrival delay is not correctly parsed.", 9.00, parsedFlight.getArrivalDelay(), 0.001);
        
        assertEquals("Cancelled is not correctly parsed.", false, parsedFlight.isCancelled() );
    }
    
    @Test
    public void ParseTestCase2() throws InstantiationException
        , IllegalAccessException, NoSuchMethodException
        , IllegalArgumentException, InvocationTargetException, IOException, Exception{         
        
        File testFile = new File("test/hm/edu/swe2/flysoft/parser/testdata/TestCsvParser_TestCase2.csv");
        System.out.println("Test with " + testFile.getAbsolutePath());
        
        AbstractMapTable config = OnTimeMapTable.getInstance();
        CsvParser<Flight> parser = new CsvParser<>(testFile.getAbsolutePath(), config,
            ',', Flight.class);
        Flight parsedFlight = parser.parse().get(0);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        //TODO set correct expected data
        /*assertEquals("Flight date is not correctly parsed.", dateFormat.parse("2016-01-29"), parsedFlight.getFlightDate());
        assertEquals("Airline id is not correctly parsed.", 19393, parsedFlight.getAirlineId());
        assertEquals("Carrier short name is not correclty parsed.", "WN", parsedFlight.getUniqueCarrierName());
        assertEquals("Flight number is not correctly parsed.", 557, parsedFlight.getFlightNumber());
        
        assertEquals("Origin airport id is not correctly parsed.", 10279, parsedFlight.getOriginAirportId());
        assertEquals("Origin airport short name is not correctly parsed.", "AMA", parsedFlight.getOriginAirportShortName());
        assertEquals("Origin city id is not correctly parsed.", 30279, parsedFlight.getOriginCityId() );
        assertEquals("Origin city name is not correctly parsed.", "Amarillo, TX", parsedFlight.getOriginCityName());
        assertEquals("Origin state short name is not correctly parsed.", "TX", parsedFlight.getOriginStateShortName());
        assertEquals("Origin state name is not correctly parsed.", "Texas", parsedFlight.getOriginStateName());
        
        assertEquals("Destination airport id is not correctly parsed.", 11259, parsedFlight.getDestAirportId());
        assertEquals("Destination airport short name is not correctly parsed.", "DAL", parsedFlight.getDestAirportShortName());
        assertEquals("Destination city id is not correctly parsed.", 30194, parsedFlight.getDestCityId());
        assertEquals("Destination city name is not correctly parsed.", "Dallas, TX", parsedFlight.getDestCityName());
        assertEquals("Destination state short name is not correctly parsed.", "TX", parsedFlight.getDestStateShortName());
        assertEquals("Destination state name is not correctly parsed.", "Texas", parsedFlight.getDestStateName());
        
        assertEquals("Depature time is not correctly parsed.", 1112, parsedFlight.getDepartureTime());
        assertEquals("Arrival time is not correctly parsed.", 1214, parsedFlight.getArrivalTime() );
        assertEquals("Depature delay is not correctly parsed.", 17.00, parsedFlight.getDepartureDelay(), 0.001);
        assertEquals("Arrival delay is not correctly parsed.", 9.00, parsedFlight.getArrivalDelay(), 0.001);
        
        assertEquals("Cancelled is not correctly parsed.", false, parsedFlight.isCancelled() );*/
    }
}
