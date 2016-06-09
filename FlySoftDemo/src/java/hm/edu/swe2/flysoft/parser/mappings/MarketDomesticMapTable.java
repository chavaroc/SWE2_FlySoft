package hm.edu.swe2.flysoft.parser.mappings;
import hm.edu.swe2.flysoft.parser.model.MethodDescriptor;
import java.util.HashMap;

/**
 * Maping for the T100 Market US Domestic table.
 * @author Philipp Chavaroche
 * @version 01.05.2016
 */
public final class MarketDomesticMapTable extends AbstractMapTable {
    
    private static AbstractMapTable singletonMapTable;
    /**
     * Construct a mapping for the market docmestic table.
     * Privae because of singleton pattern.
     */    
    private MarketDomesticMapTable(){
           super();
    }
    
     /**
     * Get the instance of the segemnt domestic table mapping.
     * @return A segement domestic mapping object.
     */
    public static AbstractMapTable getInstance(){
        if(singletonMapTable== null){
            singletonMapTable = new MarketDomesticMapTable();
        }
        return singletonMapTable;
    }

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
