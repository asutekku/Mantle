package mantle.collection;

/**
 * Collection-class
 *
 * @author Aku Mäkelä
 * @version 0.1, 28.02.2018
 */
public class Collection {
    private String collectionName = "";
    private final Assets assets = new Assets();
    private final Tags tags = new Tags();

    /**
     * Returns the count of assets in collection
     *
     * @return asset count
     */
    public int getAssetCount() {
        return assets.getCount();
    }


    /**
     * Returns the amount of tags in the collection
     * This does not return unique tags, just the count
     *
     * @return
     */
    public int getTagCount() {
        return tags.getCount();
    }

    public Tags getTags() {
        return tags;
    }

    /**
     * Removes nth asset
     *
     * @param i reference to the asset to remove
     * @return amount of assets removed
     */
    public int remove(@SuppressWarnings("unused") int i) {
        return 0;
    }


    /**
     * Adds new asset to the collection
     *
     * @param asset asset to be added
     * @throws HandleException excpetion if insertion fails
     */
    public void add(Asset asset) throws HandleException {
        assets.addNew(asset);
        asset.register();
    }


    /**
     * Adds a new tag to the asset and adds the tag to the taglsit
     *
     * @param asset   Asset the tag will be assigned to
     * @param tagName The name of the tag that will be assigned
     * @throws HandleException
     */
    public void addTag(Asset asset, String tagName) throws HandleException {
        Tag tag = new Tag(tagName);
        tag.setAssetID(asset.getId());
        asset.setTag(tag.getTagID());
        tags.addNew(tag);
    }


    /**
     * Returns the tags assigned to asset in a string
     *
     * @param assetId asset which you want the tags from
     * @return String containing the tags
     * @throws IndexOutOfBoundsException
     */
    public String getAssetTags(int assetId) throws IndexOutOfBoundsException {
        StringBuilder output = new StringBuilder();
        int tagCount = assets.get(assetId).getTagCount();
        if (tagCount != 0) {
            for (int i = 0; i < tagCount; i++) {
                for (int j = 0; j < tags.getTags().size(); j++) {
                    int tagAssetID = tags.getTags().get(j).getAssetID();
                    if (tagAssetID == assetId) {
                        String tagName = tags.getTags().get(j).getTagName();
                        output.append(tagName + ", ");
                    }
                }
            }
        }
        return output.toString();
    }

    /**
     * Returns nth asset
     *
     * @param i asset id
     * @return nth asset
     * @throws IndexOutOfBoundsException
     */
    public Asset getAsset(int i) throws IndexOutOfBoundsException {
        return assets.get(i);
    }


    /**
     * Reads the collection from filename
     * Not yet implemented
     *
     * @param fileName file to read from
     * @throws HandleException
     */
    public void readFromFile(String fileName) throws HandleException {
        assets.readFromFile(fileName);
    }


    /**
     * Saves the collection to file
     * Not yet implemented
     *
     * @throws HandleException
     */
    public void save() throws HandleException {
        assets.save();
    }

    public static void main(String args[]) {
    }

}
