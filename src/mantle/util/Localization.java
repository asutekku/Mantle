package mantle.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Localization class
 * <p>
 * Used for the localization of the application
 */
public class Localization {

    private String language;
    private String country;
    private Locale currentLocale;
    private ResourceBundle locale;


    /**
     * @return The current locale set in the localization class
     */
    public Locale getCurrentLocale() {
        return currentLocale;
    }

    /**
     * Set the locale for the class
     * Used for language and file formats
     *
     * @param language The language for the locale
     * @param country  The country for the locale
     */
    public void setCurrentLocale(String language, String country) {
        this.currentLocale = new Locale(language, country);
    }

    /**
     * Default constructor where english is the default language
     */
    public Localization() {
        this.language = "en";
        this.country = "US";
        this.currentLocale = new Locale(this.language, this.country);
        this.locale = ResourceBundle.getBundle("mantle.resources.localization.lang", this.currentLocale);
    }

    /**
     * Constructor where the country and language can be set
     *
     * @param language The language for the locale
     * @param country  The country for the locale
     */
    public Localization(String language, String country) {
        this.language = language;
        this.country = country;
        this.currentLocale = new Locale(language, country);
        this.locale = ResourceBundle.getBundle("mantle.resources.localization.lang", this.currentLocale);
    }

    /**
     * @return The language in a two letter lowercase string form
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language The language in a two letter lowercase string form
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return The country in two letter upper case string form
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country The country in two letter upper case string form
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Sets the locale
     */
    public void setLocale() {
        this.locale = ResourceBundle.getBundle("mantle.resources.localization.lang", this.currentLocale);
    }

    /**
     * @return Returns current locale
     */
    public ResourceBundle getLocale() {
        return locale;
    }
}
