package chess.pieces;

import chess.board.ChessBoard;
import java.util.List;
import chess.board.Player;
import chess.board.Square;
import chess.board.ChessBoardInitializer;
import chess.board.EmptyBoardInitializer;
import chess.board.SquareTest;
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
        init.initialize(board);
        king = new King(board.getSquare(2, 3), Player.WHITE);
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
    public void kingCanMoveToEveryNeighboringSquare() {
        int[] files = new int[]{3, 2, 1, 3, 1, 3, 2, 1};
        int[] ranks = new int[]{4, 4, 4, 3, 3, 2, 2, 2};

        SquareTest.testMultipleSquares(files, ranks, possibleMoves);
    }

    @Test
    public void kingCannotMoveOutOfBoard() {
        init.initialize(board);
        king = new King(board.getSquare(0, 0), Player.WHITE);
        init.putPieceOnBoard(board, king);
        possibleMoves = king.possibleMoves(board);

        assertFalse(possibleMoves.contains(new Square(-1, 0)));
        assertFalse(possibleMoves.contains(new Square(0, -1)));
    }
}
