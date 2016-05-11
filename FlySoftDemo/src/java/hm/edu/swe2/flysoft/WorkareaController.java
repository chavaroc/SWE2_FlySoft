package hm.edu.swe2.flysoft;

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
       model.addAttribute("airlineList", DummyData.getAirlines());
       model.addAttribute("xaxisList", DummyData.getXAxisOptions());
       model.addAttribute("yaxisList", DummyData.getYAxisOptions());
       model.addAttribute("thirdDimensionList", DummyData.getThirdDimensionOptions());
       model.addAttribute("timeDimensionList", DummyData.getTimeDimensionOptions());
       return "workarea"; // we load the wep page "setting.jsp"
   }
   
   @RequestMapping(value = "/workarea/cities", method = RequestMethod.GET)
   public String getCities(Model model) {
       
       // Link object to form
       FilterSetting setting = new FilterSetting();  
       model.addAttribute("cityForm", setting);
       
       // Fill lists
       String[] cities;
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
