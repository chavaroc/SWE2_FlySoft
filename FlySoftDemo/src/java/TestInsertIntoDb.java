
import hm.edu.swe2.flysoft.entity.controller.ParsedFlightController;
import hm.edu.swe2.flysoft.parser.CsvParser;
import hm.edu.swe2.flysoft.parser.FlightFilter;
import hm.edu.swe2.flysoft.parser.FlightPreparator;
import hm.edu.swe2.flysoft.parser.mappings.AbstractMapTable;
import hm.edu.swe2.flysoft.parser.mappings.OnTimeMapTable;
import hm.edu.swe2.flysoft.parser.model.ParsedFlight;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.ArrayList;
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
        List<String> fileList = new ArrayList<String>();
        //String filePath //"C:\\Users\\xYrs\\Documents\\Hm\\6_Semester\\SWE2\\Transtats_Data\\Dometic\\968296915_T_ONTIME.csv";
//            = "C:\\Users\\xYrs\\Documents\\Hm\\6_Semester\\SWE2\\FlySoft_Git\\FileCrawler\\FileCrawler\\downloaded\\2015_01_T_ONTIME.csv";
        String parentDir = "C:\\Users\\Zwen\\Documents\\SWE2_FlySoft\\FileCrawler\\FileCrawler\\downloaded\\";
/**        fileList.add(parentDir + "2015_02_T_ONTIME.csv");
        fileList.add(parentDir + "2015_03_T_ONTIME.csv");
        fileList.add(parentDir + "2015_04_T_ONTIME.csv");
        fileList.add(parentDir + "2015_05_T_ONTIME.csv");
        fileList.add(parentDir + "2015_06_T_ONTIME.csv");
        fileList.add(parentDir + "2015_07_T_ONTIME.csv");
        fileList.add(parentDir + "2015_08_T_ONTIME.csv");
        fileList.add(parentDir + "2015_09_T_ONTIME.csv");
        fileList.add(parentDir + "2015_10_T_ONTIME.csv");
        fileList.add(parentDir + "2015_11_T_ONTIME.csv");
        fileList.add(parentDir + "2015_12_T_ONTIME.csv");*/
        fileList.add(parentDir+ "313307865_T_ONTIME.csv");
            
        //if(args.length > 0){
        //    filePath = args[0];
        //}
        //else{
        //    System.out.println("Required OnTime csv file path as first parameter.");
            //return;
        //}
        for(String filePath : fileList){
            File f = new File(filePath);
            if(!f.exists()){
                System.out.println("Skipped file '"+filePath+"' because it does not exist");
                continue;
            }
            System.out.println("Parse file '"+filePath+"'");
            
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

}
