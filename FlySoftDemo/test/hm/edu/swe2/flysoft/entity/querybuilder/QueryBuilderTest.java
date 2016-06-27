package hm.edu.swe2.flysoft.entity.querybuilder;

import hm.edu.swe2.flysoft.entity.controller.QueryController;
import hm.edu.swe2.flysoft.ui.FilterSetting;
import static hm.edu.swe2.flysoft.util.GlobalSettings.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Philipp Chavaroche
 * @version 27.06.2016
 */
public class QueryBuilderTest {
    
    /**
     * Test the proof of concept.
     * @throws ParseException
     * @throws IOException 
     */
    @Test
    public void TestFreqTimeWeekDestination() throws ParseException, IOException{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        QueryController controller = new QueryController();
        FilterSetting settings = new FilterSetting();
        settings.setYaxis(FREQUENCIES);
        settings.setXaxis(TIME);
        settings.setThirdDimension(DESTINATION);
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
        assertTrue((long)result.get(0)[1] == 29);
        
        assertTrue((int)result.get(6)[0] == 6);
        assertTrue((long)result.get(6)[1] == 72);
        
        assertTrue((int)result.get(41)[0] == 41);
        assertTrue((long)result.get(41)[1] == 83);
        
        assertTrue((int)result.get(51)[0] == 51);
        assertTrue((long)result.get(51)[1] == 71);
        
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(result);
        System.out.println(json);
    }
    
    @Test
    public void TestFreqDestinationAirline() throws ParseException{
        final QueryController controller = new QueryController();
        final FilterSetting setting = new FilterSetting();
        setting.setYaxis(FREQUENCIES);
        setting.setXaxis(DESTINATION);
        setting.setThirdDimension(AIRLINE);
        setting.setTimeDimension("Day");
        setting.setAirlines(new String[]{"US Airways Inc.", "Orange Air"});
        setting.setDestinations(new String[]{"Washington, DC", "Orlando, FL"});
        List<Object[]> result = controller.processDataRequest(setting);
        assertTrue("There should be flights from NY to Washington DC", result.size() > 0);
    }
    
    @Test
    public void TestFreqAirlineTimeMonth() throws ParseException{
        final QueryController controller = new QueryController();
        final FilterSetting setting = new FilterSetting();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        setting.setYaxis(FREQUENCIES);
        setting.setXaxis(AIRLINE);
        setting.setThirdDimension(TIME);
        setting.setTimeDimension("Weekdays");
        setting.setTimeFrom(dateFormat.parse("2015-01-01"));
        setting.setTimeTo(dateFormat.parse("2015-06-31"));
        setting.setAirlines(new String[]{"US Airways Inc.", "Orange Air"});
        List<Object[]> result = controller.processDataRequest(setting);
        assertTrue("There should be flights from airline 'US Airways Inc' or 'Orange Air'", result.size() > 0);
    }
    
    @Test
    public void TestPassengerTimeDest() throws ParseException{
        final QueryController controller = new QueryController();
        final FilterSetting setting = new FilterSetting();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        setting.setYaxis(PASSENGER_COUNT);
        setting.setXaxis(TIME);
        setting.setThirdDimension(DESTINATION);
        setting.setTimeDimension("Month");
        setting.setTimeFrom(dateFormat.parse("2015-06-01"));
        setting.setTimeTo(dateFormat.parse("2015-12-31"));
        setting.setDestinations(new String[]{"Washington, DC", "Orlando, FL"});
        List<Object[]> result = controller.processDataRequest(setting);
        assertTrue("There should be passenger counts to destinations 'Washington, DC' or 'Orlando, FL'", result.size() > 0);
    }
    
    @Test
    public void TestPassengerTimeAirline() throws ParseException{
        final QueryController controller = new QueryController();
        final FilterSetting setting = new FilterSetting();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        setting.setYaxis(PASSENGER_COUNT);
        setting.setXaxis(TIME);
        setting.setThirdDimension(AIRLINE);
        setting.setTimeDimension("Month");
        setting.setTimeFrom(dateFormat.parse("2015-06-01"));
        setting.setTimeTo(dateFormat.parse("2015-12-31"));
        setting.setAirlines(new String[]{"US Airways Inc.", "JetBlue Airways"});
        List<Object[]> result = controller.processDataRequest(setting);
        assertTrue("There should be passenger counts from airline 'US Airways Inc' or 'JetBlue Airways'", result.size() > 0);
    }
    
