package mantle.collection;

public class StringField extends defaultField {
    private String string = "";

    public StringField(String query) {
        super(query);
    }

    public StringField(String query, Validator validator) {
        super(query, validator);
    }

    @Override
    public String toString() {
        return string;
    }

    @Override
    public String setValue(String s) {
        if (validator == null) {
            this.string = s;
            return null;
        }

        String error = validator.validate(s);
        if (error == null) {
            this.string = s;
            return null;
        }
        return error;
    }
}
