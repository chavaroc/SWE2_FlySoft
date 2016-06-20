package hm.edu.swe2.flysoft.entity;

import hm.edu.swe2.flysoft.entity.controller.MonthlyStatEntityController;
import hm.edu.swe2.flysoft.entity.exceptions.NonexistentEntityException;
import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the monthly stat entity
 * @author Zwen
 */
public class MonthlystatTest {
    
    public MonthlystatTest(){
        
    }
    @Test
    public void TestCreateMonthlystate() throws NonexistentEntityException{
        MonthlyStatEntityController controller = new MonthlyStatEntityController();
        // Attension, ID is an auto increment field in db.
        MonthlyStat monthlystat = new MonthlyStat(0);
        MonthlyStat monthlystat2 = new MonthlyStat(1);
        int previousSize = controller.findMonthlystatEntities().size();
        controller.create(monthlystat);
        controller.create(monthlystat2);
        assertTrue(previousSize + 2 == controller.findMonthlystatEntities().size());
        
        
        MonthlyStat monthlystat3 = new MonthlyStat(2);
        controller.create(monthlystat3);
        assertTrue(previousSize + 3 == controller.findMonthlystatEntities().size());
        Optional<MonthlyStat> search = controller.findMonthlyStat(monthlystat3.getMonthlyStatId());
        assertTrue(search.isPresent());
        assertTrue(search.get().equals(monthlystat3));
        
        controller.destroy(monthlystat.getMonthlyStatId());
        search = controller.findMonthlyStat(monthlystat.getMonthlyStatId());
        assertTrue(!search.isPresent());
        assertTrue(previousSize + 2 == controller.findMonthlystatEntities().size());
        controller.destroy(monthlystat2.getMonthlyStatId());
        controller.destroy(monthlystat3.getMonthlyStatId());
    }
    
}
