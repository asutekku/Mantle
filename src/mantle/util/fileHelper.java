package mantle.util;

import java.io.File;

public class fileHelper {

    public static String getSize(File file) {
        String fileSize;
        fileSize = file.length() < 1000000 ?
                Long.toString(file.length() / 1000) + " KB" :
                Long.toString(file.length() / 1000000) + " MB";
        return fileSize;
    }

    public static String getPath(File file) {
        return file.toURI().toString();
    }

}
