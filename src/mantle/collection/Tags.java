package mantle.collection;

import mantle.util.preferences.CategoryPreferences;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Tags class
 *
 * Collection has one tags class
 * There are no limits how many tags the collection can has
 *
 *
 */
public class Tags {
    private int count = 0;
    private int max_tags = 32;
    private final Tag[] tags = new Tag[max_tags];

    /**
     * Default constructor
     * <p>
     * Creates an empty Tag group
     */
    public Tags() {
    }

    /**
     * Add new tag to the tags list
     *
     * @param tag Tag to be added
     */
    public void addNew(Tag tag) {
        tag.register();
        int tagID = tag.getTagID();
        tags[tagID] = tag;
        count++;
    }

    /**
     * @return Returns the list containing all the Tags the collection has
     */
    public Tag[] getTags() {
        return tags;
    }

    /**
     * Returns the nth Tag
     *
     * @param i nth tag to get
     * @return The tag to be returned
     * @throws IndexOutOfBoundsException nth is outside of the count of tags
     */
    public Tag get(int i) throws IndexOutOfBoundsException {
        if (i < 0 || tags.length <= i)
            throw new IndexOutOfBoundsException("Illegal index: " + i);
        return tags[i];
    }


    /**
     * Returns the count of tags
     *
     * @return count of tags
     */
    public int getCount() {
        return count;
    }

    public void readFromFile(String path) throws HandleException {
        String filepath = path.replace("file:", "");
        Pattern valueMatcher = Pattern.compile("\\|");
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] pref = line.split("\\|+");
                Tag tag = new Tag();
                Matcher matcher = valueMatcher.matcher(line);
                tag.setTagID(Integer.parseInt(pref[0]));
                tag.setAssetID(Integer.parseInt(pref[1]));
                tag.setTagName(pref[2]);
                addNew(tag);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    /**
     * Saves the asset database to file
     *
     * @throws HandleException
     */
    public void save(String path, String name) throws HandleException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + name + "_tags.mnt"))) {
            for (Tag tag : tags) {
                StringBuilder line = new StringBuilder();
                line.append(tag.getTagID()).append("|").append(tag.getAssetID()).append("|").append(tag.getTagName());
                bufferedWriter.write(line.toString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
