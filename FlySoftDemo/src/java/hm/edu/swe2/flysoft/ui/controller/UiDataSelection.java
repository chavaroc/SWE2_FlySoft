
package hm.edu.swe2.flysoft.ui.controller;

import hm.edu.swe2.flysoft.util.GlobalSettings;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides data that can be selected in the ui.
 * @author Philipp Chavaroche
 * @version 11.05.2016
 */
public class UiDataSelection {

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
            //thirdDimensionOptions.add("Time");
            thirdDimensionOptions.add("Destination");
        }
        return thirdDimensionOptions;
    }

    public static List<String> getTimeDimensionOptions() {
        if (timeDimensionOptions.size() <= 0) {
            timeDimensionOptions.add(GlobalSettings.TIME_DIM_DAY);
            timeDimensionOptions.add(GlobalSettings.TIME_DIM_WEEKDAYS);
            timeDimensionOptions.add(GlobalSettings.TIME_DIM_WEEK);
            timeDimensionOptions.add(GlobalSettings.TIME_DIM_MONTH);
            timeDimensionOptions.add(GlobalSettings.TIME_DIM_YEAR);
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
