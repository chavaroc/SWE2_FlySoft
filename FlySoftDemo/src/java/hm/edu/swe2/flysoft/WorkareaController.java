package hm.edu.swe2.flysoft;

import hm.edu.swe2.flysoft.controller.AirlineEntityController;
import hm.edu.swe2.flysoft.ui.FilterSetting;
import hm.edu.swe2.flysoft.controller.CityEntityController;
import hm.edu.swe2.flysoft.entity.Airline;
import hm.edu.swe2.flysoft.entity.City;
import hm.edu.swe2.flysoft.interfaces.IAirline;
import hm.edu.swe2.flysoft.ui.CityFilter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.ModelAndView;

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
   
   
   
   @RequestMapping(value = "/workarea", method = RequestMethod.POST)
   public String addStudent(@ModelAttribute("settingForm") FilterSetting setting, 
   ModelMap model) {
      model.addAttribute("xaxis", setting.getXaxis());
      model.addAttribute("yaxis", setting.getYaxis());
      model.addAttribute("arilines", String.join(",", setting.getAirlines()));
      return "result";
   }
}
