package hm.edu.swe2.flysoft.ui.controller;

import hm.edu.swe2.flysoft.entity.controller.CityEntityController;
import hm.edu.swe2.flysoft.entity.City;
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
public class SelectdestinationsController {
    
   @RequestMapping(value = "/selectdestinations", method = RequestMethod.GET)
   public String refer(Model model) {
       /*
       // Link object to form
       FilterSetting setting = new FilterSetting();  
       model.addAttribute("settingForm", setting);
       
       // Fill lists
       model.addAttribute("airlineList", DummyData.getAirlines());
       model.addAttribute("xaxisList", DummyData.getXAxisOptions());
       model.addAttribute("yaxisList", DummyData.getYAxisOptions());*/
       return "selectdestinations";
   }
   
   @RequestMapping(value = "/selectdestinations/cities", method = RequestMethod.GET)
   public String getCities(Model model) {
       
       // Link object to form
       CityFilter setting = new CityFilter();
       model.addAttribute("cityForm", setting);
       
       // Fill lists
       List<String> cityNames = new ArrayList<String>();
       CityEntityController cityEntityController = new CityEntityController();
       List<City> cities = cityEntityController.findCityEntities();
       for (City city : cities) {
           cityNames.add(city.getName());
       }
       //model.addAttribute("cityForm", cities);
       model.addAttribute("cityNameList", cityNames);
       return "selectdestinations"; // we load the web page "setting.jsp"
   }
}
