package hm.edu.swe2.flysoft.parser.mappings;
import hm.edu.swe2.flysoft.parser.model.MethodDescriptor;
import java.util.Date;
import java.util.HashMap;

/**
 * Maping for the T100 Segement US Domestic table.
 * @author Philipp Chavaroche
 * @version 01.05.2016
 */
public final class SegmentDomesticMapTable extends AbstractMapTable {
    
    private SegmentDomesticMapTable(){
        super();
    }
    
    public static AbstractMapTable getInstance(){
        if(singletonMapTable== null){
            singletonMapTable = new SegmentDomesticMapTable();
        }
        return singletonMapTable;
    }
    
    /**
     * Initialize the map table.
     */
    @Override
    protected void initMap(){
        mapping = new HashMap<>();
        //          <CSV-Header>,<Method setter name>, <Parameter data type>
        mapping.put("PASSENGERS", new MethodDescriptor("setPassengerCount", int.class));
        mapping.put("AIRLINE_ID", new MethodDescriptor("setAirlineId", int.class));
        mapping.put("ORIGIN", new MethodDescriptor("setOriginAirportShortName", String.class));
        mapping.put("DEST", new MethodDescriptor("setDestAirportShortName", String.class));
        mapping.put("YEAR", new MethodDescriptor("setYear", int.class));      
        mapping.put("MONTH", new MethodDescriptor("setMonth", int.class));
    }
}
