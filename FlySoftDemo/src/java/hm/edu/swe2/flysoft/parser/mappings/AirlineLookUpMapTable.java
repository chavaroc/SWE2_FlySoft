package hm.edu.swe2.flysoft.parser.mappings;
import hm.edu.swe2.flysoft.parser.model.MethodDescriptor;
import java.util.HashMap;

/**
 * Mappinf for airline lookup table.
 * @author Philipp Chavaroche
 * @version 29.04.2016
 */
public final class AirlineLookUpMapTable extends AbstractMapTable {
    
    private AirlineLookUpMapTable(){
        super();
    }
    
    public static AbstractMapTable getInstance(){
        if(singletonMapTable== null){
            singletonMapTable = new AirlineLookUpMapTable();
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
        mapping.put("Code", new MethodDescriptor("setAirlineId", Integer.class));
        mapping.put("Description", new MethodDescriptor("setName", String.class));       
    }
}
