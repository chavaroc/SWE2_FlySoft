
import hm.edu.swe2.flysoft.controller.AirlineEntityController;
import hm.edu.swe2.flysoft.entity.Airline;

/**
 * @author 
 */
public class TestCreateAirline {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AirlineEntityController controller = new AirlineEntityController();
        Airline airline = new Airline();
        airline.setAirlineId(2213);
        airline.setName("ABlaBla");
        airline.setShortname("bla");
        controller.create(airline);
    }
}
