//TODO: Coding-Standard!

package filecrawler;

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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Downloads required Data (as zip) from transtats.bts.gov
 *
 * @author Markus Huber
 * @version 30-04-16 modified_last: Markus Huber
 */
public class FileCrawler {

    private static String projectDirectory;
    private static char separator;

    public static void main(String[] args) throws URISyntaxException {

        getProjectDirectory();
        System.out.println("Info: Project-Directory: " + projectDirectory);

        separator = File.separatorChar;
        System.out.println("Info: System-path-separator: " + separator);

        String marketDataRequest = readConfig(0, 43);
        doRequestAndDownload(marketDataRequest);

        String onTimeDataRequest = readConfig(45, 144);
        doRequestAndDownload(onTimeDataRequest);

        String segmentDataRequest = readConfig(147, 196);
        doRequestAndDownload(segmentDataRequest);
    }

    /**
     * Downloads required file from transtats.bts.gov
     *
     * @param zipName - Name of required file.
     */
    private static void downloadZipFile(String zipName) {
        try {
            URL url = new URL("http://tsdata.bts.gov/" + zipName);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("content-type", "binary/data");
            InputStream in = conn.getInputStream();
            String absoluteFileName = projectDirectory + separator + "downloaded" + separator + zipName;
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

    private static String readConfig(int startIndex, int endIndex) {
        ArrayList<String> result = new ArrayList<>();
        String resultPart = "";

        // file, which contains the needed data, parameters, ... for sending a correct request
        File config = new File(projectDirectory + separator + "src" + separator + "filecrawler" + separator + "crawler_config.txt");

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

    private static void doRequestAndDownload(String post) {
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

    private static void getProjectDirectory() throws URISyntaxException {
        URI uri = FileCrawler.class.getResource("crawler_config.txt").toURI();
        projectDirectory = uri.toString();
        projectDirectory = projectDirectory.substring(6);
        int endIndex = projectDirectory.indexOf("build");
        projectDirectory = projectDirectory.substring(0, endIndex);
    }

    private static void unzipFile(String fileName) throws FileNotFoundException, IOException {
        String downloadPath = projectDirectory + separator + "downloaded" + separator;
        
        ZipInputStream zis = new ZipInputStream(new FileInputStream(downloadPath + fileName));
        ZipEntry entry = zis.getNextEntry();
        
        byte[] buffer = new byte[1024];
        
        String unzippedfileName = entry.getName();
        File newFile = new File(downloadPath + unzippedfileName);
        new File(newFile.getParent()).mkdirs();
        
        try (FileOutputStream fos = new FileOutputStream(newFile)) {
            int length;
            while((length = zis.read(buffer)) > 0){
                fos.write(buffer, 0, length);
            }
        }
    }
}
