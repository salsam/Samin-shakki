package chess.board;

import chess.pieces.Queen;
import java.util.HashSet;
import java.util.Set;
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
    private static ChessBoardInitializer init;

    public ChessBoardTest() {
        init = new StandardBoardInitializer();
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
        Square[][] emptyBoard = new Square[board.getBoard().length][board.getBoard()[0].length];

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

    @Test
    public void whitePossibleMovesReturnsAllPossibleMovesInStandardStart() {
        init.initialize(board);
        Set<Square> correct = new HashSet();

        for (int i = 2; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                correct.add(board.getSquare(j, i));
            }
        }

        for (Square possible : correct) {
            assertTrue(board.whitePossibleMoves().contains(possible));
        }
    }

    @Test
    public void whitePossibleMovesReturnsOnlyPossibleMoves() {
        init.initialize(board);
        Set<Square> wrong = new HashSet();

        for (int i = 0; i < 8; i++) {
            if (i == 2 || i == 3) {
                continue;
            }
            for (int j = 0; j < 8; j++) {
                wrong.add(board.getSquare(j, i));
            }
        }

        for (Square possible : wrong) {
            assertFalse(board.whitePossibleMoves().contains(possible));
        }
    }

    @Test
    public void blackPossibleMovesReturnsAllPossibleMovesInStandardStart() {
        init.initialize(board);
        Set<Square> correct = new HashSet();

        for (int i = 4; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                correct.add(board.getSquare(j, i));
            }
        }

        for (Square possible : correct) {
            assertTrue(board.blackPossibleMoves().contains(possible));
        }
    }

    @Test
    public void blackPossibleMovesReturnsOnlyPossibleMoves() {
        init.initialize(board);
        Set<Square> wrong = new HashSet();

        for (int i = 0; i < 8; i++) {
            if (i == 4 || i == 5) {
                continue;
            }
            for (int j = 0; j < 8; j++) {
                wrong.add(board.getSquare(j, i));
            }
        }

        for (Square possible : wrong) {
            assertFalse(board.blackPossibleMoves().contains(possible));
        }
    }

    @Test
    public void whitePossibleMovesWorksInMoreComplexSituation() {
        init.initialize(board);
        init.putPieceOnBoard(board, new Queen(board.getSquare(4, 4), Player.WHITE));
        Queen q = (Queen) board.getSquare(4, 4).getPiece();
        for (Square sq : q.possibleMoves(board)) {
            assertTrue(board.whitePossibleMoves().contains(sq));
        }
    }
}
