package chess.logic.piecemovers;

import chess.logic.piecemovers.BishopMover;
import chess.logic.piecemovers.QueenMover;
import chess.logic.piecemovers.RookMover;
import chess.logic.piecemovers.KingMover;
import chess.logic.board.ChessBoard;
import chess.logic.board.Player;
import chess.logic.board.Square;
import chess.logic.board.ChessBoardInitializer;
import static chess.logic.board.ChessBoardInitializer.putPieceOnBoard;
import chess.logic.board.EmptyBoardInitializer;
import chess.logic.board.SquareTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author sami
 */
public class KingTest {

    private KingMover king;
    private ChessBoard board;
    private ChessBoardInitializer init;

    public KingTest() {
    }

    @Before
    public void setUp() {
        board = new ChessBoard();
        init = new EmptyBoardInitializer();
        init.initialise(board);
        king = new KingMover(board.getSquare(2, 3), Player.WHITE);
        putPieceOnBoard(board, king);
    }

    @Test
    public void startingColumnCorrect() {
        assertEquals(2, king.getLocation().getColumn());
    }

    @Test
    public void startingRowCorrect() {
        assertEquals(3, king.getLocation().getRow());
    }

    @Test
    public void kingCannotStayStillWhenMoving() {
        assertFalse(king.possibleMoves(board).contains(new Square(2, 3)));
    }

    @Test
    public void kingCanMoveToEveryNeighboringSquare() {
        int[] columns = new int[]{3, 2, 1, 3, 1, 3, 2, 1};
        int[] rows = new int[]{4, 4, 4, 3, 3, 2, 2, 2};

        SquareTest.testMultipleSquares(columns, rows, king.possibleMoves(board));
    }

    @Test
    public void kingCannotMoveOutOfBoard() {
        init.initialise(board);
        king = new KingMover(board.getSquare(0, 0), Player.WHITE);
        putPieceOnBoard(board, king);

        assertFalse(king.possibleMoves(board).contains(new Square(-1, 0)));
        assertFalse(king.possibleMoves(board).contains(new Square(0, -1)));
    }

    @Test
    public void kingCannotMoveToThreatenedSquare() {
        init.initialise(board);
        putPieceOnBoard(board, king);
        QueenMover q = new QueenMover(board.getSquare(3, 5), Player.BLACK);
        putPieceOnBoard(board, q);
        board.updateThreatenedSquares(Player.BLACK);

        q.threatenedSquares(board).stream().forEach(i -> {
            assertFalse(king.possibleMoves(board).contains(i));
        });
    }

    @Test
    public void kingCanTakeOpposingUnprotectedPieceThatChecksIt() {
        putPieceOnBoard(board, new QueenMover(board.getSquare(2, 4), Player.BLACK));
        board.updateThreatenedSquares(Player.BLACK);
        assertTrue(king.possibleMoves(board).contains(board.getSquare(2, 4)));
    }

    @Test
    public void kingCannotTakeProtectedPieces() {
        putPieceOnBoard(board, new QueenMover(board.getSquare(2, 4), Player.BLACK));
        putPieceOnBoard(board, new BishopMover(board.getSquare(3, 5), Player.BLACK));
        board.updateThreatenedSquares(Player.BLACK);

        assertFalse(king.possibleMoves(board).contains(board.getSquare(2, 4)));
    }

    @Test
    public void kingCanCastleKingSideIfAllRequirementsAreMet() {
        KingMover blackKing = new KingMover(board.getSquare(4, 7), Player.BLACK);
        putPieceOnBoard(board, blackKing);
        putPieceOnBoard(board, new RookMover(board.getSquare(7, 7), Player.BLACK));
        assertTrue(blackKing.possibleMoves(board).contains(board.getSquare(6, 7)));
    }

    @Test
    public void kingCanCastleQueenSideIfAllRequirementsAreMet() {
        KingMover blackKing = new KingMover(board.getSquare(4, 7), Player.BLACK);
        putPieceOnBoard(board, blackKing);
        putPieceOnBoard(board, new RookMover(board.getSquare(0, 7), Player.BLACK));
        assertTrue(blackKing.possibleMoves(board).contains(board.getSquare(2, 7)));
    }

    @Test
    public void whenCastlingKingSideChosenRookIsAlsoMovedToCorrectSquare() {
        KingMover blackKing = new KingMover(board.getSquare(4, 7), Player.BLACK);
        RookMover blackRook = new RookMover(board.getSquare(7, 7), Player.BLACK);
        putPieceOnBoard(board, blackKing);
        putPieceOnBoard(board, blackRook);
        blackKing.move(board.getSquare(6, 7), board);
        assertEquals(board.getSquare(5, 7), blackRook.getLocation());
    }

    @Test
    public void whenCastlingQueenSideChosenRookIsAlsoMovedToCorrectSquare() {
        KingMover blackKing = new KingMover(board.getSquare(4, 7), Player.BLACK);
        RookMover blackRook = new RookMover(board.getSquare(0, 7), Player.BLACK);
        putPieceOnBoard(board, blackKing);
        putPieceOnBoard(board, blackRook);
        blackKing.move(board.getSquare(2, 7), board);
        assertEquals(board.getSquare(3, 7), blackRook.getLocation());
    }
}
