package hm.edu.swe2.flysoft.interfaces;

import hm.edu.swe2.flysoft.parser.model.MethodDescriptor;
import java.util.Map;

/**
 * Interface for 'CSV column name' -> 'method setter name' mappings.
 * @author Philipp Chavaroche
 * @version 25.04.2016
 */
public interface ICsvFieldMapping {

    /**
     * Get the mapping the the mapping object.
     * @return The map that represents the mapping between column and setter.
     */
    Map<String, MethodDescriptor> getMapping();
}
