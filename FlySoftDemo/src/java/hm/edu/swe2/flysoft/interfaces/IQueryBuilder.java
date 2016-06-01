package hm.edu.swe2.flysoft.interfaces;

import hm.edu.swe2.flysoft.ui.FilterSetting;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Represents the methods for a query builder.
 * @author Philipp Chavaroche
 * @version 05.05.2016 
 */
public interface IQueryBuilder {

    /**
     * Build a sql query via the given filter settings.
     * @param settings The filter settings that should be used to build the query.
     * @param em The entity manager that create the query object for the query.
     * @return The sql query that requests the data specified by
     *         the filter settings.
     */
    Query build(final FilterSetting settings, final EntityManager em);
}
