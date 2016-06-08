
import chess.gui.GameWindow;
import chess.logic.board.StandardBoardInitializer;
import chess.logic.game.Game;
import java.io.IOException;

/**
 *
 * @author sami
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Game game = new Game(new StandardBoardInitializer());
        GameWindow window = new GameWindow(game);
    }
}
