/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hm.edu.swe2.flysoft.ui.controller;

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

    public static List<String> getXAxisOptions() {
        if (xAxisOptions.size() <= 0) {
            xAxisOptions.add("Airline");
            xAxisOptions.add("Time");
            xAxisOptions.add("Destination");
        }
        return xAxisOptions;
    }

    public static List<String> getThirdDimensionOptions() {
        if (thirdDimensionOptions.size() <= 0) {
            thirdDimensionOptions.add("nothing selected");
            thirdDimensionOptions.add("Airline");
            thirdDimensionOptions.add("Time");
            thirdDimensionOptions.add("Destination");
        }
        return thirdDimensionOptions;
    }

    public static List<String> getTimeDimensionOptions() {
        if (timeDimensionOptions.size() <= 0) {
            timeDimensionOptions.add("Day");
            timeDimensionOptions.add("Weekday(s)");
            timeDimensionOptions.add("Week");
            timeDimensionOptions.add("Month");
            timeDimensionOptions.add("Year");
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

}
