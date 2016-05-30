package chess.pieces;

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
public class PawnTest {

    private Pawn pawn;
    private ChessBoard board;
    private ChessBoardInitializer init;

    public PawnTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        init = new EmptyBoardInitializer();
        board = new ChessBoard();
        init.initialize(board);
        pawn = new Pawn(board.getSquare(2, 1), Player.WHITE);
        init.putPieceOnBoard(board, pawn);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void startingFileCorrect() {
        assertEquals(2, pawn.getLocation().getFile());
    }

    @Test
    public void startingRankCorrect() {
        assertEquals(1, pawn.getLocation().getRank());
    }

    @Test
    public void pawnCannotStayStillWhenMoving() {
        assertFalse(pawn.possibleMoves(board).contains(new Square(2, 1)));
    }

    @Test
    public void pawnCannotMoveThreeStepsUp() {
        assertFalse(pawn.possibleMoves(board).contains(new Square(2, 4)));
    }

    @Test
    public void pawnCannotMoveSideways() {
        assertFalse(pawn.possibleMoves(board).contains(new Square(1, 1)));
    }

    @Test
    public void pawnCanMoveTwoStepsUpFromStartingLocation() {
        assertTrue(pawn.possibleMoves(board).contains(new Square(2, 3)));
    }

    @Test
    public void pawnCannotMoveTwoStepsUpFromOtherLocations() {
        pawn = new Pawn(board.getSquare(2, 2), Player.WHITE);
        assertFalse(pawn.possibleMoves(board).contains(new Square(2, 4)));
    }

    @Test
    public void pawnCanTakeAPieceDiagonallyForwardToRight() {
        Pawn enemyPawn = new Pawn(board.getSquare(3, 2), Player.BLACK);
        init.putPieceOnBoard(board, enemyPawn);
        assertTrue(pawn.possibleMoves(board).contains(new Square(3, 2)));
    }

    @Test
    public void pawnCanTakeAPieceDiagonallyForwardToLeft() {
        Pawn enemyPawn = new Pawn(board.getSquare(1, 2), Player.BLACK);
        init.putPieceOnBoard(board, enemyPawn);
        assertTrue(pawn.possibleMoves(board).contains(new Square(1, 2)));
    }

    @Test
    public void pawnCannotTakeOwnPieceDiagonallyForward() {
        Pawn enemyPawn = new Pawn(board.getSquare(3, 2), Player.WHITE);
        init.putPieceOnBoard(board, enemyPawn);
        assertFalse(pawn.possibleMoves(board).contains(new Square(3, 2)));
    }

    @Test
    public void pawnCannotMoveOverTheEdge() {
        pawn = new Pawn(board.getSquare(0, 7), Player.WHITE);
        init.putPieceOnBoard(board, pawn);
        assertFalse(pawn.possibleMoves(board).contains(new Square(0, 8)));
    }
}
