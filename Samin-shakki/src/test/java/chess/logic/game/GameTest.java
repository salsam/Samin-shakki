package chess.logic.game;

import chess.logic.board.ChessBoard;
import chess.logic.board.Player;
import static chess.logic.board.chessboardinitializers.ChessBoardInitializer.putPieceOnBoard;
import chess.logic.board.chessboardinitializers.EmptyBoardInitializer;
import chess.logic.pieces.King;
import chess.logic.pieces.Queen;
import chess.logic.pieces.Rook;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author sami
 */
public class GameTest {

    private Game game;

    public GameTest() {
    }

    @Before
    public void setUp() {
        game = new Game(new EmptyBoardInitializer(), new MovementLogic());
        ChessBoard board = game.getChessBoard();
        putPieceOnBoard(board, new King(0, 0, Player.WHITE));
    }

    @Test
    public void checkIfCheckedReturnFalseIfKingIsNotChecked() {
        assertFalse(game.checkIfChecked(Player.WHITE));
    }

    @Test
    public void checkIfCheckedReturnsTrueIfKingIsChecked() {
        ChessBoard board = game.getChessBoard();
        putPieceOnBoard(board, new Queen(1, 1, Player.BLACK));
        assertTrue(game.checkIfChecked(Player.WHITE));
    }

    @Test
    public void checkMateIsFalseIfNotChecked() {
        assertFalse(game.checkMate(Player.WHITE));
    }

    @Test
    public void checkMateTrueIfKingCheckedAndCheckCannotBePrevented() {
        ChessBoard board = game.getChessBoard();
        putPieceOnBoard(board, new Queen(1, 1, Player.BLACK));
        putPieceOnBoard(board, new King(2, 2, Player.BLACK));
        assertTrue(game.checkMate(Player.WHITE));
    }

    @Test
    public void checkMateFalseIfKingCheckedButCheckingPieceCanBeTaken() {
        ChessBoard board = game.getChessBoard();
        putPieceOnBoard(board, new Queen(1, 0, Player.BLACK));
        assertFalse(game.checkMate(Player.WHITE));
    }

    @Test
    public void checkMateFalseIfCheckCanBeBlocked() {
        ChessBoard board = game.getChessBoard();
        putPieceOnBoard(board, new Queen(6, 6, Player.BLACK));
        putPieceOnBoard(board, new Rook(1, 6, Player.BLACK));
        putPieceOnBoard(board, new Rook(6, 1, Player.BLACK));
        putPieceOnBoard(board, new Rook(4, 1, Player.WHITE));

    }
}
