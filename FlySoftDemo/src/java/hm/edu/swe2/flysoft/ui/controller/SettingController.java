package hm.edu.swe2.flysoft.ui.controller;

import hm.edu.swe2.flysoft.ui.FilterSetting;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author Philipp Chavaroche
 * @version 11.05.2016
 */
@Controller
public class SettingController {
    
   @RequestMapping(value = "/setting", method = RequestMethod.GET)
   public String student(Model model) {
       
       // Link object to form
       FilterSetting setting = new FilterSetting();  
       model.addAttribute("settingForm", setting);
       
       // Fill lists
       model.addAttribute("xaxisList", UiDataSelection.getXAxisOptions());
       model.addAttribute("yaxisList", UiDataSelection.getYAxisOptions());
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
