package hm.edu.swe2.flysoft.parser.mappings;

import hm.edu.swe2.flysoft.parser.model.MethodDescriptor;
import hm.edu.swe2.flysoft.interfaces.ICsvFieldMapping;
import java.util.Collections;
import java.util.Map;

/**
 * Defines a mapping configuration between CSV columns and Java setter methods.
 * @author Philipp Chavaroche
 * @version 29.04.2016
 */
public abstract class AbstractMapTable implements ICsvFieldMapping {
    
    protected Map<String,MethodDescriptor> mapping;
    
    /**
     * Construct a new abstract table mapping.
     */
    protected AbstractMapTable(){
        initMap();
    }
    
     /**
     * Initialize the map table.
     */
    protected abstract void initMap();
    
    @Override
    public Map<String,MethodDescriptor> getMapping(){
        return Collections.unmodifiableMap(mapping);
    }
}
