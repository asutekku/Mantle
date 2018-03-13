package mantle.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class Localization {

    private String language;
    private String country;
    private Locale currentLocale;
    private ResourceBundle locale;

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(String language, String country) {
        this.currentLocale = new Locale(language, country);
    }

    public Localization() {
        this.language = "en";
        this.country = "US";
        this.currentLocale = new Locale(this.language, this.country);
        this.locale = ResourceBundle.getBundle("mantle.resources.localization.lang", this.currentLocale);
    }

    public Localization(String language, String country) {
        this.language = language;
        this.country = country;
        this.currentLocale = new Locale(language, country);
        this.locale = ResourceBundle.getBundle("mantle.resources.localization.lang", this.currentLocale);
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLocale() {
        this.locale = ResourceBundle.getBundle("mantle.resources.localization.lang", this.currentLocale);
    }

    public ResourceBundle getLocale() {
        return locale;
    }

    public static void main(String args[]) {

    }
}
