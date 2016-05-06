/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hm.edu.swe2.flysoft.entity;

import hm.edu.swe2.flysoft.controller.AirportEntityController;
import hm.edu.swe2.flysoft.controller.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Zwen
 */
public class TestAirport {
    public TestAirport(){
        
    }
 
    @Test
    public void testCreateAirport() throws NonexistentEntityException{
        AirportEntityController controller = new AirportEntityController();
        Airport airport = new Airport("muc", 0);
        Airport airport2 = new Airport("jfk", 1);
        controller.create(airport);
        controller.create(airport2);
        assertTrue(2 == controller.findAirlineEntities().size());
        
        
        Airport airport3 = new Airport("tel", 2);
        controller.create(airport3);
        assertTrue(3 == controller.findAirlineEntities().size());
        Airport search = controller.findAirport("jfk");
        assertTrue(airport2.equals(search));
        
        controller.destroy("jfk");
        search = controller.findAirport("jfk");
        assertTrue(search == null);
        assertTrue(2 == controller.findAirlineEntities().size());               
    }   
}

