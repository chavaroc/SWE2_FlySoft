package hm.edu.swe2.flysoft.entity;

import hm.edu.swe2.flysoft.entity.controller.ParsedFlightController;
import hm.edu.swe2.flysoft.entity.exceptions.NonexistentEntityException;
import hm.edu.swe2.flysoft.parser.CsvParser;
import hm.edu.swe2.flysoft.parser.FlightOnTimePreparator;
import hm.edu.swe2.flysoft.parser.mappings.AbstractMapTable;
import hm.edu.swe2.flysoft.parser.mappings.OnTimeMapTable;
import hm.edu.swe2.flysoft.parser.model.ParsedFlight;
import java.io.File;
import java.util.List;
import org.junit.Test;

/**
 * Test how long it takes to insert 10 flights (and bounding flightendpoints).
 * @author Philipp Chavaroche
 * @version 06.06.2016
 */
public class TestPerformanceInsertFlights {
    
    public TestPerformanceInsertFlights() {
    }
    
    @Test
    public void TimeMeaureInsert1kFlights() throws NonexistentEntityException, Exception{
        File testFile = new File("test/hm/edu/swe2/flysoft/parser/testdata/TestData_OnTime_1000_Fake_Dec2016.csv");
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
        
        ParsedFlightController controller = new ParsedFlightController();
        System.out.println(flights.size() + " flights parsed.");
        startTime = System.nanoTime();
        controller.createAll(flights);
        System.out.println(toSeconds(System.nanoTime() - startTime) + " sec to write into db.");
        // current time to insert 1000 flights: 143 sec (2 min 23 secs).
        // TODO test delete prod
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
