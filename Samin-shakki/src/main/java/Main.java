
import chess.gui.GraphicalUserInterface;
import chess.logic.board.chessboardinitializers.StandardBoardInitializer;
import chess.logic.game.Game;
import chess.logic.game.MovementLogic;
import chess.logic.inputprocessing.InputProcessor;

/**
 *
 * @author sami
 */
public class Main {

    public static void main(String[] args) {
        Game game = new Game(new StandardBoardInitializer(), new MovementLogic());
        InputProcessor guiLogic = new InputProcessor();
        GraphicalUserInterface gui = new GraphicalUserInterface(guiLogic, game);
        gui.run();
    }
}
