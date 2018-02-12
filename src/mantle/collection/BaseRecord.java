package mantle.collection;

import mantle.collection.Field;
import mantle.collection.DefaultRecord;


public class BaseRecord extends DefaultRecord {


    public Field fields[] = null;
    int first;

    @Override
    protected final void setFields(Field[] newFields) {
        fields = newFields;
    }

    @Override
    public Field[] getFields() {
        return fields;
    }

    public BaseRecord(Field[] fields, int first) {
        setFields(fields);
        this.first = first;
    }
}
