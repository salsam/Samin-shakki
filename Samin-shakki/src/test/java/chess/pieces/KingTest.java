package chess.pieces;

import chess.board.ChessBoard;
import java.util.List;
import chess.board.Player;
import chess.board.Square;
import chess.board.ChessBoardInitializer;
import chess.board.EmptyBoardInitializer;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author sami
 */
public class KingTest {
    
    private King king;
    private ChessBoard board;
    private ChessBoardInitializer init;
    private List<Square> possibleMoves;
    
    public KingTest() {
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
        init = new EmptyBoardInitializer();
        king = new King(2, 3, Player.WHITE);
        init.initialize(board);
        init.putPieceOnBoard(board, king);
        possibleMoves = king.possibleMoves(board);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void startingFileCorrect() {
        assertEquals(2, king.location.getFile());
    }
    
    @Test
    public void startingRankCorrect() {
        assertEquals(3, king.location.getRank());
    }
    
    @Test
    public void kingCannotStayStillWhenMoving() {
        assertFalse(possibleMoves.contains(new Square(2, 3)));
    }
    
    @Test
    public void kingCanMoveNorthEast() {
        assertTrue(possibleMoves.contains(new Square(3, 4)));
    }
    
    @Test
    public void kingCanMoveStraightUp() {
        assertTrue(possibleMoves.contains(new Square(3, 3)));
    }
    
    @Test
    public void kingCanMoveNorthWest() {
        assertTrue(possibleMoves.contains(new Square(3, 2)));
    }
    
    @Test
    public void kingCanMoveEast() {
        assertTrue(possibleMoves.contains(new Square(2, 4)));
    }
    
    @Test
    public void kingCanMoveWest() {
        assertTrue(possibleMoves.contains(new Square(2, 2)));
    }
    
    @Test
    public void kingCanMoveSouthEast() {
        assertTrue(possibleMoves.contains(new Square(1, 4)));
    }
    
    @Test
    public void kingCanMoveSouth() {
        assertTrue(possibleMoves.contains(new Square(1, 3)));
    }
    
    @Test
    public void kingCanMoveSouthWest() {
        assertTrue(possibleMoves.contains(new Square(1, 2)));
    }
}
