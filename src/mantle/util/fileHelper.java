package mantle.util;

import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import mantle.util.preferences.PreferenceLoader;

import java.io.File;
import java.util.ResourceBundle;

/**
 * FileHelper class
 *
 * Used for file handling operations and printing the filesize in proper format
 */
public class fileHelper {

    private static ResourceBundle messages = PreferenceLoader.getLanguageBundle();

    /**
     * Gets the assets size in human readable form instead of pure bytes
     *
     * @param file Asset's file
     * @return Asset's size in proper form
     */
    public static String getSize(File file) {
        String fileSize;
        fileSize = file.length() < 1000000 ?
                Long.toString(file.length() / 1000) + " KB" :
                Long.toString(file.length() / 1000000) + " MB";
        return fileSize;
    }

    /**
     * Helper function for strings
     *
     * @param key String's key
     * @return String from key
     */
    private static String $(String key) {
        return messages.getString(key);
    }

    /**
     * Opens file import dialogd
     *
     * @param menubar Just some element to select the window where it's opened from
     * @return File opened
     */
    public static File importFile(MenuBar menubar) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter($("filechooserAllFiles"), "*.*"));
        fileChooser.setTitle($("filechooserImportTitle"));
        return fileChooser.showOpenDialog(menubar.getScene().getWindow());
    }

    /**
     * Returns the path of a File in a string from
     *
     * @param file File
     * @return Path of the File
     */
    public static String getPath(File file) {
        try {
            return file.toURI().toString();
        } catch (NullPointerException e){
            return "";
        }
    }

    /**
     * Set's image to the imgview
     * If path is not image or it is not proper, image will be null
     *
     * @param imgview Imgview to set the image
     * @param path Path for the image
     */
    public static void setImage(ImageView imgview, String path) {
        if (path != null && !path.isEmpty() && path != "Undefined") {
            try {
                Image image = new Image(path);
                imgview.setImage(image);
                imgview.fitWidthProperty();
            } catch (IllegalArgumentException e) {
                imgview.setImage(null);
            }
        } else {
            imgview.setImage(null);
        }
    }

}
