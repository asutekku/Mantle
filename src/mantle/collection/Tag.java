package mantle.collection;

/**
 * Tag class
 *
 * Stores the Tag ID, Asset ID and Tag's name
 *
 * User will be able to add new tags to the collections
 *
 * @Author Aku Mäkelä
 */
public class Tag implements Record{
    private int tagID;
    private int assetID;
    private String tagName;
    private static int nextValue = 0;

    /**
     * Constructor
     */
    public Tag() {
        //
    }

    /**
     * Create a new Tag with custom string as the name of the tag
     *
     * @param tagName Name of the tag
     */
    public Tag(String tagName) {
        this.tagName = tagName;
    }

    /**
     * Assign a ID to the tag
     *
     * @return The tag ID
     */
    public int register() {
        this.tagID = nextValue;
        nextValue++;
        return this.tagID;
    }

    /**
     * @return Tag's name
     */
    public String getTagName() {
        if (tagName != null && !tagName.equals("")){
            return tagName;
        }else{
            return "null";
        }
    }

    /**
     * @param tagName Tag's name
     */
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    /**
     * @return Tag ID
     */
    public int getTagID() {
        return tagID;
    }

    /**
     * Set the tag ID
     * This should never be used though
     *
     * @param tagID Tag's ID
     */
    public void setTagID(int tagID) {
        this.tagID = tagID;
    }

    /**
     * @return Tag's name as a string
     */
    @Override
    public String toString(){
        return this.tagName;
    }

    /**
     * @return Asset ID assigned to the tag
     */
    public int getAssetID() {
        return assetID;
    }

    /**
     * Assign a asset ID to the tag
     *
     * @param assetID Asset's ID
     */
    public void setAssetID(int assetID) {
        this.assetID = assetID;
    }
}
