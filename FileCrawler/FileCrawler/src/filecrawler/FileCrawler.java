package filecrawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Downloads required Data (as zip) from transtats.bts.gov
 *
 * @author Markus Huber
 * @version 25-04-16 modified_last: Markus Huber
 */
public class FileCrawler {

    public static void main(String[] args) {
        String postRequest;

        // 0 and 43 for Table --> Market
        // postRequest = readConfig(45, 97);
        postRequest = readConfig(0, 43);

        // try to connect to the server and send post-request
        doRequestAndDownload(postRequest);
        
        
        postRequest = readConfig(45, 144);
        doRequestAndDownload(postRequest);
        
        
        postRequest = readConfig(147, 196);
        doRequestAndDownload(postRequest);
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
            FileOutputStream out = new FileOutputStream("C:\\Users\\xYrs\\Documents\\Hm\\6_Semester\\SWE2\\FlySoft_Git\\FileCrawler\\FileCrawler\\downloaded\\" + zipName);

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
        File config = new File("C:\\Users\\xYrs\\Documents\\Hm\\6_Semester\\SWE2\\FlySoft_Git\\FileCrawler\\FileCrawler\\src\\filecrawler\\crawler_config.txt");

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
        
        for(int i = startIndex; i <= endIndex; i++){
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

        } catch (IOException ex) {
            Logger.getLogger(FileCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