    @Test
    public void TestPassengerAirline() throws ParseException{
        final QueryController controller = new QueryController();
        final FilterSetting setting = new FilterSetting();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        setting.setYaxis(PASSENGER_COUNT);
        setting.setXaxis(AIRLINE);
        setting.setAirlines(new String[]{"US Airways Inc.", "JetBlue Airways"});
        List<Object[]> result = controller.processDataRequest(setting);
        assertTrue("There should be passenger counts from airline 'US Airways Inc' or 'JetBlue Airways'", result.size() > 0);
    }
    
    @Test
    public void TestPassengerDestAirline() throws ParseException{
        final QueryController controller = new QueryController();
        final FilterSetting setting = new FilterSetting();
        setting.setYaxis(PASSENGER_COUNT);
        setting.setXaxis(DESTINATION);
        setting.setThirdDimension(AIRLINE);
        setting.setDestinations(new String[]{"Washington, DC", "Orlando, FL"});
        setting.setAirlines(new String[]{"US Airways Inc.", "JetBlue Airways"});
        List<Object[]> result = controller.processDataRequest(setting);
        assertTrue("There should be passenger counts from destinations 'Washington, DC' or 'Orlando, FL'", result.size() > 0);
    }
    
    @Test
    public void TestDelayFreqTimeAirline() throws ParseException{
        final QueryController controller = new QueryController();
        final FilterSetting setting = new FilterSetting();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        setting.setYaxis(DELAY_FREQ);
        setting.setXaxis(TIME);
        setting.setThirdDimension(AIRLINE);
        setting.setTimeDimension("Month");
        setting.setTimeFrom(dateFormat.parse("2015-06-01"));
        setting.setTimeTo(dateFormat.parse("2015-12-31"));
        setting.setAirlines(new String[]{"US Airways Inc.", "JetBlue Airways"});
        List<Object[]> result = controller.processDataRequest(setting);
        assertTrue("There should be delay frequencies from airline 'US Airways Inc' or 'JetBlue Airways'", result.size() > 0);
    }
    
    @Test
    public void TestDelayFreqTimeDest() throws ParseException{
        final QueryController controller = new QueryController();
        final FilterSetting setting = new FilterSetting();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        setting.setYaxis(DELAY_FREQ);
        setting.setXaxis(TIME);
        setting.setThirdDimension(DESTINATION);
        setting.setTimeDimension("Month");
        setting.setTimeFrom(dateFormat.parse("2015-06-01"));
        setting.setTimeTo(dateFormat.parse("2015-12-31"));
        setting.setDestinations(new String[]{"Washington, DC", "Orlando, FL"});
        List<Object[]> result = controller.processDataRequest(setting);
        assertTrue("There should be delay frequencies to destinations 'Washington, DC' or 'Orlando, FL'", result.size() > 0);
    }
    
    @Test
    public void TestDelayFreqDestTime() throws ParseException{
        final QueryController controller = new QueryController();
        final FilterSetting setting = new FilterSetting();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        setting.setYaxis(DELAY_FREQ);
        setting.setXaxis(DESTINATION);
        setting.setThirdDimension(TIME);
        setting.setTimeDimension("Month");
        setting.setTimeFrom(dateFormat.parse("2015-06-01"));
        setting.setTimeTo(dateFormat.parse("2015-12-31"));
        setting.setDestinations(new String[]{"Washington, DC", "Orlando, FL"});
        List<Object[]> result = controller.processDataRequest(setting);
        assertTrue("There should be delay frequencies to destinations 'Washington, DC' or 'Orlando, FL'", result.size() > 0);
    }
    
