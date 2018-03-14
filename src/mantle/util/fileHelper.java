package mantle.util;

import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import mantle.util.preferences.PreferenceLoader;

import java.io.File;
import java.util.ResourceBundle;

public class fileHelper {

    private static ResourceBundle messages = PreferenceLoader.getLanguageBundle();

    public static String getSize(File file) {
        String fileSize;
        fileSize = file.length() < 1000000 ?
                Long.toString(file.length() / 1000) + " KB" :
                Long.toString(file.length() / 1000000) + " MB";
        return fileSize;
    }

    private static String $(String key) {
        return messages.getString(key);
    }

    public static File importFile(MenuBar menubar) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter($("filechooserAllFiles"), "*.*"));
        fileChooser.setTitle($("filechooserImportTitle"));
        return fileChooser.showOpenDialog(menubar.getScene().getWindow());
    }

    public static String getPath(File file) {
        return file.toURI().toString();
    }

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
