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
import static org.junit.Assert.assertTrue;

/**
 * Tests for the filter controller
 * @author Philipp Chavaroche
 */
public class FilterControllerTest {
    
    public FilterControllerTest() {
    }
    
    /**
     * Test the proof of concept.
     * @throws ParseException
     * @throws IOException 
     */
    @Test
    public void TestFlightsPerWeek() throws ParseException, IOException{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        QueryController controller = new QueryController();
        FilterSetting settings = new FilterSetting();
        settings.setYaxis("Frequencies");
        settings.setXaxis("Time");
        settings.setThirdDimension("Destination");
        settings.setTimeDimension("Week");
        settings.setTimeFrom(dateFormat.parse("2015-01-01"));
        settings.setTimeTo(dateFormat.parse("2015-12-31"));
        settings.setAirlines(new String[]{});
        settings.setDestinations(new String[]{"Las Vegas, NV"});
        List<Object[]> result = controller.processDataRequest(settings);
        result.stream()
            .forEach(entry -> 
            {
               if(entry.length >= 2){
                   System.out.println(entry[0] + " " +  entry[1]);
               }
            });
        assertTrue((int)result.get(0)[0] == 0);
        assertTrue((long)result.get(0)[1] == 38l);
        
        assertTrue((int)result.get(6)[0] == 6);
        assertTrue((long)result.get(6)[1] == 106l);
        
        assertTrue((int)result.get(41)[0] == 41);
        assertTrue((long)result.get(41)[1] == 127l);
        
        assertTrue((int)result.get(51)[0] == 51);
        assertTrue((long)result.get(51)[1] == 90l);
        
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(result);
        System.out.println(json);
    }
}
