package chess.pieces;

import chess.board.ChessBoard;
import chess.board.Player;
import chess.board.Square;
import chess.board.SquareTest;
import chess.board.ChessBoardInitializer;
import chess.board.EmptyBoardInitializer;
import java.util.List;
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
public class QueenTest {

    private Queen queen;
    private static ChessBoard board;
    private static ChessBoardInitializer init;
    private List<Square> possibleMoves;

    public QueenTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        board = new ChessBoard();
        init = new EmptyBoardInitializer();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        init.initialize(board);
        queen = new Queen(board.getSquare(3, 5), Player.WHITE);
        init.putPieceOnBoard(board, queen);
        possibleMoves = queen.possibleMoves(board);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void queenCannotStayStill() {
        assertFalse(possibleMoves.contains(new Square(3, 5)));
    }

    @Test
    public void queenCanMoveHorizontallyToEveryNonBlockedSquare() {
        int[] files = new int[]{0, 1, 2, 4, 5, 6, 7};

        for (int i = 0; i < files.length; i++) {
            assertTrue(possibleMoves.contains(new Square(files[i], 5)));
        }
    }

    @Test
    public void queenCanMoveVerticallyToEveryNonBlockedSquare() {
        int[] ranks = new int[]{0, 1, 2, 3, 4, 6, 7};

        for (int i = 0; i < ranks.length; i++) {
            assertTrue(possibleMoves.contains(new Square(3, ranks[i])));
        }
    }

    @Test
    public void queenCanMoveNorthEastToEveryPossibleSquareOnBoard() {
        int[] files = new int[]{4, 5};
        int[] ranks = new int[]{6, 7};

        for (int i = 0; i < files.length; i++) {
            assertTrue(possibleMoves.contains(new Square(files[i], ranks[i])));
        }
    }

    @Test
    public void queenCanMoveNorthWestToEveryPossibleSquareOnBoard() {
        int[] files = new int[]{2, 1};
        int[] ranks = new int[]{6, 7};

        SquareTest.testMultipleSquares(files, ranks, possibleMoves);
    }

    @Test
    public void queenCanMoveSouthEastToEveryPossibleSquareOnBoard() {
        int[] files = new int[]{4, 5, 6, 7};
        int[] ranks = new int[]{4, 3, 2, 1};

        SquareTest.testMultipleSquares(files, ranks, possibleMoves);
    }

    @Test
    public void queenCanMoveSouthWestToEveryPossibleSquareOnBoard() {
        int[] files = new int[]{2, 1, 0};
        int[] ranks = new int[]{4, 3, 2};

        SquareTest.testMultipleSquares(files, ranks, possibleMoves);
    }

    @Test
    public void queenCannotOnTopOfOwnPiece() {
        Pawn pawn = new Pawn(board.getSquare(3, 1), Player.WHITE);
        init.putPieceOnBoard(board, pawn);
        possibleMoves = queen.possibleMoves(board);
        assertFalse(possibleMoves.contains(new Square(3, 1)));
    }

    @Test
    public void queenCanMoveOnTopOfEnemyPiece() {
        Pawn pawn = new Pawn(board.getSquare(3, 1), Player.BLACK);
        init.putPieceOnBoard(board, pawn);
        possibleMoves = queen.possibleMoves(board);
        assertTrue(possibleMoves.contains(new Square(3, 1)));
    }

    @Test
    public void queenCannotMovePastAPiece() {
        Pawn pawn = new Pawn(board.getSquare(3, 1), Player.WHITE);
        init.putPieceOnBoard(board, pawn);
        possibleMoves = queen.possibleMoves(board);
        assertFalse(possibleMoves.contains(new Square(3, 0)));
    }

    @Test
    public void queenCannotMovePastOpposingPiece() {
        Pawn pawn = new Pawn(board.getSquare(3, 1), Player.BLACK);
        init.putPieceOnBoard(board, pawn);
        possibleMoves = queen.possibleMoves(board);
        assertFalse(possibleMoves.contains(new Square(3, 0)));
    }
}
