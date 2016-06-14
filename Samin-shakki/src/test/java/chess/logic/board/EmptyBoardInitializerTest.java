package chess.logic.board;

import chess.logic.board.chessboardinitializers.ChessBoardInitializer;
import chess.logic.board.chessboardinitializers.EmptyBoardInitializer;
import chess.logic.board.chessboardinitializers.StandardBoardInitializer;
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
        stdinit.initialize(board);
        init.initialize(board);

        for (int i = 0; i < board.getTable().length; i++) {
            for (int j = 0; j < board.getTable()[0].length; j++) {
                assertFalse(board.getSquare(i, j).containsAPiece());
            }
        }
    }

    @Test
    public void initializerClearsAllPiecesFromPlayersToo() {
        ChessBoard board = new ChessBoard(new MovementLogic());
        ChessBoardInitializer stdinit = new StandardBoardInitializer();
        stdinit.initialize(board);
        init.initialize(board);

        assertTrue(board.getPieces(Player.WHITE).isEmpty());
        assertTrue(board.getPieces(Player.BLACK).isEmpty());
    }
}
