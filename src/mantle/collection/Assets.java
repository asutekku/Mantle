package mantle.collection;

import mantle.util.preferences.CategoryPreferences;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Assets class
 * <p>
 * All of the collections assets will be stored to the asset list
 * User will be not able to create a new Assets class
 *
 * @Author Aku Mäkelä
 */
public class Assets implements Iterable<Asset> {
    private String filename = "";
    private final List<Asset> assets = new ArrayList<Asset>();

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
        assets.add(asset);
    }


    /**
     * Retuns nth asset
     *
     * @param i which asset to return
     * @return asset with i index
     * @throws IndexOutOfBoundsException out of bounds
     */
    public Asset get(int i) throws IndexOutOfBoundsException {
        return assets.get(i);
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
            for (Asset i : assets) {
                if (i.getIdNumber() == ID) {
                    returnable = i;
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
     * @param path Path to read from
     * @throws HandleException exception handler
     */
    public void readFromFile(String path) throws HandleException {
        String filepath = path.replace("file:", "");
        //Pattern valueMatcher = Pattern.compile("\\|");
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] pref = line.split("\\|+");
                Asset asset = new Asset();
                //Matcher matcher = valueMatcher.matcher(line);
                asset.setIdNumber(Integer.parseInt(pref[0]));
                asset.setFilepath(pref[1]);
                asset.setFilename(pref[2]);
                asset.setCategory(CategoryPreferences.getCategories(), pref[3]);
                asset.setType(pref[4]);
                assets.add(asset);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    /**
     * Saves the asset database to file
     *
     * @param path Path to save to
     * @param name Name of the file to save
     * @throws HandleException Exception handler
     */
    public void save(String path, String name) throws HandleException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + name + "_assets.mna"))) {
            for (Asset asset : assets) {
                StringBuilder line = new StringBuilder();
                line.append(asset.getIdNumber()).append("|").append(asset.getPath()).append("|");
                line.append(asset.getName()).append("|").append(asset.getCategory().toStringMin()).append("|");
                line.append(asset.getAuthor());
                bufferedWriter.write(line.toString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @return The count of assets
     */
    public int getCount() {
        return assets.size();
    }

    @Override
    public Iterator<Asset> iterator() {
        return null;
    }
}
