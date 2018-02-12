package mantle.collection;

public interface Field extends Cloneable{

    @Override
    String toString();

    /**
     * Used to assign value to the field
     * @param string
     * @return
     */
    String setValue(String string);

    String getQuery();

}
