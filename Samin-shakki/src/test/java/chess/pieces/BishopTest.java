package chess.pieces;

import chess.board.ChessBoard;
import chess.board.Player;
import chess.board.chessBoardInitializer;
import chess.board.emptyBoardInitializer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author sami
 */
public class BishopTest {
    Bishop bishop;
    ChessBoard board;
    chessBoardInitializer init;

    public BishopTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        board=new ChessBoard();
        init= new emptyBoardInitializer();
        bishop = new Bishop(3,4, Player.WHITE);
        
    }

    @After
    public void tearDown() {
    }
}
