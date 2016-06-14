
import chess.gui.GraphicalUserInterface;
import chess.logic.board.chessboardinitializers.StandardBoardInitializer;
import chess.logic.game.Game;
import chess.logic.game.MovementLogic;
import chess.logic.guilogic.GUILogic;

/**
 *
 * @author sami
 */
public class Main {

    public static void main(String[] args) {
        Game game = new Game(new StandardBoardInitializer(), new MovementLogic());
        GUILogic guiLogic = new GUILogic();
        GraphicalUserInterface gui = new GraphicalUserInterface(guiLogic, game);
        gui.run();
    }
}
