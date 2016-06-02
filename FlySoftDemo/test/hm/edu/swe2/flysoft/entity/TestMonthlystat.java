package hm.edu.swe2.flysoft.entity;

import hm.edu.swe2.flysoft.entity.controller.MonthlyStatEntityController;
import hm.edu.swe2.flysoft.entity.exceptions.NonexistentEntityException;
import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Zwen
 */
public class TestMonthlystat {
    
    public TestMonthlystat(){
        
    }
    @Test
    public void TestCreateMonthlystate() throws NonexistentEntityException{
        MonthlyStatEntityController controller = new MonthlyStatEntityController();
        MonthlyStat monthlystat = new MonthlyStat(0);
        MonthlyStat monthlystat2 = new MonthlyStat(1);
        controller.create(monthlystat);
        controller.create(monthlystat2);
        assertTrue(2 == controller.findMonthlystatEntities().size());
        
        
        MonthlyStat monthlystat3 = new MonthlyStat(2);
        controller.create(monthlystat3);
        assertTrue(3 == controller.findMonthlystatEntities().size());
        Optional<MonthlyStat> search = controller.findMonthlyStat(3);
        assertTrue(search.equals(monthlystat2));
        
        controller.destroy(0);
        search = controller.findMonthlyStat(0);
        assertTrue(search == null);
        assertTrue(2 == controller.findMonthlystatEntities().size());
        controller.destroy(1);
        controller.destroy(2);
    }
    
}