    @Test
    public void TestDelayDurationAirline() throws ParseException{
        final QueryController controller = new QueryController();
        final FilterSetting setting = new FilterSetting();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        setting.setYaxis(DELAY_DURATION);
        setting.setXaxis(AIRLINE);
        setting.setThirdDimension("Nothing selected");
        setting.setAirlines(new String[]{"US Airways Inc.", "JetBlue Airways"});
        List<Object[]> result = controller.processDataRequest(setting);
        assertTrue("There should be delay durations from airline 'US Airways Inc' or 'JetBlue Airways'", result.size() > 0);
    }
    
    @Test
    public void TestDelayDurationAirlineDest() throws ParseException{
        final QueryController controller = new QueryController();
        final FilterSetting setting = new FilterSetting();
        setting.setYaxis(DELAY_DURATION);
        setting.setXaxis(AIRLINE);
        setting.setThirdDimension(DESTINATION);        
        setting.setAirlines(new String[]{"US Airways Inc.", "JetBlue Airways"});
        setting.setDestinations(new String[]{"Washington, DC", "Orlando, FL"});
        List<Object[]> result = controller.processDataRequest(setting);
        assertTrue("There should be delay durations to airlines 'US Airways Inc.' or 'JetBlue Airways'", result.size() > 0);
    }
    
    @Test
    public void TestDelayDurationDest() throws ParseException{
        final QueryController controller = new QueryController();
        final FilterSetting setting = new FilterSetting();
        setting.setYaxis(DELAY_DURATION);
        setting.setXaxis(DESTINATION);
        setting.setThirdDimension("Nothing selected");
        setting.setDestinations(new String[]{"Washington, DC", "Orlando, FL"});
        List<Object[]> result = controller.processDataRequest(setting);
        assertTrue("There should be delay duration for destinations 'Washington, DC' or 'Orlando, FL'", result.size() > 0);
    }
    
    @Test
    public void TestCancelTimeAirline() throws ParseException{
        final QueryController controller = new QueryController();
        final FilterSetting setting = new FilterSetting();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        setting.setYaxis(CANCELLATIONS);
        setting.setXaxis(TIME);
        setting.setThirdDimension(AIRLINE);
        setting.setTimeDimension("Month");
        setting.setTimeFrom(dateFormat.parse("2015-06-01"));
        setting.setTimeTo(dateFormat.parse("2015-12-31"));
        setting.setAirlines(new String[]{"US Airways Inc.", "JetBlue Airways"});
        List<Object[]> result = controller.processDataRequest(setting);
        assertTrue("There should be passenger counts from airline 'US Airways Inc' or 'JetBlue Airways'", result.size() > 0);
    }
    
    @Test
    public void TestCancelAirlineDest() throws ParseException{
        final QueryController controller = new QueryController();
        final FilterSetting setting = new FilterSetting();
        setting.setYaxis(CANCELLATIONS);
        setting.setXaxis(AIRLINE);
        setting.setThirdDimension(DESTINATION);
        setting.setAirlines(new String[]{"US Airways Inc.", "JetBlue Airways"});
        setting.setDestinations(new String[]{"Washington, DC", "Orlando, FL"});
        List<Object[]> result = controller.processDataRequest(setting);
        assertTrue("There should be passenger counts to destinations 'Washington, DC' or 'Orlando, FL'", result.size() > 0);
    }
    
    @Test
    public void TestCancelDest() throws ParseException{
        final QueryController controller = new QueryController();
        final FilterSetting setting = new FilterSetting();
        setting.setYaxis(CANCELLATIONS);
        setting.setXaxis(DESTINATION);
        setting.setThirdDimension("nohting selected");
        setting.setDestinations(new String[]{"Washington, DC", "Orlando, FL"});
        List<Object[]> result = controller.processDataRequest(setting);
        assertTrue("There should be passenger counts to destinations 'Washington, DC' or 'Orlando, FL'", result.size() > 0);
    }
    
     @Test(expected = UnsupportedOperationException.class)
    public void TestUnknownYaxis(){
        final QueryController controller = new QueryController();
        final FilterSetting setting = new FilterSetting();
        setting.setYaxis("unknown");
        setting.setXaxis(DESTINATION);
        setting.setThirdDimension("nohting selected");
        setting.setDestinations(new String[]{"Washington, DC", "Orlando, FL"});
        controller.processDataRequest(setting);
    }
    
    
}
