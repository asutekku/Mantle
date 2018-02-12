package mantle.collection;

public abstract class DefaultRecord implements Cloneable, Record {

    @Override
    public int getFieldAmount() {
        return getFields().length;
    }

    @Override
    public Field getField(int f) {
        return getFields()[f];
    }

    @Override
    public String getValue(int f) {
        try {
            return getField(f).toString();
        } catch (Exception ex) {
            return "";
        }
    }

    abstract protected void setFields(Field[] fields);

    @Override
    public String setValue(int f, String s){
        try {
            String value = getField(f).setValue(s.trim());
            if (value == null) {
                return value;
            }
            return value;
        } catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }



}
