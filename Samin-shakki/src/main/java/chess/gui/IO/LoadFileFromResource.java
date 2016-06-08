package chess.gui.IO;

import java.io.File;

/**
 *
 * @author samisalo
 */
public class LoadFileFromResource {

    public static File getFile(String fileName) {
        ClassLoader classLoader = LoadFileFromResource.class.getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return file;
    }
}
