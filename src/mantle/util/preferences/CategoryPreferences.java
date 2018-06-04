package mantle.util.preferences;

import mantle.collection.Categories;
import mantle.collection.Category;
import mantle.collection.HandleException;

/**
 * @author akko
 * @version 5 Apr 2018
 *
 */
public class CategoryPreferences {

    private static final Categories categories = new Categories(4);
    private static Category basic = new Category("Basic","B");
    private static Category material = new Category("Material","M");
    private static Category staticMesh = new Category("Static Mesh","SM");
    private static Category texture = new Category("Texture","T");

    /**
     * @return Categoryarray
     */
    public static Category[] getCategoryArray() {
        return categories.getCategoryArray();
    }

    /**
     * @return Categories
     */
    public static Categories getCategories() {
        return categories;
    }

    /**
     * Add's categories to the preferences
     */
    public static void addCategories() {
        try {
            categories.addNew(basic);
            categories.addNew(material);
            categories.addNew(staticMesh);
            categories.addNew(texture);
        } catch (HandleException e) {
            e.printStackTrace();
        }
    }
}
