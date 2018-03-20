package mantle.collection;

/**
 * Category class
 *
 * Right now this class does not have much of functionality
 * but in the future it will be used to store category-specific
 * details.
 *
 * User will be not able to add their own categories
 *
 * @Author Aku Mäkelä 2018
 *
 */
public class Category {
    private int categoryID;
    private String catName;
    private int nextValue = 0;
    private String catNameMin;

    public Category() {

    }

    /**
     * @return The id for the category
     */
    public int getCategoryID() {
        return categoryID;
    }

    /**
     * Should not be used in normal cases
     *
     * @param categoryID The id of the category
     */
    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    /**
     * Constructor for the Category class
     *
     * @param categoryName Name for the category
     */
    public Category(String categoryName, String catNameMin) {
        this.catName = categoryName;
        this.catNameMin = catNameMin;
    }

    /**
     * Returns the category's name as String
     *
     * @return Name of the category
     */
    @Override
    public String toString(){
        return this.catName;
    }
    public String toStringMin(){
        return this.catNameMin;
    }

    /**
     * @return Assigns a new id to the category
     */
    public int register() {
        categoryID = nextValue;
        nextValue++;
        return categoryID;
    }

    public String getCatNameMin() {
        return catNameMin;
    }

    public void setCatNameMin(String catNameMin) {
        this.catNameMin = catNameMin;
    }
}
