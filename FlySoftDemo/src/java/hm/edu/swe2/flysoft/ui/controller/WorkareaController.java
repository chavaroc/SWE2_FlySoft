package hm.edu.swe2.flysoft.ui.controller;

import hm.edu.swe2.flysoft.entity.controller.AirlineEntityController;
import hm.edu.swe2.flysoft.entity.controller.CityEntityController;
import hm.edu.swe2.flysoft.ui.FilterSetting;
import hm.edu.swe2.flysoft.entity.controller.QueryController;
import hm.edu.swe2.flysoft.interfaces.IAirline;
import hm.edu.swe2.flysoft.interfaces.ICity;
import hm.edu.swe2.flysoft.ui.CityFilter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * A spring controller to handle HTTP requests for the work area (main page).
 *
 * @author Philipp Chavaroche
 * @version 11.05.2016
 */
@Controller
public class WorkareaController {

    /**
     * Initialize the side workarea and load possible filter options.
     *
     * @param model The model that will be filled with valid options.
     * @return The name of the new side, to which should delegated.
     */
    @RequestMapping(value = "/workarea", method = RequestMethod.GET)
    public String init(Model model) {
        // Link object to form
        FilterSetting setting = new FilterSetting();
        model.addAttribute("settingForm", setting);

        // Fill lists
        model.addAttribute("xaxisList", UiDataSelection.getXAxisOptions());
        model.addAttribute("yaxisList", UiDataSelection.getYAxisOptions());
        model.addAttribute("thirdDimensionList", UiDataSelection.getThirdDimensionOptions());
        model.addAttribute("timeDimensionList", UiDataSelection.getTimeDimensionOptions());

        model.addAttribute("airlineForm", setting);
        List<String> airlineNames = new ArrayList<>();
        AirlineEntityController airlineEntityController = new AirlineEntityController();
        List<IAirline> airlines = airlineEntityController.findAirlineEntities();

        Collections.sort(airlines, (Object arg0, Object arg1) -> ((IAirline) arg0)
                .getName().compareTo(((IAirline) arg1).getName()));

        for (IAirline airline : airlines) {
            airlineNames.add(airline.getName());
        }
        //airlineNames.s
        model.addAttribute("airlinenewNameList", airlineNames);

        // Link object to form
        final CityFilter setting2 = new CityFilter();
        model.addAttribute("cityForm", setting2);

        // Fill lists
        final CityEntityController cityEntityController = new CityEntityController();
        final List<ICity> cities = cityEntityController.findCityEntities();

        Collections.sort(cities, (Object arg0, Object arg1) -> ((ICity) arg0)
                .getName().compareTo(((ICity) arg1).getName()));

        final List<String> cityNames = cities.stream()
                .map(city -> city.getName())
                .collect(Collectors.toList());
        model.addAttribute("cityNameList", cityNames);

        // we load the wep page "workarea.jsp"
        return "workarea";
    }

    /**
     * Request the graph data for the given setting.
     *
     * @param xaxis The choosen x-axis.
     * @param yaxis The choosen y-axis.
     * @param thirddim The choosen third dimension.
     * @param timedim The choosen time dimension.
     * @param airlines The choosen airlines.
     * @param dest The choosen destination.
     * @param timerange the time range for the request.
     * @return The response body in form of json string.
     * @throws IOException In case the result of the db, could not be converted
     * to json.
     */
    @RequestMapping(value = "/workarea/graphdata", method = RequestMethod.GET)
    public @ResponseBody
    String getGraphData(
            @RequestParam("xaxis") String xaxis, @RequestParam("yaxis") String yaxis, @RequestParam("thirddim") String thirddim, @RequestParam(value = "timedim", required = false) String timedim, @RequestParam(value = "airlines[]", required = false) String[] airlines, @RequestParam(value = "destinations[]", required = false) String[] dest, @RequestParam(value = "timerange[]", required = false) String[] timerange
    ) throws IOException {
        QueryController controller = new QueryController();
        FilterSetting setting = new FilterSetting();
        try {
            setting.setXaxis(xaxis);
            setting.setYaxis(yaxis);
            setting.setTimeDimension(timedim);
            setting.setThirdDimension(thirddim);
            setting.setDestinations(dest == null ? new String[]{} : dest);
            setting.setAirlines((airlines == null ? new String[]{} : airlines));
            setting.setOrigins(new String[]{});
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.US);
            if (timerange.length > 1) {
                setting.setTimeFrom(dateFormat.parse(timerange[0]));
                setting.setTimeTo(dateFormat.parse(timerange[1]));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        // Request data from database.
        List<Object[]> result = controller.processDataRequest(setting);
        // convert it into json and return it.
        return ow.writeValueAsString(result);
    }
}
