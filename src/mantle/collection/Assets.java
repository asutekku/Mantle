package mantle.collection;

/**
 * Collection of assets
 */
public class Assets {
    private static final int max_assets = 512;
    private int count = 0;
    private String filename = "";
    private Asset items[] = new Asset[max_assets];


    /**
     * Default constructor
     */
    public Assets() {
    }

    /**
     * Inserts a new asset to the assetlist
     *
     * @param asset to be inserted
     * @throws HandleException Uh oh
     */
    public void addNew(Asset asset) throws HandleException {
        if (count >= items.length) throw new HandleException("Too much assets");
        items[count] = asset;
        count++;
    }


    /**
     * Retuns nth asset
     *
     * @param i which asset to return
     * @return asset with i index
     * @throws IndexOutOfBoundsException out of bounds
     */
    public Asset get(int i) throws IndexOutOfBoundsException {
        if (i < 0 || count <= i)
            throw new IndexOutOfBoundsException("Illegal index: " + i);
        return items[i];
    }

    /**
     * Returns asset with ID
     *
     * @param ID to be searched
     * @return asset with ID
     * @throws IndexOutOfBoundsException out of bounds
     */
    public Asset getbyID(int ID) throws IndexOutOfBoundsException {
        Asset returnable = new Asset();
        try {
            for (Asset i : items) {
                if (i.getId() ==ID){
                    returnable =  i;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.print("Use correct index");
        }
        return returnable;
    }

    public void readFromFile(String path) throws HandleException {
        filename = path + "/assets.mtn";
        throw new HandleException("Ei osata viel� lukea tiedostoa " + filename);
    }

    public void save() throws HandleException {
        throw new HandleException("Ei osata viel� tallettaa tiedostoa " + filename);
    }


    public int getCount() {
        return count;
    }

}
