package hm.edu.swe2.flysoft.parser;
import hm.edu.swe2.flysoft.parser.model.MethodDescriptor;
import hm.edu.swe2.flysoft.parser.model.interfaces.ICsvFieldMapping;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Defines a mappintg configuration between CSV columns and Java setter methods.
 * @author Philipp Chavaroche
 * @version 29.04.2016
 */
public class MapTable implements ICsvFieldMapping {
    
    private static MapTable singletonConfig;
    
    private Map<String,MethodDescriptor> mapping;
    
    private MapTable(){
        initMap();
    }
    
    public static MapTable getInstance(){
        if(singletonConfig == null){
            singletonConfig = new MapTable();
        }
        return singletonConfig;
    }
    
    @Override
    public Map<String,MethodDescriptor> getMapping(){
        return Collections.unmodifiableMap(mapping);
    }
    
    /**
     * Initialize the map table.
     */
    private void initMap(){
        mapping = new HashMap<String,MethodDescriptor>();
        //          <CSV-Header>,<Method setter name>, <Parameter data type>
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
        mapping.put("DEP_TIME", new MethodDescriptor("setDepartureTime", int.class));
        mapping.put("DEP_DELAY_NEW", new MethodDescriptor("setDepartureDelay", double.class));
        
        mapping.put("DEST_AIRPORT_ID", new MethodDescriptor("setDestAirportId", int.class));
        mapping.put("DEST", new MethodDescriptor("setDestAirportShortName", String.class));
        mapping.put("DEST_CITY_MARKET_ID", new MethodDescriptor("setDestCityId", int.class));
        mapping.put("DEST_CITY_NAME", new MethodDescriptor("setDestCityName", String.class));
        mapping.put("DEST_STATE_NM", new MethodDescriptor("setDestStateName", String.class));
        mapping.put("DEST_STATE_ABR", new MethodDescriptor("setDestStateShortName", String.class));
        mapping.put("ARR_TIME", new MethodDescriptor("setArrivalTime", int.class));
        mapping.put("ARR_DELAY_NEW", new MethodDescriptor("setArrivalDelay", double.class));
        
        mapping.put("CANCELLED", new MethodDescriptor("setCancelled", boolean.class));        
    }
}
