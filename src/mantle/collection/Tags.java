package mantle.collection;

import java.util.*;

/**
 * Tags class
 *
 * Collection has one tags class
 * There are no limits how many tags the collection can has
 *
 *
 */
public class Tags{
    private int count = 0;
    private int max_tags = 32;
    private final Tag[] tags = new Tag[max_tags];

    /**
     * Default constructor
     *
     * Creates an empty Tag group
     */
    public Tags() {
    }

    /**
     * Add new tag to the tags list
     * @param tag Tag to be added
     */
    public void addNew(Tag tag) {
        tag.register();
        int tagID = tag.getTagID();
        tags[tagID] = tag;
        count ++;
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
}
