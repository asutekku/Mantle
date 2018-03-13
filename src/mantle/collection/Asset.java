package mantle.collection;

import java.io.*;

/**
 * Asset
 *
 * @author Aku Mäkelä
 * @version 0.2, 13.03.2018
 */
public class Asset {
    private int idNumber;
    private String filepath;
    private String filename;
    private String author;
    private Tags tags = new Tags();
    private Category category = new Category();
    private String size;
    private static int nextValue = 1;

    /**
     * @return Returns the name of the assset
     */
    public String getName() {
        return filename;
    }

    /**
     * @return The path of the asset filename included
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
     * @return The tags assigned to the asset
     */
    public Tag getTags(int index) throws IndexOutOfBoundsException {
        return tags.get(index);
    }

    /**
     * @return Returns the asset filesize
     */
    public String getSize() {
        return size;
    }

    /**
     * @param filepath The filepath for the asset
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    /**
     * @param filename The name (Name) for the asset
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @param author The author of the asset
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @param category The CategoryPreferences of the asset
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * @param tag The tag to be added to the asset
     */
    public void setTag(Tag tag) throws HandleException {
        tags.addNew(tag);
    }

    /**
     * @param size Want to compress the file? Just input small filesize :^)
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Default constructor
     */
    public Asset() {

    }

    /**
     * @param filename Name for the asset
     * @param filepath Path for the asset
     * @param category CategoryPreferences of the asset
     * @param type     CategoryPreferences of the asset
     * @param size     Size of the asset
     * @param author   Asset author
     */
    public Asset(String filename, String filepath, String author, Category category, Tag tag, String size) throws HandleException {
        this.filename = filename;
        this.filepath = filepath;
        this.author = author;
        this.category = category;
        this.tags.addNew(tag);
        this.size = size;
    }

    /**
     * Print the asset details
     *
     * @param out where the details will be printed
     */
    public void print(PrintStream out) {
        out.println(String.format("%03d", idNumber, 3) + "  " + filepath + "  "
                + filename);
        out.println("  " + category + "  " + category + " " + tags);
        out.println(" Filesize: " + size);
        out.println("  " + author);
    }


    /**
     * Print the asset details
     *
     * @param os where the details will be printed
     */
    public void print(OutputStream os) {
        print(new PrintStream(os));
    }


    /**
     * Gives a new idnumber to the asset
     * The number is always ascending
     * <p>
     * If asset is deleted, the number will not be reassigned
     *
     * @return id of the asset
     */
    public int register() {
        idNumber = nextValue;
        nextValue++;
        return idNumber;
    }


    /**
     * returns The id of the asset
     *
     * @return Asset id
     */
    public int getId() {
        return idNumber;
    }

    public static void main(String args[]) {

    }

}
