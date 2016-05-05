
import hm.edu.swe2.flysoft.controller.ParsedFlightController;
import hm.edu.swe2.flysoft.parser.CsvParser;
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
        
        if(args.length > 0){
            onTimeTable = new File(args[0]);
        }
        else{
            System.out.println("Required OnTime csv file path as first parameter.");
            return;
        }
        AbstractMapTable config = OnTimeMapTable.getInstance();
        CsvParser<ParsedFlight> parser = new CsvParser<>(onTimeTable.getAbsolutePath(), config,
            ',', ParsedFlight.class);
        List<ParsedFlight> parsedFlights = parser.parse();
        FlightPreparator preparator = new FlightPreparator();
        preparator.prepareAll(parsedFlights);
        
        // TODO use FlightEntityController to insert flights
        
        ParsedFlightController controller = new ParsedFlightController();
        controller.createAll(parsedFlights);
    }

}
