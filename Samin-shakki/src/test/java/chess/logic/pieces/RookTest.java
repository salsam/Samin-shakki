package chess.logic.pieces;

import chess.logic.board.Player;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sami
 */
public class RookTest {

    private Rook rook;

    public RookTest() {
    }

    @Before
    public void setUp() {
        rook = new Rook(7, 5, Player.WHITE);
    }

    @Test
    public void rooksAreEqualIfTheyAreInSameLocationWithSameOwnerAndHasBeenMoved() {
        assertTrue(rook.equals(new Rook(7, 5, Player.WHITE)));
    }

    @Test
    public void rooksAreNotEqualIfOneHasBeenMovedAndOtherHasNot() {
        rook.setHasBeenMoved(true);
        assertFalse(rook.equals(new Rook(7, 5, Player.WHITE)));
    }

    @Test
    public void cloneReturnsDifferentRook() {
        assertFalse(rook == rook.clone());
    }

    @Test
    public void cloneReturnsIdenticalRook() {
        assertTrue(rook.equals(rook.clone()));
    }
}
