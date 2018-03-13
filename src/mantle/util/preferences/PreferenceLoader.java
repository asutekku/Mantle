package mantle.util.preferences;

import mantle.collection.Categories;
import mantle.collection.Category;

public class PreferenceLoader {
    //private static ApplicationPreferences appPref;
    private static CategoryPreferences catPref = new CategoryPreferences();
    private static Categories categories = catPref.getCategories();

    public static Category[] getCategoryArray() {
        return catPref.getCategoryArray();
    }

    public void setCategories(Categories categories) {
        PreferenceLoader.categories = categories;
    }

    public static Categories getCategories() {
        catPref.addCategories();
        return categories;
    }
}
