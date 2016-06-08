
import chess.gui.IO.LoadFileFromResource;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author sami
 */
public class Main {

    public static void main(String[] args) throws IOException {
//        Game game = new Game(new StandardBoardInitializer());
//        GameWindow window = new GameWindow(game);

        ClassLoader classLoader = LoadFileFromResource.class.getClass().getClassLoader();
        String path = classLoader.getResource("blackBishop1.png").getFile();
        File file = new File(path);
        BufferedImage image = ImageIO.read(file);
    }
}
