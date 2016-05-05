
import hm.edu.swe2.flysoft.controller.EntityController;
import hm.edu.swe2.flysoft.entity.City;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Betina Hientz
 * @version 05.05.2016
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityController<City> controller = new EntityController();
        City city = new City();
        city.setCityId(2);
        city.setName("BlaBla");
        city.setShortnamestate("bla");
        controller.create(city);
    }

}
