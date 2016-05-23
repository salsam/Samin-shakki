package chess.pieces;

import chess.pieces.Pawn;
import chess.board.Player;
import chess.board.Square;
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
    private Square[][] board;

    public PawnTest() {
    }

    public static Square[][] emptyBoard() {
        Square[][] emptyBoard = new Square[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                emptyBoard[i][j] = new Square(i, j);
            }
        }

        return emptyBoard;
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        pawn = new Pawn(2, 1, Player.WHITE);
        board = emptyBoard();
        board[2][1].setPiece(pawn);
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
        pawn = new Pawn(2, 2, Player.WHITE);
        assertFalse(pawn.possibleMoves(board).contains(new Square(2, 4)));
    }

    @Test
    public void pawnCannotTakeNonExistingPieces() {
        assertFalse(pawn.canTakeAnOpposingPiece(1, board));
    }

    @Test
    public void pawnCanTakeAPieceDiagonallyForward() {
        Pawn enemyPawn = new Pawn(3, 2, Player.BLACK);
        board[3][2].setPiece(enemyPawn);
        assertTrue(pawn.canTakeAnOpposingPiece(1, board));
    }

    @Test
    public void pawnCannotTakeOwnPieceDiagonallyForward() {
        Pawn enemyPawn = new Pawn(3, 2, Player.WHITE);
        board[3][2].setPiece(enemyPawn);
        assertFalse(pawn.canTakeAnOpposingPiece(1, board));
    }
}
