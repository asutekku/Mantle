package mantle.collection;

import java.util.*;

/**
 * Tags
 */
public class Tags{
    private int count = 0;
    private final List<Tag> tags = new ArrayList<>();

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
        tags.add(tag);
        count ++;
    }

    public List<Tag> getTags() {
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
        if (i < 0 || tags.size() <= i)
            throw new IndexOutOfBoundsException("Illegal index: " + i);
        return tags.get(i);
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
