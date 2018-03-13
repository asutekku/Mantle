package mantle.util.controllers;

import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;

import java.io.File;

public class DnD {

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
