package mantle.util.validate;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

/**
 * @author akko
 * @version 5 Apr 2018
 *
 */
public class path {
    /**
     * @param path Path to validate
     * @return True or false depending if the path is valid
     */
    public static boolean pathValidator(String path) {
        try {
            Paths.get(path);

        } catch (InvalidPathException | NullPointerException ex) {
            return false;
        }
        return true;
    }
}
