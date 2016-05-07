/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hm.edu.swe2.flysoft.entity;

import hm.edu.swe2.flysoft.controller.MonthlystatEntityController;
import hm.edu.swe2.flysoft.controller.exceptions.NonexistentEntityException;
import java.util.Optional;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Zwen
 */
public class TestMonthlystat {
    
    public TestMonthlystat(){
        
    }
    @Test
    public void TestCreateMonthlystate() throws NonexistentEntityException{
        MonthlystatEntityController controller = new MonthlystatEntityController();
        Monthlystat monthlystat = new Monthlystat(0);
        Monthlystat monthlystat2 = new Monthlystat(1);
        controller.create(monthlystat);
        controller.create(monthlystat2);
        assertTrue(2 == controller.findMmonthlystatEntities().size());
        
        
        Monthlystat monthlystat3 = new Monthlystat(2);
        controller.create(monthlystat3);
        assertTrue(3 == controller.findMmonthlystatEntities().size());
        Optional<Monthlystat> search = controller.findMonthlystat(3);
        assertTrue(search.equals(monthlystat2));
        
        controller.destroy(0);
        search = controller.findMonthlystat(0);
        assertTrue(search == null);
        assertTrue(2 == controller.findMmonthlystatEntities().size());
    }
    
}
