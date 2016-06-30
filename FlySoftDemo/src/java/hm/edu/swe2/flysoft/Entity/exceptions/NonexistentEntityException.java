package hm.edu.swe2.flysoft.entity.exceptions;

/**
 * An exception, that is thrown, if an entity does not exist.
 * @author Philipp Chavaroche
 * @version 30.06.2016
 */
public class NonexistentEntityException extends Exception {
    public NonexistentEntityException(String message, Throwable cause) {
        super(message, cause);
    }
    public NonexistentEntityException(String message) {
        super(message);
    }
}
