
import chess.logic.board.StandardBoardInitializer;
import chess.logic.game.Game;

/**
 *
 * @author sami
 */
public class Main {

    public static void main(String[] args) {
        Game g = new Game(new StandardBoardInitializer());
        g.start();
    }
}
