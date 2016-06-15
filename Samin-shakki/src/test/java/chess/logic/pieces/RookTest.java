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
    public void cloneReturnsDifferentBishop() {
        assertFalse(rook == rook.clone());
    }

    @Test
    public void cloneReturnsIdenticalBishop() {
        assertTrue(rook.equals(rook.clone()));
    }

    @Test
    public void cloneHasBeenMovedIfRookHasBeenMoved() {
        rook.setHasBeenMoved(true);
        Rook clone = (Rook) rook.clone();
        assertTrue(clone.getHasBeenMoved());
    }

    @Test
    public void cloneHasNotBeenMovedIfRookHasNot() {
        Rook clone = (Rook) rook.clone();
        assertFalse(clone.getHasBeenMoved());
    }
}
