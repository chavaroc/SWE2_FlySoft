package hm.edu.swe2.flysoft.parser.model;

/**
 * Represents a description of a single argument method.
 * Includes the name of the method and type of the first argument.
 * @author Philipp Chavaroche
 * @version 27.04.2016
 */
public class MethodDescriptor {
    
    private String name;
    private Class<?> argumentType;

    /**
     * Construct a new method descriptor.
     * @param name The name of the method.
     * @param argumentType The type of the first argument.
     */
    public MethodDescriptor(String name, Class<?> argumentType) {
        this.name = name;
        this.argumentType = argumentType;
    }

    /**
     * Get the method name.
     * @return The name of the method.
     */
    public String getMethodName() {
        return name;
    }

    /**
     * Set the name of the method.
     * @param name The name, that should be set for the method.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the type of the first argument of the method.
     * @return The type of the first argumet.
     */
    public Class<?> getArgumentType() {
        return argumentType;
    }

    /**
     * Set the type of the first argument of the method.
     * @param name The type of the first argument of the method.
     */
    public void setArgumentType(Class<?> argumentType) {
        this.argumentType = argumentType;
    }
}
