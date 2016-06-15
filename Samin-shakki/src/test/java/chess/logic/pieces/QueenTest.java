package chess.logic.pieces;

import chess.logic.board.Player;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sami
 */
public class QueenTest {

    private Queen queen;

    public QueenTest() {
    }

    @Before
    public void setUp() {
        queen = new Queen(2, 3, Player.BLACK);
    }

    @Test
    public void cloneReturnsDifferentQueen() {
        assertFalse(queen == queen.clone());
    }

    @Test
    public void cloneReturnsIdenticalQueen() {
        assertTrue(queen.equals(queen.clone()));
    }
}
