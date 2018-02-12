package mantle.collection;

public abstract class defaultField implements Field {

    private String query;

    protected Validator validator;

    public defaultField(String query)  { this.query = query; }

    /**
     * Used to initialize default field
     * @param query String to bet set as query
     * @param validator Validator used to validate that the input for the field is correct
     */
    public defaultField(String query, Validator validator) {
        this.query = query;
        this.validator = validator;
    }

    /**
     * Prints the field value to a string
     */
    @Override
    public abstract String toString();

    /**
     * Used to get get query from the field
     * @return returns query;
     */
    public String getQuery() {
        return this.query;
    }

    /**
     * Used to set the query for the field
     * @param query String to be set as the query.
     */
    /*public String setQuery(String query){
        this.query = query;
    }*/
}
