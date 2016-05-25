package chess.board;

import chess.pieces.Pawn;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sami
 */
public class SquareTest {

    public static void testMultipleSquares(int[] files, int[] ranks, List<Square> possibleMoves) {
        List<Square> realPossibilities = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            realPossibilities.add(new Square(files[i], ranks[i]));
        }
        for (Square sq : realPossibilities) {
            assertTrue(possibleMoves.contains(sq));
        }
    }

    private Square square;

    public SquareTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        square = new Square(2, 3);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void returnCorrectColumn() {
        assertEquals(2, square.getFile());
    }

    @Test
    public void returnCorrectRow() {
        assertEquals(3, square.getRank());
    }

    @Test
    public void pieceNullIfNoPieceOnThisSquare() {
        assertEquals(null, square.getPiece());
    }

    @Test
    public void pieceCorrectIfNotNull() {
        Pawn pawn = new Pawn(new Square(2, 1), Player.WHITE);
        square.setPiece(pawn);
        assertEquals(pawn, square.getPiece());
    }

    @Test
    public void twoSquaresAreEqualIfSameColumnAndRow() {
        assertTrue(square.equals(new Square(2, 3)));
    }

    @Test
    public void twoSquaresAreNotEqualIfDifferentColumn() {
        assertFalse(square.equals(new Square(3, 3)));
    }

    @Test
    public void twoSquaresAreNotEqualIfDifferentRow() {
        assertFalse(square.equals(new Square(2, 2)));
    }

    @Test
    public void intIsNotASquare() {
        assertFalse(square.equals(1));
    }

    @Test
    public void toStringIsInCorrectFormat() {
        assertEquals("(2,3)", square.toString());
    }
}
