package mantle.collection;

import mantle.util.controllers.eventHandler;
import mantle.util.preferences.CategoryPreferences;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Collection-class
 * <p>
 * Used to store the assets and tags used in the collection
 * For now, there's no way to save the collection
 *
 * @author Aku Mäkelä
 * @version 0.1, 28.02.2018
 */
public class Collection {
    private String collectionName = "Sample";
    private String collectionPath = "/Users/akko/Documents/JYU/ohj2/";
    private final Assets assets = new Assets();
    private final Tags tags = new Tags();

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

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

    /**
     * @return Tags added to the collection
     */
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
     * Adds a new tag to the asset and adds the tag to the taglist
     *
     * @param asset   Asset the tag will be assigned to
     * @param tagName The name of the tag that will be assigned
     * @throws HandleException
     */
    public void addTag(Asset asset, String tagName) throws HandleException {
        Tag tag = new Tag(tagName);
        tag.setAssetID(asset.getIdNumber());
        //asset.setTag(tag.getTagID());
        tags.addNew(tag);
    }


    /**
     * Returns the tags assigned to asset in a string
     * This function combines the assets and tags
     *
     * @param assetId asset which you want the tags from
     * @return String containing the tags
     * @throws IndexOutOfBoundsException
     */
    public String getAssetTags(int assetId) throws IndexOutOfBoundsException {
        StringBuilder output = new StringBuilder();
        output.append("");
        for (int i = 0; i < tags.getCount(); i++) {
            if (tags.getTags()[i].getAssetID() == assetId) {
                String tagName = tags.getTags()[i].getTagName();
                output.append(tagName + " ");
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
     * Creates a new asset and assigns it to the collection
     *
     * @return assetID if creation is valid, -1 if it fails
     */
    public int newAsset() {
        Asset newAsset = new Asset();
        try {
            this.add(newAsset);
            return newAsset.getIdNumber();
        } catch (HandleException e) {
            eventHandler.error("Problems with creating a new asset " + e.getMessage());
            return -1;
        }
    }

    /**
     * Reads the collection from filename
     * Not yet implemented
     *
     * @param fileName file to read from
     * @throws HandleException
     */
    public void readFromFile(String fileName) throws HandleException, IOException {
        String filepath = fileName.replace("file:", "");
        String assetLine = Files.readAllLines(Paths.get(filepath)).get(3);
        String tagLine = Files.readAllLines(Paths.get(filepath)).get(4);
        Pattern valueMatcher = Pattern.compile("\\|");
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String asline =  assetLine.replace("Assets: ","");
            String tline = tagLine.replace("Tags: ","");
            assets.readFromFile(asline);
            tags.readFromFile(tline);
        } catch (IOException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    /**
     * Saves the collection to file
     * Not yet implemented
     *
     * @throws HandleException
     */
    public void save() throws HandleException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(collectionPath + collectionName + ".mcl"))) {
            StringBuilder line = new StringBuilder();
            bufferedWriter.write("Mantle collection: " + collectionName);
            bufferedWriter.newLine();
            bufferedWriter.write("Created on: " + dtf.format(localDate));
            bufferedWriter.newLine();
            bufferedWriter.write("#################################");
            bufferedWriter.newLine();
            bufferedWriter.write("Assets: " + collectionPath + collectionName + "_assets.mna");
            bufferedWriter.newLine();
            bufferedWriter.write("Tags: " + collectionPath + collectionName + "_tags.mnt");
            bufferedWriter.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        assets.save(collectionPath, collectionName);
        tags.save(collectionPath, collectionName);
    }
}
