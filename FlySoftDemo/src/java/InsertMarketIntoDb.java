
import hm.edu.swe2.flysoft.entity.MonthlyStat;
import hm.edu.swe2.flysoft.entity.controller.MonthlyStatEntityController;
import hm.edu.swe2.flysoft.parser.CsvParser;
import hm.edu.swe2.flysoft.parser.FlightMarketPrepartor;
import hm.edu.swe2.flysoft.parser.NewYorkMonthlyStatFilter;
import hm.edu.swe2.flysoft.parser.mappings.AbstractMapTable;
import hm.edu.swe2.flysoft.parser.mappings.MarketDomesticMapTable;
import java.io.File;
import java.util.List;

/**
 * Writes all needed informations in the database.
 * @author Sven Schulz
 */
public class InsertMarketIntoDb {

    private List<String> fileList;

    /**
     * Declares the needed variabels and starts the method writeIntoDb
     * @param fileList - List of all market csv.
     */
    public InsertMarketIntoDb(List<String> fileList) {
        this.fileList = fileList;
        try {
            writeIntoDB();
        } catch (Exception e) {
        }
    }

    /**
     * Starts the csv parser, looks after needed Informations and write
     * them into the Database.
     * @throws Exception - failure by writing in the Database
     */
    private void writeIntoDB() throws Exception {
        File marketTableFile;
        for (String filePath : fileList) {
            File f = new File(filePath);
            if (!f.exists()) {
                System.out.println("Skipped file '" + filePath + "' because it does not exist");
                continue;
            }
            System.out.println("Parse file '" + filePath + "'");

            marketTableFile = new File(filePath);
            AbstractMapTable config = MarketDomesticMapTable.getInstance();
            CsvParser<MonthlyStat> parser = new CsvParser<>(marketTableFile.getAbsolutePath(), config,
                    ',', MonthlyStat.class);

            System.out.println("Start parsing...");
            List<MonthlyStat> parsedStats = parser.parse();
            System.out.println(parsedStats.size() + " flights parsed.");

            System.out.println("Start filtering...");
            NewYorkMonthlyStatFilter newYorkFilter = new NewYorkMonthlyStatFilter();
            parsedStats = newYorkFilter.filter(parsedStats);
            System.out.println("Filtering finished (" + parsedStats.size() + " flights left).");

            FlightMarketPrepartor marketPrepartor = new FlightMarketPrepartor();
            marketPrepartor.prepareAll(parsedStats);

            System.out.println("Start adding to database...");
            MonthlyStatEntityController controller = new MonthlyStatEntityController();
            for (int i = 0; i < parsedStats.size(); i++) {
                controller.create(parsedStats.get(i));
            }
            System.out.println("Insertion to database finished.");
        }
    }
}
