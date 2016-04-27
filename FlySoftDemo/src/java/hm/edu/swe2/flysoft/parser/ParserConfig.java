/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hm.edu.swe2.flysoft.parser;
import hm.edu.swe2.flysoft.parser.model.MethodDescriptor;
import hm.edu.swe2.flysoft.parser.model.interfaces.ICsvFieldMapping;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Philipp Chavaroche
 * @version 
 */
public class ParserConfig implements ICsvFieldMapping {
    
    private static ParserConfig singletonConfig;
    
    private Map<String,MethodDescriptor> mapping;
    
    private ParserConfig(){
        initMap();
    }
    
    public static ParserConfig getInstance(){
        if(singletonConfig == null){
            singletonConfig = new ParserConfig();
        }
        return singletonConfig;
    }
    
    @Override
    public Map<String,MethodDescriptor> getMapping(){
        return Collections.unmodifiableMap(mapping);
    }
    
    private void initMap(){
        mapping = new HashMap<String,MethodDescriptor>();
        //          <CSV-Header>,<PropertySetName>
        mapping.put("FL_DATE", new MethodDescriptor("setFlightDate", Date.class));
        mapping.put("FL_NUM", new MethodDescriptor("setFlightNumber", int.class));
        mapping.put("AIRLINE_ID", new MethodDescriptor("setAirlineId", int.class));
        mapping.put("UNIQUE_CARRIER", new MethodDescriptor("setUniqueCarrierName", String.class));
        
        mapping.put("ORIGIN_AIRPORT_ID", new MethodDescriptor("setOriginAirportId", int.class));
        mapping.put("ORIGIN", new MethodDescriptor("setOriginAirportShortName", String.class));
        mapping.put("ORIGIN_CITY_MARKET_ID", new MethodDescriptor("setOriginCityId", int.class));
        mapping.put("ORIGIN_CITY_NAME", new MethodDescriptor("setOriginCityName", String.class));
        mapping.put("ORIGIN_STATE_NM", new MethodDescriptor("setOriginStateName", String.class));
        mapping.put("ORIGIN_STATE_ABR", new MethodDescriptor("setOriginStateShortName", String.class));
        mapping.put("DEP_DELAY_NEW", new MethodDescriptor("setDepatureDelay", double.class));
        
        mapping.put("DEST_AIRPORT_ID", new MethodDescriptor("setDestAirportId", int.class));
        mapping.put("DEST", new MethodDescriptor("setDestAirportShortName", String.class));
        mapping.put("DEST_CITY_MARKET_ID", new MethodDescriptor("setDestCityId", int.class));
        mapping.put("DEST_CITY_NAME", new MethodDescriptor("setOriginCityName", String.class));
        mapping.put("DEST_STATE_NM", new MethodDescriptor("setDestStateName", String.class));
        mapping.put("DEST_STATE_ABR", new MethodDescriptor("setDestStateShortName", String.class));
        mapping.put("ARR_DELAY_NEW", new MethodDescriptor("setArrivalDelay", double.class));
        
        mapping.put("CANCELLED", new MethodDescriptor("setCancelled", boolean.class));        
    }
    

}
