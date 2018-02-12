package mantle.collection;

/**
 * Interface for the record
 */

public interface Record {


    /**
     * @return Get the reference to the whole record
     */
    public abstract int getFieldAmount();

    public abstract Field[] getFields();

    public abstract Field getField(int f);

    /**
     * Used to set value for the field
     * @param f field the value to bet set
     * @param s The value being set
     */
    public abstract String setValue(int f, String s);

    /**
     * Used to return the value from field
     * @param f The field value is wanted from
     */
    public abstract String getValue(int f);
}
