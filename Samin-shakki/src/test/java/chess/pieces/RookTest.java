package chess.pieces;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import chess.board.ChessBoard;
import chess.board.Player;
import chess.board.Square;
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
public class RookTest {

    private Rook rook;
    private static ChessBoard board;
    private static ChessBoardInitializer init;
    private List<Square> possibleMoves;

    public RookTest() {
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
        rook = new Rook(board.getSquare(3, 5), Player.WHITE);
        init.putPieceOnBoard(board, rook);
        possibleMoves = rook.possibleMoves(board);
    }

    public void tearDown() {
    }

    @Test
    public void rookCannotStayStill() {
        assertFalse(possibleMoves.contains(new Square(3, 5)));
    }

    @Test
    public void rookCanMoveHorizontallyToEveryNonBlockedSquare() {
        int[] files = new int[]{0, 1, 2, 4, 5, 6, 7};

        for (int i = 0; i < files.length; i++) {
            assertTrue(possibleMoves.contains(new Square(files[i], 5)));
        }
    }

    @Test
    public void rookCanMoveVerticallyToEveryNonBlockedSquare() {
        int[] ranks = new int[]{0, 1, 2, 3, 4, 6, 7};

        for (int i = 0; i < ranks.length; i++) {
            assertTrue(possibleMoves.contains(new Square(3, ranks[i])));
        }
    }

    @Test
    public void rookCannotOnTopOfOwnPiece() {
        Pawn pawn = new Pawn(board.getSquare(3, 1), Player.WHITE);
        init.putPieceOnBoard(board, pawn);
        possibleMoves = rook.possibleMoves(board);
        assertFalse(possibleMoves.contains(new Square(3, 1)));
    }

    @Test
    public void rookCanMoveOnTopOfEnemyPiece() {
        Pawn pawn = new Pawn(board.getSquare(3, 1), Player.BLACK);
        init.putPieceOnBoard(board, pawn);
        possibleMoves = rook.possibleMoves(board);
        assertTrue(possibleMoves.contains(new Square(3, 1)));
    }

    @Test
    public void rookCannotMovePastAPiece() {
        Pawn pawn = new Pawn(board.getSquare(3, 1), Player.WHITE);
        init.putPieceOnBoard(board, pawn);
        possibleMoves = rook.possibleMoves(board);
        assertFalse(possibleMoves.contains(new Square(3, 0)));
    }

    @Test
    public void rookCannotMovePastOpposingPiece() {
        Pawn pawn = new Pawn(board.getSquare(3, 1), Player.BLACK);
        init.putPieceOnBoard(board, pawn);
        possibleMoves = rook.possibleMoves(board);
        assertFalse(possibleMoves.contains(new Square(3, 0)));
    }
}
