package hm.edu.swe2.flysoft.parser.mappings;
import hm.edu.swe2.flysoft.parser.model.MethodDescriptor;
import java.util.HashMap;

/**
 * Mapping for airline lookup table.
 * @author Philipp Chavaroche
 * @version 29.04.2016
 */
public final class AirlineLookUpMapTable extends AbstractMapTable {
    
    /**
     * The singleton object of this class.
     */
    private static AbstractMapTable singletonMapTable;
    
    /**
     * Construct a mapping for the airline lookup table.
     * Privae because of singleton pattern.
     */
    private AirlineLookUpMapTable(){
        super();
    }
    
    /**
     * Get the instance of the airline look up table mapping.
     * @return A airline look up table mapping object.
     */
    public static AbstractMapTable getInstance(){
        if(singletonMapTable== null){
            singletonMapTable = new AirlineLookUpMapTable();
        }
        return singletonMapTable;
    }
    
    @Override
    protected void initMap(){
        mapping = new HashMap<>();
        //          <CSV-Header>,<Method setter name>, <Parameter data type>
        mapping.put("Code", new MethodDescriptor("setAirlineId", Integer.class));
        mapping.put("Description", new MethodDescriptor("setName", String.class));       
    }
}
