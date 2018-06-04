package mantle.collection;

import mantle.util.preferences.PreferenceLoader;

import java.io.*;

/**
 * Asset class
 * <p>
 * Stores all the details required when creating an asset
 * <p>
 * User will be able to create new assets
 * (That's kind of point of the application)
 * <p>
 * The class will not store any Tags as that will be handled by the collection
 *
 * @author Aku Mäkelä
 * @version 0.2, 13.03.2018
 */
public class Asset implements Record{
    private int idNumber;
    private String filepath;
    private String name;
    private String author;
    private Category category;
    private String type;
    private String size;
    private static int nextValue = 0;

    /**
     * @return Returns the name of the asset
     */
    public String getName() {
        return name;
    }

    /**
     * @return The path of the asset name included
     */
    public String getPath() {
        return filepath;
    }

    /**
     * @return The author of the asset
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @return The CategoryPreferences of the asset
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @return Returns the asset's filesize
     */
    public String getSize() {
        return size;
    }

    /**
    * @return Returns the asset's type
    */
    public String getType() {
        return type;
    }

    /**
     * Returns either true or false depending if assiging a path works
     * Right now it only checks if the path is empty
     *
     * @param filepath The filepath for the asset
     * @return true or false depending on the result
     */
    public boolean setFilepath(String filepath) {
        if (filepath != null && !filepath.isEmpty()) {
            this.filepath = filepath;
            return true;
        }
        return false;
    }

    /**
     * @param filename The name (Name) for the asset
     * @return true or false depending on the result
     */
    public boolean setFilename(String filename) {
        if (filename != null && !filename.isEmpty()) {
            this.name = filename;
            return true;
        }
        return false;
    }

    /**
     * @param author The author of the asset
     * @return true or false depending on the result
     */
    public boolean setAuthor(String author) {
        if (author != null && !author.isEmpty()) {
            this.author = author;
            return true;
        }
        return false;
    }

    /**
     * Assigns a new type to the asset and checks if it goes through
     *
     * @param type Type to add
     * @return true or false depending on the result
     */
    public boolean setType(String type) {
        if (type != null && !type.isEmpty()) {
            this.type = type;
            return true;
        }
        return false;
    }

    /**
     * Assigns a category to the asset from a ComboBox
     * If category is not defined, category is Basic
     *
     * @param categories Categoryset to choose the category from
     * @param value Sets the category based on the string
     */
    public void setCategory(Categories categories, String value) {
        for (int i = 0; i < categories.getCategoryArray().length; i++) {
            if (categories.getCategoryArray()[i].toString() == value) {
                setCategory(categories.getCategoryArray()[i]);
                break;
            }
            setCategory(categories.getCategoryArray()[0]);
        }
    }

    /**
     * General set category when you have the precise category defined
     *
     * @param category The category for the asset
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * @param tagID The id of the tag to be assigned to the asset

    public void setTag(int tagID) throws HandleException {
    tags.add(tagID);
    }*/

    /**
     * Setter function for the size
     * Is boolean for easy checking if the set worked
     *
     * @param size Want to compress the file? Just input small filesize :^)
     * @return true or false depending on the result
     */
    public boolean setSize(String size) {
        if (size != null && !size.isEmpty()) {
            this.size = size;
            return true;
        }
        return false;
    }

    /**
     * Default constructor
     */
    public Asset() {
        this.name = "New asset";
        this.filepath = "Undefined";
        this.author = "Undefined";
        this.category = PreferenceLoader.getCategoryArray()[0]; // Basic category
        this.type = "Undefined";
        this.size = "Undefined";
    }

    /**
     * Constructor with parameters
     * Used very rarely
     *
     * @param name     Name for the asset
     * @param filepath Path for the asset
     * @param category CategoryPreferences of the asset
     * @param size     Size of the asset
     * @param author   Asset author
     */
    public Asset(String name, String filepath, String author, Category category, String size) {
        this.name = name;
        this.filepath = filepath;
        this.author = author;
        this.category = category;
        this.size = size;
    }

    /**
     * Print the asset's name
     *
     * @param out where the details will be printed
     */
    public void print(PrintStream out) {
        out.println(name);
    }

    /**
     * Gives a new idnumber to the asset
     * The number is always ascending
     * <p>
     * If asset is deleted, the ID for that will not be reassigned
     *
     * @return id of the asset
     */
    public int register() {
        idNumber = nextValue;
        nextValue++;
        return idNumber;
    }

    @Override
    public String toString() {
        return this.name;
    }

    /**
     * returns The id of the asset
     *
     * @return Asset id
     */
    public int getIdNumber() {
        return idNumber;
    }
    
    /**
     * Set asset's ID
     *
     * @param ID the ID of the asset to modify
     */
    public void setIdNumber(int ID) {
        this.idNumber = ID;
        nextValue = ID + 1;
    }

    /**
     * @param args Args if you want
     */
    public static void main(String[] args){
        Asset newAsset = new Asset();
        newAsset.setFilename("YO");
    }
}
