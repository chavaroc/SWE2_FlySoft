
import hm.edu.swe2.flysoft.controller.ParsedFlightController;
import hm.edu.swe2.flysoft.parser.CsvParser;
import hm.edu.swe2.flysoft.parser.FlightFilter;
import hm.edu.swe2.flysoft.parser.FlightPreparator;
import hm.edu.swe2.flysoft.parser.mappings.AbstractMapTable;
import hm.edu.swe2.flysoft.parser.mappings.OnTimeMapTable;
import hm.edu.swe2.flysoft.parser.model.ParsedFlight;
import java.io.File;
import java.util.List;


/**
 *
 * @author Philipp Chavaroche
 * @version 
 */
public class TestInsertIntoDb {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        File onTimeTable;
        String filePath = "C:\\Users\\xYrs\\Documents\\Hm\\6_Semester\\SWE2\\Transtats_Data\\Dometic\\968296915_T_ONTIME.csv";
        
        if(args.length > 0){
            filePath = args[0];
        }
        else{
            System.out.println("Required OnTime csv file path as first parameter.");
            //return;
        }
        onTimeTable = new File(filePath);
        AbstractMapTable config = OnTimeMapTable.getInstance();
        CsvParser<ParsedFlight> parser = new CsvParser<>(onTimeTable.getAbsolutePath(), config,
            ',', ParsedFlight.class);
        
        System.out.println("Start parsing...");
        List<ParsedFlight> parsedFlights = parser.parse();
        System.out.println(parsedFlights.size() + " flights parsed.");
        
        System.out.println("Start filtering...");
        FlightFilter newYorkFilter = new FlightFilter();
        parsedFlights = newYorkFilter.filter(parsedFlights);
        System.out.println("Filtering finished ("+parsedFlights.size() +" flights left).");
        
        System.out.println("Prepare flights.");
        FlightPreparator preparator = new FlightPreparator();
        preparator.prepareAll(parsedFlights);
        System.out.println("Preparation finished.");
        
        System.out.println("Start adding to database...");
        ParsedFlightController controller = new ParsedFlightController();
        controller.createAll(parsedFlights);
        System.out.println("Insertion to database finished.");
    }

}
