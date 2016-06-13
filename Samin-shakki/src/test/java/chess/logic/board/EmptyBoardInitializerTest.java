package chess.logic.board;

import chess.logic.game.MovementLogic;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sami
 */
public class EmptyBoardInitializerTest {

    private static ChessBoardInitializer init;

    public EmptyBoardInitializerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        init = new EmptyBoardInitializer();
    }

    @Test
    public void initializerClearsBoardOfAllPiecesWhenInitializingABoard() {
        ChessBoard board = new ChessBoard(new MovementLogic());
        ChessBoardInitializer stdinit = new StandardBoardInitializer();
        stdinit.initialise(board);
        init.initialise(board);

        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[0].length; j++) {
                assertFalse(board.getSquare(i, j).containsAPiece());
            }
        }
    }

    @Test
    public void initializerClearsAllPiecesFromPlayersToo() {
        ChessBoard board = new ChessBoard(new MovementLogic());
        ChessBoardInitializer stdinit = new StandardBoardInitializer();
        stdinit.initialise(board);
        init.initialise(board);

        assertTrue(board.getPieces(Player.WHITE).isEmpty());
        assertTrue(board.getPieces(Player.BLACK).isEmpty());
    }
}
