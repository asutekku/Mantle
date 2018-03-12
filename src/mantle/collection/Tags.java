package mantle.collection;

/**
 * Tags
 */
public class Tags {
    private static final int max_tags = 512;
    private int count = 0;
    private Tag tags[] = new Tag[max_tags];


    /**
     * Default constructor
     *
     * Creates an empty Tag group
     */
    public Tags() {
    }


    public void addNew(Tag tag) throws HandleException {
        if (count >= tags.length) throw new HandleException("Too much tags");
        tags[count] = tag;
        count++;
    }


    /**
     * Returns the nth Tag
     * @param i nth tag to get
     * @return The tag to be returned
     * @throws IndexOutOfBoundsException nth is outside of the count of tags
     */
    public Tag get(int i) throws IndexOutOfBoundsException {
        if (i < 0 || count <= i)
            throw new IndexOutOfBoundsException("Illegal index: " + i);
        return tags[i];
    }


    /**
     * Returns the count of tags
     * @return count of tags
     */
    public int getCount() {
        return count;
    }

    public static void main(String args[]) {
    }

}
