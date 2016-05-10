package hm.edu.swe2.flysoft.parser.mappings;

import hm.edu.swe2.flysoft.parser.model.MethodDescriptor;
import hm.edu.swe2.flysoft.interfaces.ICsvFieldMapping;
import java.util.Collections;
import java.util.Map;

/**
 * Defines a mappintg configuration between CSV columns and Java setter methods.
 * @author Philipp Chavaroche
 * @version 
 */
public abstract class AbstractMapTable implements ICsvFieldMapping {
    
    protected Map<String,MethodDescriptor> mapping;
    
    protected AbstractMapTable(){
        initMap();
    }
    
    protected abstract void initMap();
    
    @Override
    public Map<String,MethodDescriptor> getMapping(){
        return Collections.unmodifiableMap(mapping);
    }
    
    

}