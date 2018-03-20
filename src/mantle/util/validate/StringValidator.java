package mantle.util.validate;

import mantle.collection.HandleException;

public class StringValidator {
    private static int length = 32;
    public static String validateString(String string) throws HandleException {
        if (string != null && !string.isEmpty() && string.length() <= length) {
            return string;
        } else if (string.length() > length) {
            throw new HandleException("String is too long");
        }else {
            throw new HandleException("String is empty");
        }
    }
}
