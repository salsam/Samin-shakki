package logic.assistance;

import logic.pieces.Pawn;
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
        Pawn pawn = new Pawn(2, 3, Player.WHITE);
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
}
