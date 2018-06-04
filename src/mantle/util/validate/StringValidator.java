package mantle.util.validate;

import mantle.collection.HandleException;

/**
 * @author akko
 * @version 5 Apr 2018
 *
 */
public class StringValidator {
    private static int length = 32;
    
    /**
     * @param string String to check
     * @return String is 
     * @throws HandleException Exceptionhandler :D
     */
    public static String validateString(String string) throws HandleException {
        if (string != null && !string.isEmpty() && string.length() <= length) {
            return string;
        } else if (string != null && string.length() >= length) {
            throw new HandleException("String is too long");
        }else {
            throw new HandleException("String is empty");
        }
    }
}
