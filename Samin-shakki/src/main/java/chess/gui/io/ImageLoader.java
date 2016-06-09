package chess.gui.io;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author samisalo
 */
public class ImageLoader {

    public static File getFile(String fileName) {
        ClassLoader classLoader = ImageLoader.class.getClass().getClassLoader();
        File file = new File(classLoader.getSystemResource(fileName).getFile());
        return file;
    }

    public static BufferedImage getImage(String fileName) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(getFile(fileName));
        } catch (Exception e) {
        }
        return img;
    }
}
