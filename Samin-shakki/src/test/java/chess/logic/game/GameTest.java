package chess.logic.game;

import chess.logic.board.StandardBoardInitializer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author sami
 */
public class GameTest {

    private Game game;

    public GameTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        game = new Game(new StandardBoardInitializer());
    }

    @After
    public void tearDown() {
    }
}
