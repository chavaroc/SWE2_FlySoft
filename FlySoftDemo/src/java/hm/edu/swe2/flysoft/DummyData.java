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
    private static List<String> thirdDimensionOptions = new ArrayList<String>();
    private static List<String> timeDimensionOptions = new ArrayList<>();
    //TimeRange muss auch noch einstellbar sein --> braucht man hier was?
    private static List<String> destinationOptions = new ArrayList<>();
    private static List<String> airlines = new ArrayList<String>();

    public static List<String> getXAxisOptions() {
        if (xAxisOptions.size() <= 0) {
            xAxisOptions.add("Airlineschmarrn");
            xAxisOptions.add("Time");
            xAxisOptions.add("Destination");
            xAxisOptions.add("Origin");
        }
        return xAxisOptions;
    }

    public static List<String> getThirdDimensionOptions() {
        if (thirdDimensionOptions.size() <= 0) {
            thirdDimensionOptions.add("Airline3D");
            thirdDimensionOptions.add("Time");
            thirdDimensionOptions.add("Destination");
            thirdDimensionOptions.add("Origin");
        }
        return thirdDimensionOptions;
    }

    public static List<String> getTimeDimensionOptions() {
        if (timeDimensionOptions.size() <= 0) {
            timeDimensionOptions.add("Day");
            timeDimensionOptions.add("Week");
            timeDimensionOptions.add("Month");
        }
        return timeDimensionOptions;
    }

    public static List<String> getYAxisOptions() {
        if (yAxisOptions.size() <= 0) {
            yAxisOptions.add("Frequencies");
            yAxisOptions.add("Count of passengers");
            yAxisOptions.add("Delay frequencies");
            yAxisOptions.add("Delay durations");
            yAxisOptions.add("Cancellations");
        }
        return yAxisOptions;
    }

    public static String[] getAirlines() {
        if (airlines.size() <= 0) {
            airlines.add("Lufthansa");
            airlines.add("Airberlin");
            airlines.add("EasyJet");
        }
        return airlines.toArray(new String[0]);
    }

}
