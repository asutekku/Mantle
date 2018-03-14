package mantle.collection;

/**
 * Handle exceptions
 */
public class HandleException extends Exception {
    private static final long serialVersionUID = 1L;
    /**
     * Exception handler
     *
     * @param message Exception message
     */
    public HandleException(String message) {
        super(message);
    }
}