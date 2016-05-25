package chess.board;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sami
 */
public class ChessBoardTest {

    private ChessBoard board;

    public ChessBoardTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        board = new ChessBoard();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getBoardReturnsBoard() {
        Square[][] emptyBoard = new Square[board.getBoard()[0].length][board.getBoard().length];

        for (int i = 0; i < emptyBoard.length; i++) {
            for (int j = 0; j < emptyBoard[0].length; j++) {
                emptyBoard[i][j] = new Square(i, j);
            }
        }
        Assert.assertArrayEquals(emptyBoard, board.getBoard());
    }

    @Test
    public void withinTableReturnTrueIfSquareWithinTable() {
        assertTrue(board.withinTable(3, 6));
    }

    @Test
    public void withinTableReturnTrueIfSquareOnTheEdge() {
        assertTrue(board.withinTable(0, 0));
    }

    @Test
    public void withinTableReturnFalseIfSquareNotWithinTable() {
        assertFalse(board.withinTable(-1, 5));
    }
}
