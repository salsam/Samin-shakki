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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author sami
 */
public class KnightTest {

    private Knight knight;
    private ChessBoard board;
    private ChessBoardInitializer init;
    private List<Square> possibleMoves;

    public KnightTest() {
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
        knight = new Knight(board.getSquare(4, 4), Player.WHITE);
        init.putPieceOnBoard(board, knight);
        possibleMoves = knight.possibleMoves(board);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void knightCannotStayStillWhenMoving() {
        assertFalse(possibleMoves.contains(new Square(4, 4)));
    }

    @Test
    public void knightCanMoveToEveryPossibleSquare() {
        int[] files = new int[]{2, 2, 3, 3, 5, 5, 6, 6};
        int[] ranks = new int[]{3, 5, 2, 6, 2, 6, 3, 5};

        SquareTest.testMultipleSquares(files, ranks, possibleMoves);
    }

    @Test
    public void knightCannotMoveOverTheEdge() {
        knight = new Knight(board.getSquare(0, 0), Player.WHITE);
        init.putPieceOnBoard(board, knight);
        assertFalse(knight.possibleMoves(board).contains(new Square(-1, -2)));
        assertFalse(knight.possibleMoves(board).contains(new Square(1, -2)));
        assertFalse(knight.possibleMoves(board).contains(new Square(-1, 2)));
    }

}
