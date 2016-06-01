package hm.edu.swe2.flysoft.ui.controller;

import hm.edu.swe2.flysoft.entity.controller.CityEntityController;
import hm.edu.swe2.flysoft.entity.City;
import hm.edu.swe2.flysoft.ui.CityFilter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * A spring controller to handle HTTP requests to select destinations.
 * @author Philipp Chavaroche
 * @version 11.05.2016
 */
@Controller
public class SelectdestinationsController {
    
    /**
     * Refer to the side selectdestinations.jsp
     * @param model ignored.
     * @return The name of the new side, to which should delegated.
     */
   @RequestMapping(value = "/selectdestinations", method = RequestMethod.GET)
   public String refer(Model model) {
       return "selectdestinations";
   }
   
   /**
    * Get all city names from the database and send it back to the caller.
    * @param model The model, which should be filled with city names.
    * @return The new side, to which should delegated.
    */
   @RequestMapping(value = "/selectdestinations/cities", method = RequestMethod.GET)
   public String getCities(final Model model) {
       
       // Link object to form
       final CityFilter setting = new CityFilter();
       model.addAttribute("cityForm", setting);
       
       // Fill lists
       final CityEntityController cityEntityController = new CityEntityController();
       final List<City> cities = cityEntityController.findCityEntities();
       final List<String> cityNames = cities.stream()
           .map(city -> city.getName())
           .collect(Collectors.toList());
       model.addAttribute("cityNameList", cityNames);
       return "selectdestinations";
   }
}
