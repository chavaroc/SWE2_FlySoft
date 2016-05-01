package hm.edu.swe2.flysoft.interfaces;

import hm.edu.swe2.flysoft.parser.model.MethodDescriptor;
import java.util.Map;

/**
 * Interface for CSV -> field name mappings.
 * @author Philipp Chavaroche
 * @version 25.04.2016
 */
public interface ICsvFieldMapping {

    Map<String, MethodDescriptor> getMapping();
    
}
