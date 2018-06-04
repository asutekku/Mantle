package mantle.util.controllers;

import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;

import java.io.File;

/**
 * Drag and Drop controller
 * Right now for some unknown reason it does not work
 */
public class DnD {

    /**
     * Should return the path of the file dropped
     * Does not work :(
     *
     * @param e event :D
     * @return Path of the asset
     */
    public static String mouseDragDropped(final DragEvent e) {
        final Dragboard db = e.getDragboard();
        boolean success = false;
        String path = "";
        if (db.hasFiles()) {
            success = true;
            final File file = db.getFiles().get(0);
            path = file.getAbsolutePath();
        }
        e.setDropCompleted(success);
        e.consume();
        return path;
    }
}
