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

    public MethodDescriptor(String name, Class<?> argumentType) {
        this.name = name;
        this.argumentType = argumentType;
    }

    public String getMethodName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getArgumentType() {
        return argumentType;
    }

    public void setArgumentType(Class<?> argumentType) {
        this.argumentType = argumentType;
    }
}
