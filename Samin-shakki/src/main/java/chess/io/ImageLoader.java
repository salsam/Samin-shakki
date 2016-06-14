package chess.io;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * This class loads images and other files from resources folder and saves them
 * in corresponding data type.
 *
 * @author sami
 */
public class ImageLoader {

    /**
     * Uses ClassLoader to load file matching given name from resources folder
     * and then returns it.
     *
     * @param fileName name of file t load
     * @return file that was found with matching name. If nothing is found
     * returns null.
     */
    public static File getFile(String fileName) {
        ClassLoader classLoader = ImageLoader.class.getClass().getClassLoader();
        File file = new File(classLoader.getSystemResource(fileName).getFile());
        return file;
    }

    /**
     * Searches for image with the name given as parameter and returns it in
     * BufferedImage format. If nothing is found, returns null.
     *
     * @param fileName name of image to load
     * @return image that was found, if nothing found returns null.
     */
    public static BufferedImage getImage(String fileName) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(getFile(fileName));
        } catch (Exception e) {
        }
        return img;
    }
}
