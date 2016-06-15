package chess.logic.pieces;

import chess.logic.board.Player;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author sami
 */
public class BishopTest {

    private Bishop bishop;

    public BishopTest() {
    }

    @Before
    public void setUp() {
        bishop = new Bishop(2, 3, Player.BLACK);
    }

    @Test
    public void cloneReturnsDifferentBishop() {
        assertFalse(bishop == bishop.clone());
    }

    @Test
    public void cloneReturnsIdenticalBishop() {
        assertTrue(bishop.equals(bishop.clone()));
    }

}
