package chess.logic.pieces;

import chess.logic.pieces.Pawn;
import chess.logic.pieces.Bishop;
import chess.logic.board.ChessBoard;
import chess.logic.board.Player;
import chess.logic.board.Square;
import chess.logic.board.SquareTest;
import chess.logic.board.ChessBoardInitializer;
import chess.logic.board.EmptyBoardInitializer;
import java.util.List;
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
public class BishopTest {

    private Bishop bishop;
    private static ChessBoard board;
    private static ChessBoardInitializer init;
    private List<Square> possibleMoves;

    public BishopTest() {
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
        init.initialise(board);
        bishop = new Bishop(board.getSquare(3, 4), Player.WHITE);
        init.putPieceOnBoard(board, bishop);
        possibleMoves = bishop.possibleMoves(board);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void bishopCannotStayStillWhenMoving() {
        assertFalse(possibleMoves.contains(new Square(3, 4)));
    }

    @Test
    public void bishopCanMoveNorthEastToEveryPossibleSquareOnBoard() {
        int[] files = new int[]{4, 5, 6};
        int[] ranks = new int[]{5, 6, 7};

        SquareTest.testMultipleSquares(files, ranks, possibleMoves);
    }

    @Test
    public void bishopCanMoveNorthWestToEveryPossibleSquareOnBoard() {
        int[] files = new int[]{2, 1, 0};
        int[] ranks = new int[]{5, 6, 7};

        SquareTest.testMultipleSquares(files, ranks, possibleMoves);
    }

    @Test
    public void bishopCanMoveSouthEastToEveryPossibleSquareOnBoard() {
        int[] files = new int[]{4, 5, 6, 7};
        int[] ranks = new int[]{3, 2, 1, 0};

        SquareTest.testMultipleSquares(files, ranks, possibleMoves);
    }

    @Test
    public void bishopCanMoveSouthWestToEveryPossibleSquareOnBoard() {
        int[] files = new int[]{2, 1, 0};
        int[] ranks = new int[]{3, 2, 1};

        SquareTest.testMultipleSquares(files, ranks, possibleMoves);
    }

    @Test
    public void bishopCannotMoveOnTopOfOwnPiece() {
        Pawn pawn = new Pawn(board.getSquare(4, 5), Player.WHITE);
        init.putPieceOnBoard(board, pawn);
        possibleMoves = bishop.possibleMoves(board);
        assertFalse(possibleMoves.contains(new Square(4, 5)));
    }

    @Test
    public void bishopCanMoveOnTopOfEnemyPiece() {
        Pawn pawn = new Pawn(board.getSquare(4, 5), Player.BLACK);
        init.putPieceOnBoard(board, pawn);
        possibleMoves = bishop.possibleMoves(board);
        assertTrue(possibleMoves.contains(new Square(4, 5)));
    }

    @Test
    public void bishopCannotMovePastAPiece() {
        Pawn pawn = new Pawn(board.getSquare(4, 5), Player.WHITE);
        init.putPieceOnBoard(board, pawn);
        possibleMoves = bishop.possibleMoves(board);
        assertFalse(possibleMoves.contains(new Square(5, 6)));
    }

    @Test
    public void bishopCannotMovePastOpposingPiece() {
        Pawn pawn = new Pawn(board.getSquare(4, 5), Player.BLACK);
        init.putPieceOnBoard(board, pawn);
        possibleMoves = bishop.possibleMoves(board);
        assertFalse(possibleMoves.contains(new Square(5, 6)));
    }
}
