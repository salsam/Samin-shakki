package chess.logic.board;

import chess.logic.pieces.Queen;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sami
 */
public class ChessBoardLogicTest {

    private ChessBoardLogic board;
    private static ChessBoardInitializer init;

    public ChessBoardLogicTest() {
        init = new StandardBoardInitializer();
    }

    @Before
    public void setUp() {
        board = new ChessBoardLogic();
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
        init.initialise(board);
        Set<Square> correct = new HashSet();

        for (int i = 2; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                correct.add(board.getSquare(j, i));
            }
        }

        board.updateThreatenedSquares(Player.WHITE);
        for (Square possible : correct) {
            assertTrue(board.threatenedSquares(Player.WHITE).contains(possible));
        }
    }

    @Test
    public void whitePossibleMovesReturnsOnlyPossibleMoves() {
        init.initialise(board);
        Set<Square> wrong = new HashSet();

        for (int i = 0; i < 8; i++) {
            if (i == 2 || i == 3) {
                continue;
            }
            for (int j = 0; j < 8; j++) {
                wrong.add(board.getSquare(j, i));
            }
        }

        board.updateThreatenedSquares(Player.WHITE);
        for (Square possible : wrong) {
            assertFalse(board.threatenedSquares(Player.WHITE).contains(possible));
        }
    }

    @Test
    public void blackPossibleMovesReturnsAllPossibleMovesInStandardStart() {
        init.initialise(board);
        Set<Square> correct = new HashSet();

        for (int i = 4; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                correct.add(board.getSquare(j, i));
            }
        }

        board.updateThreatenedSquares(Player.BLACK);
        for (Square possible : correct) {
            assertTrue(board.threatenedSquares(Player.BLACK).contains(possible));
        }
    }

    @Test
    public void blackPossibleMovesReturnsOnlyPossibleMoves() {
        init.initialise(board);
        Set<Square> wrong = new HashSet();

        for (int i = 0; i < 8; i++) {
            if (i == 4 || i == 5) {
                continue;
            }
            for (int j = 0; j < 8; j++) {
                wrong.add(board.getSquare(j, i));
            }
        }

        board.updateThreatenedSquares(Player.BLACK);
        for (Square possible : wrong) {
            assertFalse(board.threatenedSquares(Player.BLACK).contains(possible));
        }
    }

    @Test
    public void whitePossibleMovesWorksInMoreComplexSituation() {
        init.initialise(board);
        init.putPieceOnBoard(board, new Queen(board.getSquare(4, 4), Player.WHITE));
        Queen q = (Queen) board.getSquare(4, 4).getPiece();
        board.updateThreatenedSquares(Player.WHITE);
        for (Square sq : q.possibleMoves(board)) {
            assertTrue(board.threatenedSquares(Player.WHITE).contains(sq));
        }
    }

    @Test
    public void getKingsReturnsMapThatContainsKingLocations() {
        init.initialise(board);
        assertEquals(board.getSquare(4, 0), board.getKings().get(Player.WHITE).getLocation());
        assertEquals(board.getSquare(3, 7), board.getKings().get(Player.BLACK).getLocation());
    }

    @Test
    public void copyCreatesAnIdenticalChessBoard() {
        init.initialise(board);
        ChessBoardLogic copy = board.copy();

        assertTrue(Arrays.deepEquals(board.getBoard(), copy.getBoard()));
    }

    @Test
    public void copyCreatesANewChessBoard() {
        init.initialise(board);
        ChessBoardLogic copy = board.copy();

        Queen queen = new Queen(board.getSquare(4, 4), Player.BLACK);
        init.putPieceOnBoard(board, queen);

        assertTrue(board.getSquare(4, 4).containsAPiece());
        assertFalse(copy.getSquare(4, 4).containsAPiece());

        queen.move(board.getSquare(4, 1), board);
        assertEquals(Player.BLACK, board.getSquare(4, 1).getPiece().getOwner());
        assertEquals(Player.WHITE, copy.getSquare(4, 1).getPiece().getOwner());
    }
}
