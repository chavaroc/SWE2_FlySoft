/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hm.edu.swe2.flysoft.entity.querybuilder;

import static hm.edu.swe2.flysoft.entity.querybuilder.AbstractQueryBuilder.validTimeDimensions;
import hm.edu.swe2.flysoft.interfaces.IQueryBuilder;
import hm.edu.swe2.flysoft.ui.FilterSetting;
import static hm.edu.swe2.flysoft.util.GlobalSettings.AIRLINE;
import static hm.edu.swe2.flysoft.util.GlobalSettings.DESTINATION;
import static hm.edu.swe2.flysoft.util.GlobalSettings.ORIGIN;
import static hm.edu.swe2.flysoft.util.GlobalSettings.TIME;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Build all querys, that need delay at the y-axis.
 * @author Philipp Chavaroche
 * @version 18.05.2016
 */
public class DelayQueryBuilder implements IQueryBuilder{

    @Override
    public Query build(FilterSetting settings, EntityManager em) {
        Query query = null;
        String selectToken;
        String whereToken;
        // Check which x-axis is given
        if(TIME.equalsIgnoreCase(settings.getXaxis())){
            
        }
        else if (AIRLINE.equalsIgnoreCase(settings.getXaxis())){
           
        }
        else if (DESTINATION.equalsIgnoreCase(settings.getXaxis())){
            
        }
        else if (ORIGIN.equalsIgnoreCase(settings.getXaxis())){
           
        }
        else{
            throw new UnsupportedOperationException("Not supported yet.");
        }   
        return query;
    }

}
