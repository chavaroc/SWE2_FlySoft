package hm.edu.swe2.flysoft.entity;

import hm.edu.swe2.flysoft.ui.FilterSetting;
import hm.edu.swe2.flysoft.entity.controller.QueryController;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import org.junit.Test;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

/**
 * Tests for the filter controller
 * @author Philipp Chavaroche
 */
public class TestFilterController {
    
    public TestFilterController() {
    }
    
    @Test
    public void TestFlightsPerWeek() throws ParseException, IOException{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        QueryController controller = new QueryController();
        FilterSetting settings = new FilterSetting();
        settings.setYaxis("Frequencies");
        settings.setXaxis("Time");
        settings.setTimeDimension("Week");
        settings.setTimeFrom(dateFormat.parse("2016-01-01"));
        settings.setTimeTo(dateFormat.parse("2016-12-31"));
        List<Object[]> result = controller.processDataRequest(settings);
        result.stream()
            .forEach(entry -> 
            {
               if(entry.length >= 2){
                   System.out.println(entry[0] + " " +  entry[1]);
               }
            });
        //assertEquals(result.get(0)[0], 0);
        //assertEquals(result.get(0)[1], 23);
        
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(result);
        System.out.println(json);
    }
}
