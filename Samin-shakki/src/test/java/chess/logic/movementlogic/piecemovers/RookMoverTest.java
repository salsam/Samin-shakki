package chess.logic.movementlogic.piecemovers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template column, choose Tools | Templates
 * and open the template in the editor.
 */
import chess.domain.board.ChessBoard;
import chess.domain.board.Player;
import chess.domain.board.Square;
import chess.logic.chessboardinitializers.ChessBoardInitializer;
import static chess.logic.chessboardinitializers.ChessBoardInitializer.putPieceOnBoard;
import chess.logic.chessboardinitializers.EmptyBoardInitializer;
import chess.logic.movementlogic.MovementLogic;
import chess.domain.pieces.Pawn;
import chess.domain.pieces.Rook;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sami
 */
public class RookMoverTest {

    private Rook rook;
    private static ChessBoard board;
    private static ChessBoardInitializer init;
    private static RookMover rookMover;

    public RookMoverTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        board = new ChessBoard(new MovementLogic());
        init = new EmptyBoardInitializer();
        rookMover = new RookMover();
    }

    @Before
    public void setUp() {
        init.initialize(board);
        rook = new Rook(3, 5, Player.WHITE, "wr");
        putPieceOnBoard(board, rook);
    }

    @Test
    public void rookCannotStayStill() {
        assertFalse(rookMover.possibleMoves(rook, board).contains(new Square(3, 5)));
    }

    @Test
    public void rookCanMoveHorizontallyToEveryNonBlockedSquare() {
        int[] columns = new int[]{0, 1, 2, 4, 5, 6, 7};

        for (int i = 0; i < columns.length; i++) {
            assertTrue(rookMover.possibleMoves(rook, board).contains(new Square(columns[i], 5)));
        }
    }

    @Test
    public void rookCanMoveVerticallyToEveryNonBlockedSquare() {
        int[] rows = new int[]{0, 1, 2, 3, 4, 6, 7};

        for (int i = 0; i < rows.length; i++) {
            assertTrue(rookMover.possibleMoves(rook, board).contains(new Square(3, rows[i])));
        }
    }

    @Test
    public void rookCannotOnTopOfOwnPiece() {
        Pawn pawn = new Pawn(3, 1, Player.WHITE, "wp");
        putPieceOnBoard(board, pawn);
        assertFalse(rookMover.possibleMoves(rook, board).contains(new Square(3, 1)));
    }

    @Test
    public void rookCanMoveOnTopOfEnemyPiece() {
        Pawn pawn = new Pawn(3, 1, Player.BLACK, "bp");
        putPieceOnBoard(board, pawn);
        assertTrue(rookMover.possibleMoves(rook, board).contains(new Square(3, 1)));
    }

    @Test
    public void rookCannotMovePastAPiece() {
        Pawn pawn = new Pawn(3, 1, Player.WHITE, "wp");
        putPieceOnBoard(board, pawn);
        assertFalse(rookMover.possibleMoves(rook, board).contains(new Square(3, 0)));
    }

    @Test
    public void rookCannotMovePastOpposingPiece() {
        Pawn pawn = new Pawn(3, 1, Player.BLACK, "bp");
        putPieceOnBoard(board, pawn);
        assertFalse(rookMover.possibleMoves(rook, board).contains(new Square(3, 0)));
    }
}
