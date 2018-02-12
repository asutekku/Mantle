package mantle.collection;

/**
 * @AUTHOR Aku Mäkelä
 * @Version 0.01 , 12.02.2018
 */

public class Asset extends BaseRecord {

    public Asset() {
        super(new Field[]{
                new StringField("filepath", new FilepathValidator()),
                new StringField("fileName")
        }, 1);
    }

}