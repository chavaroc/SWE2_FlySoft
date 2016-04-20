/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hm.edu.swe2.flysoft;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Philipp Chavaroche
 * @version 
 */
public class DummyData {
    
    private static List<String> xAxisOptions = new ArrayList<String>();
    private static List<String> yAxisOptions = new ArrayList<String>();
    private static List<String> airlines = new ArrayList<String>();
    
    public static List<String> getXAxisOptions(){
        if(xAxisOptions.size() <= 0){
            xAxisOptions.add("Time");
            xAxisOptions.add("Weekday");
            xAxisOptions.add("Origin");
            xAxisOptions.add("Destination");
        }
        return xAxisOptions;
    }
    
    public static List<String> getYAxisOptions(){
        if(yAxisOptions.size() <= 0){
            yAxisOptions.add("Frequencies");
            yAxisOptions.add("Count of passenger");
            yAxisOptions.add("Delay frequencies");
            yAxisOptions.add("Cancellations");
        }
        return yAxisOptions;
    }
    
    public static String[] getAirlines(){
        if(airlines.size() <= 0){
            airlines.add("Lufthansa");
            airlines.add("Airberlin");
            airlines.add("EasyJet");
        }
        return airlines.toArray(new String[0]);
    }

}
