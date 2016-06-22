package chess.domain;

import chess.domain.GameSituation;
import chess.logic.movementlogic.MovementLogic;
import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardCopier;
import chess.domain.board.Player;
import chess.logic.board.chessboardinitializers.ChessBoardInitializer;
import static chess.logic.board.chessboardinitializers.ChessBoardInitializer.putPieceOnBoard;
import chess.logic.board.chessboardinitializers.EmptyBoardInitializer;
import chess.logic.board.chessboardinitializers.StandardBoardInitializer;
import chess.domain.pieces.King;
import chess.domain.pieces.Pawn;
import chess.domain.pieces.Queen;
import chess.domain.pieces.Rook;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author sami
 */
public class GameSituationTest {

    private GameSituation game;

    public GameSituationTest() {
    }

    @Before
    public void setUp() {
        game = new GameSituation(new EmptyBoardInitializer(), new MovementLogic());
        ChessBoard board = game.getChessBoard();
        putPieceOnBoard(board, new King(0, 0, Player.WHITE, "wk"));
    }

    @Test
    public void checkIfCheckedReturnFalseIfKingIsNotChecked() {
        assertFalse(game.checkIfChecked(Player.WHITE));
    }

    @Test
    public void checkIfCheckedReturnsTrueIfKingIsChecked() {
        ChessBoard board = game.getChessBoard();
        putPieceOnBoard(board, new Queen(1, 1, Player.BLACK, "bq"));
        assertTrue(game.checkIfChecked(Player.WHITE));
    }

    @Test
    public void checkMateIsFalseIfNotChecked() {
        assertFalse(game.checkMate(Player.WHITE));
    }

    @Test
    public void checkMateTrueIfKingCheckedAndCheckCannotBePrevented() {
        ChessBoard board = game.getChessBoard();
        putPieceOnBoard(board, new Queen(1, 1, Player.BLACK, "bq"));
        putPieceOnBoard(board, new King(2, 2, Player.BLACK, "bk"));
        assertTrue(game.checkMate(Player.WHITE));
    }

    @Test
    public void checkMateFalseIfKingCheckedButCheckingPieceCanBeTaken() {
        ChessBoard board = game.getChessBoard();
        putPieceOnBoard(board, new Queen(1, 1, Player.BLACK, "bq"));
        assertFalse(game.checkMate(Player.WHITE));
    }

    @Test
    public void chessBoardIsNotAffectedByCheckingIfKingIsCheckMated() {
        ChessBoard board = game.getChessBoard();
        ChessBoard copy = ChessBoardCopier.copy(board);
        putPieceOnBoard(board, new Queen(6, 6, Player.BLACK, "bq"));
        putPieceOnBoard(board, new Rook(1, 6, Player.BLACK, "br1"));
        putPieceOnBoard(board, new Rook(6, 1, Player.BLACK, "br2"));
        putPieceOnBoard(board, new Rook(4, 1, Player.WHITE, "wr"));
        game.checkMate(Player.WHITE);
        assertTrue(board.getTable() == game.getChessBoard().getTable());
        assertTrue(Arrays.deepEquals(copy.getTable(), game.getChessBoard().getTable()));
    }

    @Test
    public void chessBoardIsNotAffectedByCheckingIfKingIsCheckMatedInComplexSituation() {
        ChessBoardInitializer stdinit = new StandardBoardInitializer();
        stdinit.initialize(game.getChessBoard());
        MovementLogic mvl = game.getChessBoard().getMovementLogic();

        mvl.move(game.getChessBoard().getSquare(4, 1).getPiece(), game.getChessBoard().getSquare(4, 2), game.getChessBoard());
        mvl.move(game.getChessBoard().getSquare(5, 6).getPiece(), game.getChessBoard().getSquare(5, 5), game.getChessBoard());
        mvl.move(game.getChessBoard().getSquare(5, 0).getPiece(), game.getChessBoard().getSquare(1, 4), game.getChessBoard());
        mvl.move(game.getChessBoard().getSquare(3, 6).getPiece(), game.getChessBoard().getSquare(3, 5), game.getChessBoard());
        mvl.move(game.getChessBoard().getSquare(3, 0).getPiece(), game.getChessBoard().getSquare(7, 4), game.getChessBoard());
        mvl.move(game.getChessBoard().getSquare(5, 5).getPiece(), game.getChessBoard().getSquare(5, 4), game.getChessBoard());
        mvl.move(game.getChessBoard().getSquare(7, 4).getPiece(), game.getChessBoard().getSquare(4, 7), game.getChessBoard());
        game.getChessBoard().updateThreatenedSquares(Player.WHITE);
        ChessBoard backUp = ChessBoardCopier.copy(game.getChessBoard());
        
//        assertTrue(game.checkMate(Player.BLACK));
        assertTrue(Arrays.deepEquals(backUp.getTable(), game.getChessBoard().getTable()));
    }

    @Test
    public void checkMateFalseIfCheckCanBeBlocked() {
        ChessBoard board = game.getChessBoard();
        putPieceOnBoard(board, new Queen(6, 6, Player.BLACK, "bq"));
        putPieceOnBoard(board, new Rook(1, 6, Player.BLACK, "br1"));
        putPieceOnBoard(board, new Rook(6, 1, Player.BLACK, "br2"));
        putPieceOnBoard(board, new Rook(4, 1, Player.WHITE, "wr"));
        assertFalse(game.checkMate(Player.WHITE));
    }

    @Test
    public void checkMateFalseIfKingCanMoveToUnthreatenedSquare() {
        ChessBoard board = game.getChessBoard();
        putPieceOnBoard(board, new Pawn(1, 1, Player.BLACK, "bp1"));
        putPieceOnBoard(board, new Pawn(2, 2, Player.BLACK, "bp2"));
        assertFalse(game.checkMate(Player.WHITE));
    }

    @Test
    public void checkMateFalseInComplexSituationWhereKingThreatenedByProtectedPieceButCanBeAvoided() {
        game = new GameSituation(new StandardBoardInitializer(), new MovementLogic());
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
        Pawn whitePawn = new Pawn(4, 4, Player.WHITE, "wp");
        Pawn blackPawn = new Pawn(4, 6, Player.BLACK, "bp");
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
        putPieceOnBoard(board, new Rook(1, 7, Player.BLACK, "br1"));
        putPieceOnBoard(board, new Rook(7, 1, Player.BLACK, "br2"));
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
        putPieceOnBoard(board, new Rook(1, 7, Player.BLACK, "br1"));
        putPieceOnBoard(board, new Rook(7, 1, Player.BLACK, "br2"));
        putPieceOnBoard(board, new Pawn(4, 4, Player.WHITE, "wp"));
        board.updateThreatenedSquares(Player.BLACK);
        assertFalse(game.stalemate(Player.WHITE));
    }
}
