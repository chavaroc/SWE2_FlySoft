package hm.edu.swe2.flysoft.ui.controller;

import hm.edu.swe2.flysoft.entity.controller.AirlineEntityController;
import hm.edu.swe2.flysoft.ui.FilterSetting;
import hm.edu.swe2.flysoft.entity.controller.FilterController;
import hm.edu.swe2.flysoft.entity.Airline;
import hm.edu.swe2.flysoft.entity.City;
import hm.edu.swe2.flysoft.interfaces.IAirline;
import hm.edu.swe2.flysoft.ui.CityFilter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Philipp Chavaroche
 * @version 
 */
@Controller
public class WorkareaController {
    
   @RequestMapping(value = "/workarea", method = RequestMethod.GET)
   public String student(Model model) {
       
       // Link object to form
       FilterSetting setting = new FilterSetting();  
       model.addAttribute("settingForm", setting);
       
       // Fill lists
       model.addAttribute("xaxisList", DummyData.getXAxisOptions());
       model.addAttribute("yaxisList", DummyData.getYAxisOptions());
       model.addAttribute("thirdDimensionList", DummyData.getThirdDimensionOptions());
       model.addAttribute("timeDimensionList", DummyData.getTimeDimensionOptions());
       
       
       model.addAttribute("airlineForm", setting);
       List<String> airlineNames = new ArrayList<>();
       AirlineEntityController airlineEntityController = new AirlineEntityController();
       List<IAirline> airlines = airlineEntityController.findAirlineEntities();
       for(IAirline airline : airlines){
           airlineNames.add(airline.getName());
       }
       model.addAttribute("airlinenewNameList", airlineNames);
       return "workarea"; // we load the wep page "setting.jsp"
   }
   
   @RequestMapping(value = "/workarea/graphdata", method = RequestMethod.GET)
   public @ResponseBody String getGraphData(
        @RequestParam("xaxis") String xaxis
       ,@RequestParam("yaxis") String yaxis
       ,@RequestParam("timedim") String timedim
       ,@RequestParam("thirddim") String thirddim
       ,@RequestParam("destinations") String dest
        ) throws IOException {
       FilterController controller = new FilterController();
       FilterSetting setting = new FilterSetting();  
       // Hardcoded Workarround -> todo: Add Time range fields in gui 
       try{
           setting.setXaxis(xaxis);
           setting.setYaxis(yaxis);
           setting.setTimeDimension(timedim);
           setting.setThirdDimension(thirddim);
           //setting.setDestinations(dest);
           DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
           setting.setTimeFrom(dateFormat.parse("2015-01-01"));
           setting.setTimeTo(dateFormat.parse("2015-12-31"));
       }
       catch(Exception ex){
           System.out.println(ex);
       }
       ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
       List<Object[]> result = controller.processDataRequest(setting);
       return ow.writeValueAsString(result);       
   }
      
   
   @RequestMapping(value = "/workarea", method = RequestMethod.POST)
   public String addStudent(@ModelAttribute("settingForm") FilterSetting setting, 
   ModelMap model) {
      model.addAttribute("xaxis", setting.getXaxis());
      model.addAttribute("yaxis", setting.getYaxis());
      model.addAttribute("arilines", String.join(",", setting.getAirlines()));
      return "result";
   }
}
