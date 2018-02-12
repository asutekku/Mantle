package mantle.collection;
/**
 * @AUTHOR Aku Mäkelä
 * @Version 0.01 , 12.02.2018
 *
 * Used to validate filepath, so no erronous types will be entered
 *
 */

public class FilepathValidator implements Validator {

    @Override
    public String validate(String query) {
        if (query != null) {
            return query;
        }
        return null;
    }
}
