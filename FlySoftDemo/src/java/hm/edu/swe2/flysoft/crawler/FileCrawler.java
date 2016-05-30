package hm.edu.swe2.flysoft.crawler;

import hm.edu.swe2.flysoft.util.GlobalSettings;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.EnumSet;
import java.util.Set;

/**
 * Downloads and unpacks required Data from transtats.bts.gov.
 *
 * @author Markus Huber
 * @version 30-05-16 modified_last: Markus Huber
 */
public class FileCrawler {

    // location of this file/class
    private String projectDirectory;

    // system-specific path-separator
    private static final char SEPARATOR = File.separatorChar;

    // OnTime, Segment or Market-Table
    private final Set<CrawlTableType> tableTypesTocrawl;

    private final List<String> crawledFileNames;

    /**
     * Creates a new FileCrawler an starts the FileCrawler.
     *
     * @param args - not used.
     *
     */
    public static void main(String[] args) throws URISyntaxException {
        FileCrawler crawler = new FileCrawler(
                EnumSet.of(CrawlTableType.OnTime, CrawlTableType.T100MarketDomestic, CrawlTableType.T100SegmentDomestic
                ));
        crawler.crawl();
    }

    /**
     * Constructor.
     *
     * @param types - specifieces the Table-Types to download.
     * @throws URISyntaxException
     */
    public FileCrawler(Set<CrawlTableType> types) throws URISyntaxException {
        projectDirectory = getProjectDirectory();
        tableTypesTocrawl = types;
        crawledFileNames = new ArrayList<>();
    }

    /**
     * Starts the crawling, dependent on the tableTypesToCrawl.
     *
     * @throws URISyntaxException
     */
    public void crawl() throws URISyntaxException {
        System.out.println("Start file crawling...");
        System.out.println("Info: Project-Directory: " + projectDirectory);
        System.out.println("Info: System-path-separator: " + SEPARATOR);
        if (tableTypesTocrawl.contains(CrawlTableType.OnTime)) {
            String onTimeDataRequest = readConfig(45, 144);
            doRequestAndDownload(onTimeDataRequest);
        }
        if (tableTypesTocrawl.contains(CrawlTableType.T100MarketDomestic)) {
            String marketDataRequest = readConfig(0, 43);
            doRequestAndDownload(marketDataRequest);
        }
        if (tableTypesTocrawl.contains(CrawlTableType.T100SegmentDomestic)) {
            String segmentDataRequest = readConfig(147, 196);
            doRequestAndDownload(segmentDataRequest);
        }
        System.out.println("Crawled files:");
        crawledFileNames.stream()
                .forEach(fileName -> System.out.println(fileName));
    }

    /**
     * The list of file names, that were crawled.
     *
     * @return A list of file names, that were crawled.
     */
    public List<String> getCrawledFileNames() {
        return crawledFileNames;
    }

    /**
     * Downloads required file from transtats.bts.gov
     *
     * @param zipName - Name of required file.
     */
    private void downloadZipFile(String zipName) {
        try {
            URL url = new URL("http://tsdata.bts.gov/" + zipName);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("content-type", "binary/data");
            InputStream in = conn.getInputStream();
            String absoluteFileName = projectDirectory + SEPARATOR + GlobalSettings.CRAWLER_DOWNLOAD_DIR + zipName;
            FileOutputStream out = new FileOutputStream(absoluteFileName);

            byte[] b = new byte[1024];
            int count;
            while ((count = in.read(b)) >= 0) {
                out.write(b, 0, count);
            }
            out.close();
            in.close();
            System.out.println("Info: Stored File " + zipName + ".");
            System.out.println("");
        } catch (IOException e) {
        }
    }

    /**
     * Reads the config file, which includes the parameters for the request for
     * transtats.bts.gov.
     *
     * @param startIndex - index where to start reading the config-file (depends
     * on the table-type, that is needed)
     * @param endIndex - index where to end reading the config-file (depends on
     * the table-type, that is needed)
     * @return resultPart - String - includes all the needed parameters and
     * headerinformation for a request for a certain table-type
     * @throws URISyntaxException
     */
    private String readConfig(int startIndex, int endIndex) throws URISyntaxException {
        ArrayList<String> result = new ArrayList<>();
        String resultPart = "";

        // file, which contains the needed data, parameters, ... for sending a correct request
        URI configUri = FileCrawler.class.getResource(GlobalSettings.CRAWLER_CONFIG_FILE_NAME).toURI();
        File config = new File(configUri.getPath());

        try {
            FileReader reader = new FileReader(config);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String tmpLine;
            for (int i = 0; i < 197; i++) {
                tmpLine = bufferedReader.readLine();
                result.add(tmpLine + "\r\n");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileCrawler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = startIndex; i <= endIndex; i++) {
            resultPart += result.get(i);
        }
        return resultPart;
    }

    /**
     * Connects to Transtats.bts.gov. Sends Request for downloading a certain
     * table, with the needed information. Receives response and starts
     * download. Starts Unzipping of the downloaded file.
     *
     * @param post - String - includes all the needed parameters and
     * headerinformation for a request for a certain table-type
     */
    private void doRequestAndDownload(String post) {
        String responsePart;

        // name of required zip-file
        String fileName;

        try (Socket socket = new Socket("transtats.bts.gov", 80);
                BufferedWriter toServer = new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader fromServer = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()))) {

            ArrayList<String> response = new ArrayList<>();
            toServer.write(post);
            toServer.flush();
            System.out.println("Info: Uploaded Request.");

            responsePart = fromServer.readLine();
            response.add(responsePart);
            while (responsePart.length() > 0) {
                responsePart = fromServer.readLine();
                response.add(responsePart);
            }

            System.out.println("Info: Received Response from Server.");
            System.out.print("Info: Response: ");
            System.out.println(response);

            // reading name of zip-file, which contains the requested data
            fileName = response.get(4).substring(32);
            System.out.println("Info: Requested file's name: " + fileName);

            downloadZipFile(fileName);
            unzipFile(fileName);

        } catch (IOException ex) {
            Logger.getLogger(FileCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getProjectDirectory() throws URISyntaxException {
        URI uri = FileCrawler.class.getResource(GlobalSettings.CRAWLER_CONFIG_FILE_NAME).toURI();
        projectDirectory = uri.toString();
        projectDirectory = projectDirectory.substring(6); // remove prefix "file:/"
        int endIndex = projectDirectory.indexOf("build");
        projectDirectory = projectDirectory.substring(0, endIndex);
        return projectDirectory;
    }

    /**
     * Unzips downloaded files.
     * @param fileName - Name for the unzipped File.
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private void unzipFile(String fileName) throws FileNotFoundException, IOException {
        String downloadPath = projectDirectory + SEPARATOR + GlobalSettings.CRAWLER_DOWNLOAD_DIR;

        ZipInputStream zis = new ZipInputStream(new FileInputStream(downloadPath + fileName));
        ZipEntry entry = zis.getNextEntry();

        byte[] buffer = new byte[1024];

        String unzippedfileName = entry.getName();
        File newFile = new File(downloadPath + unzippedfileName);
        crawledFileNames.add(newFile.getAbsolutePath());
        new File(newFile.getParent()).mkdirs();

        try (FileOutputStream fos = new FileOutputStream(newFile)) {
            int length;
            while ((length = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        }
    }
}
