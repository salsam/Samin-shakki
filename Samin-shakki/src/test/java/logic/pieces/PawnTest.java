package logic.pieces;

import logic.assistance.Player;
import logic.assistance.Square;
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
public class PawnTest {

    private Pawn pawn;

    public PawnTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        pawn = new Pawn(2, 1, Player.WHITE);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void startingColumnRight() {
        assertEquals(2, pawn.getLocation().getColumn());
    }

    @Test
    public void startingRowRight() {
        assertEquals(1, pawn.getLocation().getRow());
    }

    @Test
    public void columnRightAfterMovingOneStepUp() {
        pawn.move(new Square(2, 2));
        assertEquals(2, pawn.getLocation().getColumn());
    }

    @Test
    public void rowRightAfterMovingOneStepUp() {
        pawn.move(new Square(2, 2));
        assertEquals(2, pawn.getLocation().getRow());
    }
//
//    @Test
//    public void pawnCannotMoveThreeStepsUp() {
//        assertFalse(pawn.possibleMoves().contains(new Square(2, 4)));
//    }
//
//    @Test
//    public void pawnCannotMoveSideways() {
//        assertFalse(pawn.possibleMoves().contains(new Square(1, 1)));
//    }
//
//    @Test
//    public void pawnCanMoveTwoStepsUpFromStartingLocation() {
//        assertTrue(pawn.possibleMoves().contains(new Square(2, 3)));
//    }
//
//    @Test
//    public void pawnCannotMoveTwoStepsUpFromOtherLocations() {
//        pawn = new Pawn(2, 2, Player.WHITE);
//        assertFalse(pawn.possibleMoves().contains(new Square(2, 4)));
//    }
}
