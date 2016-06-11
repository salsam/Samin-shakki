package chess.logic.pieces;

import chess.logic.board.ChessBoard;
import chess.logic.board.Player;
import chess.logic.board.Square;
import chess.logic.board.SquareTest;
import chess.logic.board.ChessBoardInitializer;
import static chess.logic.board.ChessBoardInitializer.putPieceOnBoard;
import chess.logic.board.EmptyBoardInitializer;
import java.util.Set;
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
    private Set<Square> possibleMoves;

    public BishopTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        board = new ChessBoard();
        init = new EmptyBoardInitializer();
    }

    @Before
    public void setUp() {
        init.initialise(board);
        bishop = new Bishop(board.getSquare(3, 4), Player.WHITE);
        putPieceOnBoard(board, bishop);
        possibleMoves = bishop.possibleMoves(board);
    }

    @Test
    public void bishopCannotStayStillWhenMoving() {
        assertFalse(possibleMoves.contains(new Square(3, 4)));
    }

    @Test
    public void bishopCanMoveNorthEastToEveryPossibleSquareOnBoard() {
        int[] columns = new int[]{4, 5, 6};
        int[] rows = new int[]{5, 6, 7};

        SquareTest.testMultipleSquares(columns, rows, possibleMoves);
    }

    @Test
    public void bishopCanMoveNorthWestToEveryPossibleSquareOnBoard() {
        int[] columns = new int[]{2, 1, 0};
        int[] rows = new int[]{5, 6, 7};

        SquareTest.testMultipleSquares(columns, rows, possibleMoves);
    }

    @Test
    public void bishopCanMoveSouthEastToEveryPossibleSquareOnBoard() {
        int[] columns = new int[]{4, 5, 6, 7};
        int[] rows = new int[]{3, 2, 1, 0};

        SquareTest.testMultipleSquares(columns, rows, possibleMoves);
    }

    @Test
    public void bishopCanMoveSouthWestToEveryPossibleSquareOnBoard() {
        int[] columns = new int[]{2, 1, 0};
        int[] rows = new int[]{3, 2, 1};

        SquareTest.testMultipleSquares(columns, rows, possibleMoves);
    }

    @Test
    public void bishopCannotMoveOnTopOfOwnPiece() {
        Pawn pawn = new Pawn(board.getSquare(4, 5), Player.WHITE);
        putPieceOnBoard(board, pawn);
        possibleMoves = bishop.possibleMoves(board);
        assertFalse(possibleMoves.contains(new Square(4, 5)));
    }

    @Test
    public void bishopCanMoveOnTopOfEnemyPiece() {
        Pawn pawn = new Pawn(board.getSquare(4, 5), Player.BLACK);
        putPieceOnBoard(board, pawn);
        possibleMoves = bishop.possibleMoves(board);
        assertTrue(possibleMoves.contains(new Square(4, 5)));
    }

    @Test
    public void bishopCannotMovePastAPiece() {
        Pawn pawn = new Pawn(board.getSquare(4, 5), Player.WHITE);
        putPieceOnBoard(board, pawn);
        possibleMoves = bishop.possibleMoves(board);
        assertFalse(possibleMoves.contains(new Square(5, 6)));
    }

    @Test
    public void bishopCannotMovePastOpposingPiece() {
        Pawn pawn = new Pawn(board.getSquare(4, 5), Player.BLACK);
        putPieceOnBoard(board, pawn);
        possibleMoves = bishop.possibleMoves(board);
        assertFalse(possibleMoves.contains(new Square(5, 6)));
    }
}
