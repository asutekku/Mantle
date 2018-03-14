package mantle.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Assets class
 *
 * All of the collections assets will be stored to the asset list
 * User will be not able to create a new Assets class
 *
 * @Author Aku Mäkelä
 *
 */
public class Assets  implements Iterable<Asset> {
    private static int count = 0;
    private String filename = "";
    private final List<Asset> items = new ArrayList<Asset>();

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
        items.add(asset);
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
        return items.get(i);
    }

    /**
     * Returns asset by ID
     *
     * @param ID to be searched
     * @return asset with ID
     * @throws IndexOutOfBoundsException out of bounds
     */
    public Asset getbyID(int ID) throws IndexOutOfBoundsException {
        Asset returnable = null;
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

    /**
     * Reads the asset database from a file
     * MCL = Mantle CoLlection
     *
     * @param path
     * @throws HandleException
     */
    public void readFromFile(String path) throws HandleException {
        filename = path + "/assets.mcl";
        throw new HandleException("Can't read from file: " + filename);
    }

    /**
     * Saves the asset database to file
     *
     * @throws HandleException
     */
    public void save() throws HandleException {
        throw new HandleException("Can't save to file: " + filename);
    }


    /**
     * @return The count of assets
     */
    public int getCount() {
        return count;
    }

    @Override
    public Iterator<Asset> iterator() {
        return null;
    }
}
