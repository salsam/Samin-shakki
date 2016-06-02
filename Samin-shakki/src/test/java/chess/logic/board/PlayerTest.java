package chess.logic.board;

import static chess.logic.board.Player.getOpponent;
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
public class PlayerTest {

    public PlayerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getOpponentReturnBlackIfPlayerIsWhite() {
        assertEquals(Player.BLACK, getOpponent(Player.WHITE));
    }

    @Test
    public void getOpponentReturnsWhiteIfPlayerIsWhite() {
        assertEquals(Player.WHITE, getOpponent(Player.BLACK));
    }
}
