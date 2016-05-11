package hm.edu.swe2.flysoft.entity;

import hm.edu.swe2.flysoft.FilterSetting;
import hm.edu.swe2.flysoft.controller.FilterController;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Philipp Chavaroche
 */
public class TestFilterController {
    
    public TestFilterController() {
    }
    
    @Test
    public void TestFlightsPerWeek() throws ParseException{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        FilterController controller = new FilterController();
        FilterSetting settings = new FilterSetting();
        settings.setXaxis("Frequencies");
        settings.setYaxis("Time");
        settings.setDateFrom(dateFormat.parse("2016-01-01"));
        settings.setDateTo(dateFormat.parse("2016-12-31"));
        List<Object[]> result = controller.processDataRequest(settings);
        result.stream()
            .forEach(entry -> 
            {
               if(entry.length >= 2){
                   System.out.println(entry[0] + " " +  entry[1]);
               }
            });
        assertEquals(result.get(0)[0], 0);
        assertEquals(result.get(0)[1], 23);
    }
}
