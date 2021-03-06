package hm.edu.swe2.flysoft.entity.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * An exception, that is thrown, if "illegal orphan" occur.
 * @author Philipp Chavaroche
 * @version 30.06.2016
 */
public class IllegalOrphanException extends Exception {
    private List<String> messages;
    
    /**
     * Construct a new IllegalOrphanException.
     * @param messages The reason of the exception.
     */
    public IllegalOrphanException(List<String> messages) {
        super((messages != null && messages.size() > 0 ? messages.get(0) : null));
        if (messages == null) {
            this.messages = new ArrayList<String>();
        }
        else {
            this.messages = messages;
        }
    }
    public List<String> getMessages() {
        return messages;
    }
}
