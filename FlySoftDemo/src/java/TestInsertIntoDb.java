
import hm.edu.swe2.flysoft.crawler.CrawlTableType;
import hm.edu.swe2.flysoft.crawler.FileCrawler;
import hm.edu.swe2.flysoft.entity.controller.ParsedFlightController;
import hm.edu.swe2.flysoft.parser.CsvParser;
import hm.edu.swe2.flysoft.parser.NewYorkFlightFilter;
import hm.edu.swe2.flysoft.parser.FlightOnTimePreparator;
import hm.edu.swe2.flysoft.parser.mappings.AbstractMapTable;
import hm.edu.swe2.flysoft.parser.mappings.OnTimeMapTable;
import hm.edu.swe2.flysoft.parser.model.ParsedFlight;
import hm.edu.swe2.flysoft.util.GlobalSettings;
import java.io.File;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * This class performs a data download from trans stats (via crawler),
 * parse the data and write them into db.
 * @author Philipp Chavaroche
 * @version 25.05.2016
 */
public class TestInsertIntoDb {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        File onTimeTableFile;
        List<String> fileList = new ArrayList<String>();
        String parentDir = GlobalSettings.CRAWLER_DOWNLOAD_DIR;
        /*fileList.add(parentDir + "2015_02_T_ONTIME.csv");
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
        
        FileCrawler crawler = new FileCrawler(
            EnumSet.of(CrawlTableType.OnTime));
        crawler.crawl();
        fileList.addAll(crawler.getCrawledFileNames());
            
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
            
            onTimeTableFile = new File(filePath);
            AbstractMapTable config = OnTimeMapTable.getInstance();
            CsvParser<ParsedFlight> parser = new CsvParser<>(onTimeTableFile.getAbsolutePath(), config,
                ',', ParsedFlight.class);

            System.out.println("Start parsing...");
            List<ParsedFlight> parsedFlights = parser.parse();
            System.out.println(parsedFlights.size() + " flights parsed.");

            System.out.println("Start filtering...");
            NewYorkFlightFilter newYorkFilter = new NewYorkFlightFilter();
            parsedFlights = newYorkFilter.filter(parsedFlights);
            System.out.println("Filtering finished ("+parsedFlights.size() +" flights left).");

            System.out.println("Prepare flights.");
            FlightOnTimePreparator preparator = new FlightOnTimePreparator();
            preparator.prepareAll(parsedFlights);
            System.out.println("Preparation finished.");

            System.out.println("Start adding to database...");
            ParsedFlightController controller = new ParsedFlightController();
            controller.createAll(parsedFlights);
            System.out.println("Insertion to database finished.");
        }
    }
    

}
