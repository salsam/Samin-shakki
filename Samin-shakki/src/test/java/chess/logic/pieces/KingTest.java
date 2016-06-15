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
    public void kingsAreEqualIfInSameLocationAndHavingSameHasBeenMovedAndOwner() {
        assertTrue(king.equals(new King(7, 5, Player.WHITE)));
    }

    @Test
    public void kingsAreNotEqualIfOneHasBeenMovedAndOtherHasNot() {
        king.setHasBeenMoved(true);
        assertFalse(king.equals(new King(7, 5, Player.WHITE)));
    }

    @Test
    public void cloneReturnsDifferentKing() {
        assertFalse(king == king.clone());
    }

    @Test
    public void cloneReturnsIdenticalKing() {
        assertTrue(king.equals(king.clone()));
    }
}
