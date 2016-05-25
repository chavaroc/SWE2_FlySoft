package hm.edu.swe2.flysoft.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * TODO necessary?
 *  A spring controller to handle HTTP requests to select origions.
 * @author Philipp Chavaroche
 * @version 11.05.2016
 */
@Controller
public class SelectoriginsController {
    
   @RequestMapping(value = "/selectorigins", method = RequestMethod.GET)
   public String refer(Model model) {
       /*
       // Link object to form
       FilterSetting setting = new FilterSetting();  
       model.addAttribute("settingForm", setting);
       
       // Fill lists
       model.addAttribute("airlineList", DummyData.getAirlines());
       model.addAttribute("xaxisList", DummyData.getXAxisOptions());
       model.addAttribute("yaxisList", DummyData.getYAxisOptions());*/
       return "selectorigins"; // we load the wep page "setting.jsp"
   }
}
