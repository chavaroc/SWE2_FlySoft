/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hm.edu.swe2.flysoft.entity.querybuilder;

import hm.edu.swe2.flysoft.interfaces.IQueryBuilder;
import hm.edu.swe2.flysoft.ui.FilterSetting;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Philipp Chavaroche
 * @version 
 */
public class DelayQueryBuilder implements IQueryBuilder{

    @Override
    public Query build(FilterSetting settings, EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
