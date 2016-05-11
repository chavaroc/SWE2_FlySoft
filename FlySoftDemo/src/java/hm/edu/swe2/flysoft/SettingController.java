package hm.edu.swe2.flysoft;

import hm.edu.swe2.flysoft.ui.FilterSetting;
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
public class SettingController {
    
   @RequestMapping(value = "/setting", method = RequestMethod.GET)
   public String student(Model model) {
       
       // Link object to form
       FilterSetting setting = new FilterSetting();  
       model.addAttribute("settingForm", setting);
       
       // Fill lists
       model.addAttribute("airlineList", DummyData.getAirlines());
       model.addAttribute("xaxisList", DummyData.getXAxisOptions());
       model.addAttribute("yaxisList", DummyData.getYAxisOptions());
       return "setting"; // we load the wep page "setting.jsp"
   }
   
   @RequestMapping(value = "/setting", method = RequestMethod.POST)
   public String addStudent(@ModelAttribute("settingForm") FilterSetting setting, 
   ModelMap model) {
      model.addAttribute("xaxis", setting.getXaxis());
      model.addAttribute("yaxis", setting.getYaxis());
      model.addAttribute("arilines", String.join(",", setting.getAirlines()));
      return "result";
   }
}
