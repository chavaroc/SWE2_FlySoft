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
}
