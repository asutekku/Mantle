package mantle.collection;

/**
 * Categories class
 *
 * Used to story categories which are assigned to an asset
 * These are for internal use and user will be not able to
 * add a new Category to be used
 *
 * @Author Aku Mäkelä
 */
public class Categories {
    private Category[] categories;
    int count = 0;

    /**
     * Creates a new category array with n elements
     *
     * @param size Arrays size
     */
    public Categories(int size) {
        categories = new Category[size];
    }

    /**
     * Adds a new Category to the category array
     *
     * @param category category to add
     * @throws HandleException Exceptionhandler
     */
    public void addNew(Category category) throws HandleException {
        if (count >= categories.length) throw new HandleException("Too much assets");
        categories[count] = category;
        count++;
    }

    /**
     * Returns the categoryarray
     *
     * @return categories
     */
    public Category[] getCategoryArray(){
        return categories;
    }

    /**
     * @return The count of categories
     */
    public int length(){
        return categories.length;
    }

    /**
     * Returns the nth category's name as string
     *
     * @param index Index of the category
     * @return Name of the category
     */
    public String toString(int index) {
        return categories[index].toString();
    }

}
