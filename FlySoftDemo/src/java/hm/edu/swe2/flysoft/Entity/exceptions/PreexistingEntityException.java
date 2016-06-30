package hm.edu.swe2.flysoft.entity.exceptions;

/**
 * An exception, that is thrown, if an entity pre exist.
 * @author Philipp Chavaroche
 * @version 30.06.2016
 */
public class PreexistingEntityException extends Exception {
    public PreexistingEntityException(String message, Throwable cause) {
        super(message, cause);
    }
    public PreexistingEntityException(String message) {
        super(message);
    }
}
