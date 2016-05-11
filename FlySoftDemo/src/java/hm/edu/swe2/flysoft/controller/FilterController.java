package hm.edu.swe2.flysoft.controller;

import hm.edu.swe2.flysoft.ui.FilterSetting;
import static hm.edu.swe2.flysoft.util.GlobalSettings.DB_PROD_FLIGHT_COUNT_WEEK;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TemporalType;

/**
 *
 * @author Philipp Chavaroche
 * @version 
 */
public class FilterController extends AbstractEntityController{
    
    public List<Object[]> processDataRequest(FilterSetting filter){
        EntityManager em = getEntityManager();

        // TODO find a better way as compare strings?
      //  if("Frequencies".equals(filter.getYaxis()) && "Time".equals(filter.getXaxis())
      //  && "Week".equals(filter.getTimeDimension()))
      {
            StoredProcedureQuery query = em.createStoredProcedureQuery(DB_PROD_FLIGHT_COUNT_WEEK);
            query.registerStoredProcedureParameter(1, Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);

            query.setParameter(1, filter.getTimeFrom(), TemporalType.DATE);
            query.setParameter(2, filter.getTimeTo(), TemporalType.DATE);
            query.setParameter(3, "Las Vegas, NV"); // TODO no hardcoded values!
            query.execute();
            return query.getResultList();
        }
    }
}
