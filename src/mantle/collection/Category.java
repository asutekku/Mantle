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
    public Category(String categoryName) {
        this.catName = categoryName;
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

    /**
     * @return Assigns a new id to the category
     */
    public int register() {
        categoryID = nextValue;
        nextValue++;
        return categoryID;
    }
}
