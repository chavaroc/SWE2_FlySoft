
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Starts the the CSV Parser and writes the information to the Database
 *
 * @author Zwen
 */
public class StartIntoDb {

    private final File intoFile;
    private final File[] fileArray;
    private List<String> intoMarket;
    private List<String> intoOnTime;

    /**
     * @param path - Is the path from the folder where all csv are.
     */
    public StartIntoDb(final String path) {
        intoFile = new File(path);
        fileArray = intoFile.listFiles();
        intoMarket = new ArrayList<>();
        intoOnTime = new ArrayList<>();
        sortArray();
        InsertMarketIntoDb market = new InsertMarketIntoDb(intoMarket);
        TestInsertIntoDb onTime = new TestInsertIntoDb(intoOnTime);
    }

    /**
     * This Method sort all csv files, and add the right File into a ArrayList.
     */
    private void sortArray() {
        for (int i = 0; i < fileArray.length; i++) {
            File sortFile = fileArray[i];
            String sortString = sortFile.getName();
            if (sortString.contains("ONTIME")) {
                intoOnTime.add(sortFile.getAbsolutePath());
            } else if (sortString.contains("MARKET")) {
                intoMarket.add(sortFile.getAbsolutePath());
            }
        }
    }

    /**
     * Starts the csv Parser and the connection to the Database.
     *
     * @param args - path of the csv folder.
     */
    public static void main(String[] args) {
        String path;
        try (Scanner sc = new Scanner(System.in)) {
            path = sc.nextLine();
        }
        StartIntoDb intoDb = new StartIntoDb(path);
    }

}
