package chess.logic.pieces;

import chess.logic.board.Player;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sami
 */
public class KingTest {

    private King king;

    public KingTest() {
    }

    @Before
    public void setUp() {
        king = new King(7, 5, Player.WHITE);
    }

    @Test
    public void cloneReturnsDifferentBishop() {
        assertFalse(king == king.clone());
    }

    @Test
    public void cloneReturnsIdenticalBishop() {
        assertTrue(king.equals(king.clone()));
    }

    @Test
    public void cloneHasBeenMovedIfKingHasBeenMoved() {
        king.setHasBeenMoved(true);
        King clone = (King) king.clone();
        assertTrue(clone.getHasBeenMoved());
    }

    @Test
    public void cloneHasNotBeenMovedIfKingHasNot() {
        King clone = (King) king.clone();
        assertFalse(clone.getHasBeenMoved());
    }
}
