package mantle.util.validate;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public class path {
    public static boolean pathValidator(String path) {
        try {
            Paths.get(path);

        } catch (InvalidPathException | NullPointerException ex) {
            return false;
        }
        return true;
    }
}
