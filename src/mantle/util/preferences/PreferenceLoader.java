package mantle.util.preferences;

import mantle.collection.Categories;
import mantle.collection.Category;
import mantle.util.Localization;

import java.util.Locale;
import java.util.ResourceBundle;

public class PreferenceLoader {
    //private static ApplicationPreferences appPref;
    private static CategoryPreferences catPref = new CategoryPreferences();
    private static Categories categories = catPref.getCategories();
    private static Localization locale = new Localization("en", "US");
    private static ResourceBundle languageBundle = locale.getLocale();

    public static ResourceBundle getLanguageBundle() {
        return languageBundle;
    }

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
