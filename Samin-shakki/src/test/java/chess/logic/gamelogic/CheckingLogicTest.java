package chess.logic.cllogic;

import chess.domain.GameSituation;
import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardCopier;
import chess.domain.board.Player;
import chess.domain.pieces.King;
import chess.domain.pieces.Pawn;
import chess.domain.pieces.Queen;
import chess.domain.pieces.Rook;
import chess.logic.board.chessboardinitializers.ChessBoardInitializer;
import static chess.logic.board.chessboardinitializers.ChessBoardInitializer.putPieceOnBoard;
import chess.logic.board.chessboardinitializers.StandardBoardInitializer;
import chess.logic.gamelogic.CheckingLogic;
import chess.logic.movementlogic.MovementLogic;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sami
 */
public class CheckingLogicTest {

    private CheckingLogic cl;
    private ChessBoard board;

    public CheckingLogicTest() {
    }

    @Before
    public void setUp() {
        board = new ChessBoard(new MovementLogic());
        cl = new CheckingLogic(board);
    }

    @Test
    public void checkIfCheckedReturnFalseIfKingIsNotChecked() {
        assertFalse(cl.checkIfChecked(Player.WHITE));
    }

    @Test
    public void checkIfCheckedReturnsTrueIfKingIsChecked() {
        putPieceOnBoard(board, new Queen(1, 1, Player.BLACK, "bq"));
        assertTrue(cl.checkIfChecked(Player.WHITE));
    }

    @Test
    public void checkMateIsFalseIfNotChecked() {
        assertFalse(cl.checkMate(Player.WHITE));
    }

    @Test
    public void checkMateTrueIfKingCheckedAndCheckCannotBePrevented() {
        putPieceOnBoard(board, new Queen(1, 1, Player.BLACK, "bq"));
        putPieceOnBoard(board, new King(2, 2, Player.BLACK, "bk"));
        assertTrue(cl.checkMate(Player.WHITE));
    }

    @Test
    public void checkMateFalseIfKingCheckedButCheckingPieceCanBeTaken() {
        putPieceOnBoard(board, new Queen(1, 1, Player.BLACK, "bq"));
        assertFalse(cl.checkMate(Player.WHITE));
    }

    @Test
    public void chessBoardIsNotAffectedByCheckingIfKingIsCheckMated() {
        ChessBoard copy = ChessBoardCopier.copy(board);
        putPieceOnBoard(board, new Queen(6, 6, Player.BLACK, "bq"));
        putPieceOnBoard(board, new Rook(1, 6, Player.BLACK, "br1"));
        putPieceOnBoard(board, new Rook(6, 1, Player.BLACK, "br2"));
        putPieceOnBoard(board, new Rook(4, 1, Player.WHITE, "wr"));
        cl.checkMate(Player.WHITE);
        assertTrue(board.getTable() == board.getTable());
        assertTrue(Arrays.deepEquals(copy.getTable(), board.getTable()));
    }

    @Test
    public void chessBoardIsNotAffectedByCheckingIfKingIsCheckMatedInComplexSituation() {
        ChessBoardInitializer stdinit = new StandardBoardInitializer();
        stdinit.initialize(board);
        MovementLogic mvl = board.getMovementLogic();

        mvl.move(board.getSquare(4, 1).getPiece(), board.getSquare(4, 2), board);
        mvl.move(board.getSquare(5, 6).getPiece(), board.getSquare(5, 5), board);
        mvl.move(board.getSquare(5, 0).getPiece(), board.getSquare(1, 4), board);
        mvl.move(board.getSquare(3, 6).getPiece(), board.getSquare(3, 5), board);
        mvl.move(board.getSquare(3, 0).getPiece(), board.getSquare(7, 4), board);
        mvl.move(board.getSquare(5, 5).getPiece(), board.getSquare(5, 4), board);
        mvl.move(board.getSquare(7, 4).getPiece(), board.getSquare(4, 7), board);
        board.updateThreatenedSquares(Player.WHITE);
        ChessBoard backUp = ChessBoardCopier.copy(board);

        assertTrue(cl.checkMate(Player.BLACK));
        assertTrue(Arrays.deepEquals(backUp.getTable(), board.getTable()));
    }

    @Test
    public void checkMateFalseIfCheckCanBeBlocked() {
        putPieceOnBoard(board, new Queen(6, 6, Player.BLACK, "bq"));
        putPieceOnBoard(board, new Rook(1, 6, Player.BLACK, "br1"));
        putPieceOnBoard(board, new Rook(6, 1, Player.BLACK, "br2"));
        putPieceOnBoard(board, new Rook(4, 1, Player.WHITE, "wr"));
        assertFalse(cl.checkMate(Player.WHITE));
    }

    @Test
    public void checkMateFalseIfKingCanMoveToUnthreatenedSquare() {
        putPieceOnBoard(board, new Pawn(1, 1, Player.BLACK, "bp1"));
        putPieceOnBoard(board, new Pawn(2, 2, Player.BLACK, "bp2"));
        assertFalse(cl.checkMate(Player.WHITE));
    }

    @Test
    public void checkMateFalseInComplexSituationWhereKingThreatenedByProtectedPieceButCanBeAvoided() {
        MovementLogic mvl = board.getMovementLogic();
        King whiteKing = board.getKings().get(Player.WHITE);
        Pawn whitePawn = (Pawn) board.getSquare(5, 1).getPiece();
        Pawn blackPawn1 = (Pawn) board.getSquare(5, 6).getPiece();
        Pawn blackPawn2 = (Pawn) board.getSquare(4, 6).getPiece();

        mvl.move(whitePawn, board.getSquare(5, 2), board);
        mvl.move(blackPawn1, board.getSquare(5, 4), board);
        mvl.move(whiteKing, board.getSquare(5, 1), board);
        mvl.move(blackPawn2, board.getSquare(4, 4), board);
        mvl.move(whiteKing, board.getSquare(6, 2), board);
        mvl.move(blackPawn1, board.getSquare(5, 3), board);
        assertFalse(cl.checkMate(Player.WHITE));
    }

    @Test
    public void staleMateTrueIfKingNotCheckedAndThereIsNoLegalMoves() {
        putPieceOnBoard(board, new Rook(1, 7, Player.BLACK, "br1"));
        putPieceOnBoard(board, new Rook(7, 1, Player.BLACK, "br2"));
        board.updateThreatenedSquares(Player.BLACK);
        assertTrue(cl.stalemate(Player.WHITE));
    }

    @Test
    public void staleMateFalseIfKingCanMoveLegally() {
        assertFalse(cl.stalemate(Player.WHITE));
    }

    @Test
    public void staleMateFalseIfThereIsSomeOtherPieceThatCanMoveLegally() {
        putPieceOnBoard(board, new Rook(1, 7, Player.BLACK, "br1"));
        putPieceOnBoard(board, new Rook(7, 1, Player.BLACK, "br2"));
        putPieceOnBoard(board, new Pawn(4, 4, Player.WHITE, "wp"));
        board.updateThreatenedSquares(Player.BLACK);
        assertFalse(cl.stalemate(Player.WHITE));
    }
}
