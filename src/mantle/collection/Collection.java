package mantle.collection;

/**
 * Collection-class
 *
 * @author Aku Mäkelä
 * @version 0.1, 28.02.2018
 */
public class Collection {
    private final Assets assets = new Assets();

    /**
     * Returns the count of assets in collection
     * @return asset count
     */
    public int getAssetCount() {
        return assets.getCount();
    }


    /**
     * Removes nth asset
     * @param i reference to the asset to remove
     * @return amount of assets removed
     */
    public int remove(@SuppressWarnings("unused") int i) {
        return 0;
    }


    /**
     * Adds new asset to the collection
     * @param asset asset to be added
     * @throws HandleException excpetion if insertion fails
     */
    public void add(Asset asset) throws HandleException {
        assets.addNew(asset);
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
