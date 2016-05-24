package hm.edu.swe2.flysoft.interfaces;

import hm.edu.swe2.flysoft.ui.FilterSetting;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Philipp Chavaroche
 * @version 
 */
public interface IQueryBuilder {

    Query build(final FilterSetting settings, final EntityManager em);
}
