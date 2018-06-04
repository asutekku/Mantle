package mantle.util.preferences;

import mantle.collection.Categories;
import mantle.collection.Category;
import mantle.util.Localization;

//import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author akko
 * @version 5 Apr 2018
 *
 */
public class PreferenceLoader {
    //private static ApplicationPreferences appPref;
    private static Categories categories = CategoryPreferences.getCategories();
    private static Localization locale = new Localization("en", "US");
    private static ResourceBundle languageBundle = locale.getLocale();

    /**
     * @return Returns languagebunle
     */
    public static ResourceBundle getLanguageBundle() {
        return languageBundle;
    }

    /**
     * @return Categoryarray
     */
    public static Category[] getCategoryArray() {
        return CategoryPreferences.getCategoryArray();
    }

    /**
     * @param categories Categories to set
     */
    public void setCategories(Categories categories) {
        PreferenceLoader.categories = categories;
    }

    /**
     * @return Categories
     */
    public static Categories getCategories() {
        CategoryPreferences.addCategories();
        return categories;
    }
}
