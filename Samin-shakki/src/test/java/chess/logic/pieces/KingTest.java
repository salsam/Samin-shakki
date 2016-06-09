package chess.logic.pieces;

import chess.logic.board.ChessBoardLogic;
import java.util.Set;
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

    private King king;
    private ChessBoardLogic board;
    private ChessBoardInitializer init;
    private Set<Square> possibleMoves;

    public KingTest() {
    }

    @Before
    public void setUp() {
        board = new ChessBoardLogic();
        init = new EmptyBoardInitializer();
        init.initialise(board);
        king = new King(board.getSquare(2, 3), Player.WHITE);
        putPieceOnBoard(board, king);
        possibleMoves = king.possibleMoves(board);
    }

    @Test
    public void startingcolumnCorrect() {
        assertEquals(2, king.location.getcolumn());
    }

    @Test
    public void startingrowCorrect() {
        assertEquals(3, king.location.getrow());
    }

    @Test
    public void kingCannotStayStillWhenMoving() {
        assertFalse(possibleMoves.contains(new Square(2, 3)));
    }

    @Test
    public void kingCanMoveToEveryNeighboringSquare() {
        int[] columns = new int[]{3, 2, 1, 3, 1, 3, 2, 1};
        int[] rows = new int[]{4, 4, 4, 3, 3, 2, 2, 2};

        SquareTest.testMultipleSquares(columns, rows, possibleMoves);
    }

    @Test
    public void kingCannotMoveOutOfBoard() {
        init.initialise(board);
        king = new King(board.getSquare(0, 0), Player.WHITE);
        putPieceOnBoard(board, king);
        possibleMoves = king.possibleMoves(board);

        assertFalse(possibleMoves.contains(new Square(-1, 0)));
        assertFalse(possibleMoves.contains(new Square(0, -1)));
    }

    @Test
    public void kingCannotMoveToThreatenedSquare() {
        init.initialise(board);
        putPieceOnBoard(board, king);
        Queen q = new Queen(board.getSquare(3, 5), Player.BLACK);
        putPieceOnBoard(board, q);
        board.updateThreatenedSquares(Player.BLACK);
        possibleMoves = king.possibleMoves(board);

        q.threatenedSquares(board).stream().forEach(i -> {
            assertFalse(possibleMoves.contains(i));
        });
    }

    @Test
    public void kingCanTakeOpposingUnprotectedQueen() {
        putPieceOnBoard(board, new Queen(board.getSquare(2, 4), Player.BLACK));
        assertTrue(king.possibleMoves(board).contains(board.getSquare(2, 4)));
    }
}
