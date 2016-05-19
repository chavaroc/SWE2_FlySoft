package hm.edu.swe2.flysoft.entity.controller;

import hm.edu.swe2.flysoft.entity.querybuilder.DelayDurationQueryBuilder;
import hm.edu.swe2.flysoft.entity.querybuilder.FrequencyQueryBuilder;
import hm.edu.swe2.flysoft.ui.FilterSetting;
import static hm.edu.swe2.flysoft.util.GlobalSettings.*;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * The job of the QueryBuilder class is to convert given filter settings
 * into a sql query, that requests the matching data.
 * @author Philipp Chavaroche
 * @version 
 */
public class QueryController extends AbstractEntityController {

    /**
     * Build a sql query, to request the data descript by the given settings.
     * @param settings The current filter settings.
     * @return The query to request the data, descriped by the filter settings.
     */
    private Query buildQuery(final FilterSetting settings){
        final EntityManager em = getEntityManager();
        Query query = null;
        // evaulate x-axis
        if(FREQUENCIES.equalsIgnoreCase(settings.getYaxis())){
            query = new FrequencyQueryBuilder().build(settings, em);
        }
        else if(DELAY_DURATION.equalsIgnoreCase(settings.getYaxis())){
            query = new DelayDurationQueryBuilder().build(settings, em);
        }
        else{
            throw new UnsupportedOperationException("Not supported yet.");
        }   
        return query;
    }
    
    /**
     * Request data for the given filter settings.
     * @param filter The filter, that defines the data scope.
     * @return The data for the given filter.
     */
    public List<Object[]> processDataRequest(final FilterSetting filter){
        
        final Query query = buildQuery(filter);
        return query.getResultList();
    }
}
