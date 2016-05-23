package chess.pieces;

import chess.pieces.Knight;
import java.util.List;
import chess.board.Player;
import chess.board.Square;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author sami
 */
public class KnightTest {

    private Knight knight;
    private Square[][] board;
    private List<Square> possibleMoves;

    public KnightTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        knight = new Knight(4, 4, Player.WHITE);
        board = PawnTest.emptyBoard();
        board[4][4].setPiece(knight);
        possibleMoves = knight.possibleMoves(board);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void knightCannotStayStillWhenMoving() {
        assertFalse(possibleMoves.contains(new Square(4, 4)));
    }

    @Test
    public void knightCanMoveTwoUpOneRight() {
        assertTrue(possibleMoves.contains(new Square(5, 6)));
    }

    @Test
    public void knightCanMoveTwoUpOneLeft() {
        assertTrue(possibleMoves.contains(new Square(3, 6)));
    }

    @Test
    public void knightCanMoveTwoDownOneRight() {
        assertTrue(possibleMoves.contains(new Square(5, 2)));
    }

    @Test
    public void knightCanMoveTwoDownOneLeft() {
        assertTrue(possibleMoves.contains(new Square(3, 2)));
    }

    @Test
    public void knightCanMoveOneUpTwoRight() {
        assertTrue(possibleMoves.contains(new Square(6, 5)));
    }

    @Test
    public void knightCanMoveOneUpTwoLeft() {
        assertTrue(possibleMoves.contains(new Square(2, 5)));
    }

    @Test
    public void knightCanMoveOneDownTwoRight() {
        assertTrue(possibleMoves.contains(new Square(6, 3)));
    }

    @Test
    public void knightCanMoveOneDownTwoLeft() {
        assertTrue(possibleMoves.contains(new Square(2, 3)));
    }

}
