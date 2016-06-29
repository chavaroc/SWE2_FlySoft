package hm.edu.swe2.flysoft.entity;

import hm.edu.swe2.flysoft.entity.controller.FlightEndPointEntityController;
import hm.edu.swe2.flysoft.entity.controller.FlightEntityController;
import hm.edu.swe2.flysoft.entity.controller.ParsedFlightController;
import hm.edu.swe2.flysoft.entity.controller.QueryController;
import hm.edu.swe2.flysoft.entity.exceptions.NonexistentEntityException;
import hm.edu.swe2.flysoft.parser.CsvParser;
import hm.edu.swe2.flysoft.parser.FlightOnTimePreparator;
import hm.edu.swe2.flysoft.parser.NewYorkFlightFilter;
import hm.edu.swe2.flysoft.parser.mappings.AbstractMapTable;
import hm.edu.swe2.flysoft.parser.mappings.OnTimeMapTable;
import hm.edu.swe2.flysoft.parser.model.ParsedFlight;
import hm.edu.swe2.flysoft.ui.FilterSetting;
import static hm.edu.swe2.flysoft.util.GlobalSettings.FREQUENCIES;
import static hm.edu.swe2.flysoft.util.GlobalSettings.TIME;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * Test how long it takes to insert 10 flights (and bounding flightendpoints).
 * @author Philipp Chavaroche
 * @version 06.06.2016
 */
public class PerformanceInsertFlightsTest {
    
    /**
     * Insert 1000 on time flights into database and measure the time, how long 
     * it takes.
     * All inserted flights will deleted after measurement.
     * @throws NonexistentEntityException
     * @throws Exception 
     */
    //@Test
    public void TimeMeaureInsert1kFlights() throws NonexistentEntityException, Exception{
        FlightEndPointEntityController endpointController = new FlightEndPointEntityController();
        FlightEntityController flightController = new FlightEntityController(endpointController);
        File testFile = new File("test/hm/edu/swe2/flysoft/parser/testdata/TestData_OnTime_1000_Fake_Dec2016.csv");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        System.out.println("Test with " + testFile.getAbsolutePath());
        
        AbstractMapTable config = OnTimeMapTable.getInstance();
        CsvParser<ParsedFlight> parser = new CsvParser<>(testFile.getAbsolutePath(), config,
            ',', ParsedFlight.class);
        long startTime = System.nanoTime();
        // Parse
        List<ParsedFlight> flights = parser.parse();
        // Data correction / preparation
        FlightOnTimePreparator preparator = new FlightOnTimePreparator();
        preparator.prepareAll(flights);
        System.out.println(toSeconds(System.nanoTime() - startTime) + " sec for parsing and preparation.");
        System.out.println(flights.size() + " flights parsed.");
        
        // Write into db (mearue)
        final int flightCountBefore = flightController.getFlightCount();
        ParsedFlightController controller = new ParsedFlightController();
        startTime = System.nanoTime();
        controller.createAll(flights);
        System.out.println(toSeconds(System.nanoTime() - startTime) + " sec to write into db.");
        
        // check if the entries were really written
        final int flightCountAfter = flightController.getFlightCount();
        System.out.println(flightCountAfter - flightCountBefore + " flights written.");
        
        // clear up
        System.out.println("Start to clear up...");
        controller.destroy(dateFormat.parse("2016-12-01"), dateFormat.parse("2016-12-31"));
        System.out.println("Finished");
        
        //insert 1000 flights, buffer size 200, vpn home: (2 min 23 secs).
        //insert 1000 flights, buffer size 500, vpn home: 2 min 22 secs
        //insert 1000 flights, buffer size 100, vpn home: 2 min 22 secs
        //insert 1000 flights, buffer size 5, vpn home: 2 min 42 secs
        //insert 1000 flights, buffer size 1000, vpn home: 2 min 22 secs
        
    }
    
    /**
     * Test end to end data inport..
     * @throws NonexistentEntityException
     * @throws Exception 
     */
    @Test
    public void TestInsert50Flights() throws NonexistentEntityException, Exception{
        FlightEndPointEntityController endpointController = new FlightEndPointEntityController();
        FlightEntityController flightController = new FlightEntityController(endpointController);
        final String dateFrom = "2016-12-01";
        final String dateTo = "2016-12-31";
        File testFile = new File("test/hm/edu/swe2/flysoft/parser/testdata/TestData_OnTime_50_Fake_Dec2016.csv");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        
        System.out.println("Test with " + testFile.getAbsolutePath());
        
        AbstractMapTable config = OnTimeMapTable.getInstance();
        CsvParser<ParsedFlight> parser = new CsvParser<>(testFile.getAbsolutePath(), config,
            ',', ParsedFlight.class);
        long startTime = System.nanoTime();
        // Parse
        List<ParsedFlight> flights = parser.parse();
        
        // filter non new york flights
        NewYorkFlightFilter newYorkFilter = new NewYorkFlightFilter();
        flights = newYorkFilter.filter(flights);
        
        // Data correction / preparation
        FlightOnTimePreparator preparator = new FlightOnTimePreparator();
        preparator.prepareAll(flights);
        System.out.println(toSeconds(System.nanoTime() - startTime) + " sec for parsing and preparation.");
        System.out.println(flights.size() + " flights parsed.");
        
        // Write into db (mearue)
        final int flightCountBefore = flightController.getFlightCount();
        ParsedFlightController flightcontroller = new ParsedFlightController();
        startTime = System.nanoTime();
        flightcontroller.createAll(flights);
        System.out.println(toSeconds(System.nanoTime() - startTime) + " sec to write into db.");
        
        // check if the entries were really written
        final int flightCountAfter = flightController.getFlightCount();
        System.out.println(flightCountAfter - flightCountBefore + " flights written.");
        
        // check results
        assertTrue("Not all 50 entries were inserted", flightCountAfter - flightCountBefore == 50);
        
        // clear up
        System.out.println("Start to clear up...");
        flightcontroller.destroy(dateFormat.parse(dateFrom), dateFormat.parse(dateTo));
        System.out.println("Finished");        
    }
    
    /**
     * Converts the given value of nano seconds into seconds.
     * @param nanoSeconds The given value of nano seconds
     * @return The string that represents the time in min and sec.
     */
    private String toSeconds(long nanoSeconds){
        String result;
        //                  micro , milli, sec
        long secs = nanoSeconds/1000/1000/1000;
        if(secs > 60){
            result = secs/60 + " min " + secs%60 + " secs";
        }
        else{
            result = secs + " secs";
        }
        return result;
    }
    
}
