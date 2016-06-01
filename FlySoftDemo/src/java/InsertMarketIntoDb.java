
import hm.edu.swe2.flysoft.crawler.CrawlTableType;
import hm.edu.swe2.flysoft.crawler.FileCrawler;
import hm.edu.swe2.flysoft.entity.Monthlystat;
import hm.edu.swe2.flysoft.entity.controller.MonthlystatEntityController;
import hm.edu.swe2.flysoft.entity.controller.ParsedFlightController;
import hm.edu.swe2.flysoft.parser.CsvParser;
import hm.edu.swe2.flysoft.parser.FlightPreparator;
import hm.edu.swe2.flysoft.parser.NewYorkFlightFilter;
import hm.edu.swe2.flysoft.parser.NewYorkMonthlyStatFilter;
import hm.edu.swe2.flysoft.parser.mappings.AbstractMapTable;
import hm.edu.swe2.flysoft.parser.mappings.MarketDomesticMapTable;
import hm.edu.swe2.flysoft.parser.mappings.OnTimeMapTable;
import hm.edu.swe2.flysoft.parser.model.ParsedFlight;
import hm.edu.swe2.flysoft.util.GlobalSettings;
import java.io.File;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zwen
 */
public class InsertMarketIntoDb {
        public static void main(String[] args) throws Exception {
        File marketTableFile;
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
        fileList.add(parentDir+ "859627970_T_T100D_MARKET_ALL_CARRIER.csv");
        
       /** FileCrawler crawler = new FileCrawler(
            EnumSet.of(CrawlTableType.T100MarketDomestic));
        crawler.crawl();
        fileList.addAll(crawler.getCrawledFileNames());*/
        for(String filePath : fileList){
            File f = new File(filePath);
            if(!f.exists()){
                System.out.println("Skipped file '"+filePath+"' because it does not exist");
                continue;
            }
            System.out.println("Parse file '"+filePath+"'");
            
            marketTableFile = new File(filePath);
            AbstractMapTable config = MarketDomesticMapTable.getInstance();
            CsvParser<Monthlystat> parser = new CsvParser<>(marketTableFile.getAbsolutePath(), config,
                ',', Monthlystat.class);

            System.out.println("Start parsing...");
            List<Monthlystat> parsedStats = parser.parse();
            System.out.println(parsedStats.size() + " flights parsed.");
            
            System.out.println("Start filtering...");
            NewYorkMonthlyStatFilter newYorkFilter = new NewYorkMonthlyStatFilter();
            parsedStats = newYorkFilter.filter(parsedStats);
            System.out.println("Filtering finished ("+parsedStats.size() +" flights left).");

            System.out.println("Start adding to database...");
            MonthlystatEntityController controller = new MonthlystatEntityController();
            for(int i = 0; i < parsedStats.size(); i++){
                controller.create(parsedStats.get(i));
            }
            System.out.println("Insertion to database finished.");
        }
    }

}
