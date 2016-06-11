package chess.logic.game;

import static chess.logic.board.ChessBoardInitializer.putPieceOnBoard;
import chess.logic.board.ChessBoard;
import chess.logic.board.EmptyBoardInitializer;
import chess.logic.board.Player;
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
        game = new Game(new EmptyBoardInitializer());
        ChessBoard board = game.getChessBoard();
        putPieceOnBoard(board, new King(board.getSquare(0, 0), Player.WHITE));
    }

    @Test
    public void checkIfCheckedReturnFalseIfKingIsNotChecked() {
        assertFalse(game.checkIfChecked(Player.WHITE));
    }

    @Test
    public void checkIfCheckedReturnsTrueIfKingIsChecked() {
        ChessBoard board = game.getChessBoard();
        putPieceOnBoard(board, new Queen(board.getSquare(1, 1), Player.BLACK));
        assertTrue(game.checkIfChecked(Player.WHITE));
    }

    @Test
    public void checkMateIsFalseIfNotChecked() {
        assertFalse(game.checkMate(Player.WHITE));
    }

    @Test
    public void checkMateTrueIfKingCheckedAndCheckCannotBePrevented() {
        ChessBoard board = game.getChessBoard();
        putPieceOnBoard(board, new Queen(board.getSquare(1, 1), Player.BLACK));
        putPieceOnBoard(board, new King(board.getSquare(2, 2), Player.BLACK));
        assertTrue(game.checkMate(Player.WHITE));
    }

    @Test
    public void checkMateFalseIfKingCheckedButCheckingPieceCanBeTaken() {
        ChessBoard board = game.getChessBoard();
        putPieceOnBoard(board, new Queen(board.getSquare(1, 0), Player.BLACK));
        assertFalse(game.checkMate(Player.WHITE));
    }

    @Test
    public void checkMateFalseIfCheckCanBeBlocked() {
        ChessBoard board = game.getChessBoard();
        putPieceOnBoard(board, new Queen(board.getSquare(6, 6), Player.BLACK));
        putPieceOnBoard(board, new Rook(board.getSquare(1, 6), Player.BLACK));
        putPieceOnBoard(board, new Rook(board.getSquare(6, 1), Player.BLACK));
        putPieceOnBoard(board, new Rook(board.getSquare(4, 1), Player.WHITE));

    }
}
