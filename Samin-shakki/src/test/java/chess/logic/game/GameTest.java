package chess.logic.game;

import chess.logic.movementlogic.MovementLogic;
import chess.logic.board.ChessBoard;
import chess.logic.board.Player;
import static chess.logic.board.chessboardinitializers.ChessBoardInitializer.putPieceOnBoard;
import chess.logic.board.chessboardinitializers.EmptyBoardInitializer;
import chess.logic.board.chessboardinitializers.StandardBoardInitializer;
import chess.logic.pieces.King;
import chess.logic.pieces.Pawn;
import chess.logic.pieces.Queen;
import chess.logic.pieces.Rook;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
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
        putPieceOnBoard(board, new Queen(1, 1, Player.BLACK));
        assertFalse(game.checkMate(Player.WHITE));
    }

    @Test
    public void chessBoardIsNotAffectedByCheckingIfKingIsCheckMated() {
        ChessBoard board = game.getChessBoard();
        putPieceOnBoard(board, new Queen(6, 6, Player.BLACK));
        putPieceOnBoard(board, new Rook(1, 6, Player.BLACK));
        putPieceOnBoard(board, new Rook(6, 1, Player.BLACK));
        putPieceOnBoard(board, new Rook(4, 1, Player.WHITE));
        game.checkMate(Player.WHITE);
        assertFalse(board.getTable() == game.getChessBoard().getTable());
        assertTrue(Arrays.deepEquals(board.getTable(), game.getChessBoard().getTable()));
    }

    @Test
    public void checkMateFalseIfCheckCanBeBlocked() {
        ChessBoard board = game.getChessBoard();
        putPieceOnBoard(board, new Queen(6, 6, Player.BLACK));
        putPieceOnBoard(board, new Rook(1, 6, Player.BLACK));
        putPieceOnBoard(board, new Rook(6, 1, Player.BLACK));
        putPieceOnBoard(board, new Rook(4, 1, Player.WHITE));
        assertFalse(game.checkMate(Player.WHITE));
    }

    @Test
    public void checkMateFalseIfKingCanMoveToUnthreatenedSquare() {
        ChessBoard board = game.getChessBoard();
        putPieceOnBoard(board, new Pawn(1, 1, Player.BLACK));
        putPieceOnBoard(board, new Pawn(2, 2, Player.BLACK));
        assertFalse(game.checkMate(Player.WHITE));
    }

    @Test
    public void checkMateFalseInComplexSituationWhereKingThreatenedByProtectedPieceButCanBeAvoided() {
        game = new Game(new StandardBoardInitializer(), new MovementLogic());
        ChessBoard board = game.getChessBoard();
        MovementLogic mvl = board.getMovementLogic();
        King whiteKing = board.getKings().get(Player.WHITE);
        Pawn whitePawn = (Pawn) board.getSquare(5, 1).getPiece();
        Pawn blackPawn1 = (Pawn) board.getSquare(5, 6).getPiece();
        Pawn blackPawn2 = (Pawn) board.getSquare(4, 6).getPiece();

        mvl.move(whitePawn, board.getSquare(5, 2), board);
        game.nextTurn();
        mvl.move(blackPawn1, board.getSquare(5, 4), board);
        game.nextTurn();
        mvl.move(whiteKing, board.getSquare(5, 1), board);
        game.nextTurn();
        mvl.move(blackPawn2, board.getSquare(4, 4), board);
        game.nextTurn();
        mvl.move(whiteKing, board.getSquare(6, 2), board);
        game.nextTurn();
        mvl.move(blackPawn1, board.getSquare(5, 3), board);
        game.nextTurn();
        assertFalse(game.checkMate(Player.WHITE));
    }

    @Test
    public void whoseTurnReturnsWhiteIfOddTurnAndBlackIfEvenTurn() {
        assertEquals(Player.WHITE, game.whoseTurn());
        game.nextTurn();
        assertEquals(Player.BLACK, game.whoseTurn());
        game.nextTurn();
        assertEquals(Player.WHITE, game.whoseTurn());
    }

    @Test
    public void playersPawnsBecomeNoLongerCapturableEnPassantOnPlayersNextTurn() {
        ChessBoard board = game.getChessBoard();
        Pawn whitePawn = new Pawn(4, 4, Player.WHITE);
        Pawn blackPawn = new Pawn(4, 6, Player.BLACK);
        putPieceOnBoard(board, whitePawn);
        putPieceOnBoard(board, blackPawn);
        board.getMovementLogic().move(whitePawn, board.getSquare(4, 6), board);
        assertTrue(whitePawn.getMovedTwoSquaresLastTurn());
        game.nextTurn();
        game.nextTurn();
        assertFalse(whitePawn.getMovedTwoSquaresLastTurn());
    }

    @Test
    public void staleMateTrueIfKingNotCheckedAndThereIsNoLegalMoves() {
        ChessBoard board = game.getChessBoard();
        putPieceOnBoard(board, new Rook(1, 7, Player.BLACK));
        putPieceOnBoard(board, new Rook(7, 1, Player.BLACK));
        board.updateThreatenedSquares(Player.BLACK);
        assertTrue(game.stalemate(Player.WHITE));
    }

    @Test
    public void staleMateFalseIfKingCanMoveLegally() {
        assertFalse(game.stalemate(Player.WHITE));
    }

    @Test
    public void staleMateFalseIfThereIsSomeOtherPieceThatCanMoveLegally() {
        ChessBoard board = game.getChessBoard();
        putPieceOnBoard(board, new Rook(1, 7, Player.BLACK));
        putPieceOnBoard(board, new Rook(7, 1, Player.BLACK));
        putPieceOnBoard(board, new Pawn(4, 4, Player.WHITE));
        board.updateThreatenedSquares(Player.BLACK);
        assertFalse(game.stalemate(Player.WHITE));
    }
}
