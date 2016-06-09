package chess.logic.pieces;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template column, choose Tools | Templates
 * and open the template in the editor.
 */
import chess.logic.board.ChessBoardLogic;
import chess.logic.board.Player;
import chess.logic.board.Square;
import chess.logic.board.ChessBoardInitializer;
import chess.logic.board.EmptyBoardInitializer;
import java.util.Set;
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
    private static ChessBoardLogic board;
    private static ChessBoardInitializer init;
    private Set<Square> possibleMoves;

    public RookTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        board = new ChessBoardLogic();
        init = new EmptyBoardInitializer();
    }
    
    @Before
    public void setUp() {
        init.initialise(board);
        rook = new Rook(board.getSquare(3, 5), Player.WHITE);
        init.putPieceOnBoard(board, rook);
        possibleMoves = rook.possibleMoves(board);
    }

    @Test
    public void rookCannotStayStill() {
        assertFalse(possibleMoves.contains(new Square(3, 5)));
    }

    @Test
    public void rookCanMoveHorizontallyToEveryNonBlockedSquare() {
        int[] columns = new int[]{0, 1, 2, 4, 5, 6, 7};

        for (int i = 0; i < columns.length; i++) {
            assertTrue(possibleMoves.contains(new Square(columns[i], 5)));
        }
    }

    @Test
    public void rookCanMoveVerticallyToEveryNonBlockedSquare() {
        int[] rows = new int[]{0, 1, 2, 3, 4, 6, 7};

        for (int i = 0; i < rows.length; i++) {
            assertTrue(possibleMoves.contains(new Square(3, rows[i])));
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
