package chess.logic.pieces;

import chess.logic.board.Player;
import org.junit.Before;
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

    @Before
    public void setUp() {
        pawn = new Pawn(7, 5, Player.WHITE);
    }

    @Test
    public void pawnsAreEqualIfTheyAreInSameLocationWithSameOwnerAndHasBeenMoved() {
        assertTrue(pawn.equals(new Pawn(7, 5, Player.WHITE)));
    }

    @Test
    public void pawnsAreNotEqualIfOneHasBeenMovedAndOtherHasNot() {
        pawn.setHasBeenMoved(true);
        assertFalse(pawn.equals(new Pawn(7, 5, Player.WHITE)));
    }

    @Test
    public void pawnAreEqualIfBothMovedTwoSquaresLastTurn() {
        pawn.setHasBeenMoved(true);
        Pawn comp = new Pawn(7, 5, Player.WHITE);
        comp.setHasBeenMoved(true);
        assertTrue(pawn.equals(comp));
    }

    @Test
    public void pawnAreNotEqualIfOnlyOneOfThemMovedTwoSquaresLastTurn() {
        pawn.setHasBeenMoved(true);
        assertFalse(pawn.equals(new Pawn(7, 5, Player.WHITE)));
    }

    @Test
    public void cloneReturnsDifferentPawn() {
        assertFalse(pawn == pawn.clone());
    }

    @Test
    public void cloneReturnsIdenticalPawn() {
        assertTrue(pawn.equals(pawn.clone()));
    }
}
