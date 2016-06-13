package chess.logic.board;

import static chess.logic.board.ChessBoardInitializer.putPieceOnBoard;
import chess.logic.piecemovers.BishopMover;
import chess.logic.piecemovers.KingMover;
import chess.logic.piecemovers.KnightMover;
import chess.logic.piecemovers.PawnMover;
import chess.logic.piecemovers.PieceMover;
import chess.logic.piecemovers.QueenMover;
import chess.logic.piecemovers.RookMover;
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
public class StandardBoardInitializerTest {

    private static ChessBoard board;
    private static ChessBoardInitializer init;

    public StandardBoardInitializerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        board = new ChessBoard();
        init = new StandardBoardInitializer();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        init.initialise(board);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void pawnsOnCorrectSquares() {
        int[] columns = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
        int[] rows = new int[]{1, 6};

        testThatSquaresHavePieceOfCorrectClass(rows, columns, new PawnMover(board.getSquare(0, 1), Player.WHITE));
    }

    @Test
    public void rooksOnCorrectSquares() {
        int[] columns = new int[]{0, 7, 0, 7};
        int[] rows = new int[]{0, 0, 7, 7};

        testThatSquaresHavePieceOfCorrectClass(rows, columns, new RookMover(board.getSquare(0, 0), Player.WHITE));
    }

    @Test
    public void knightsOnCorrectSquares() {
        int[] columns = new int[]{1, 6, 1, 6};
        int[] rows = new int[]{0, 0, 7, 7};

        testThatSquaresHavePieceOfCorrectClass(rows, columns, new KnightMover(board.getSquare(1, 0), Player.WHITE));
    }

    @Test
    public void bishopsOnCorrectSquares() {
        int[] columns = new int[]{2, 5, 2, 5};
        int[] rows = new int[]{0, 0, 7, 7};

        testThatSquaresHavePieceOfCorrectClass(rows, columns, new BishopMover(board.getSquare(2, 0), Player.WHITE));
    }

    @Test
    public void kingsOnCorrectSquares() {
        int[] columns = new int[]{4, 3};
        int[] rows = new int[]{0, 7};

        testThatSquaresHavePieceOfCorrectClass(rows, columns, new KingMover(board.getSquare(4, 0), Player.WHITE));
    }

    @Test
    public void queensOnCorrectSquares() {
        int[] columns = new int[]{3, 4};
        int[] rows = new int[]{0, 7};

        testThatSquaresHavePieceOfCorrectClass(rows, columns, new QueenMover(board.getSquare(3, 0), Player.WHITE));
    }

    private void testThatSquaresHavePieceOfCorrectClass(int[] rows, int[] columns, PieceMover piece) {
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < columns.length; j++) {
                assertEquals(piece.getClass(), board.getSquare(columns[i], rows[i]).getPiece().getClass());
            }
        }
    }

    public void testThatSquaresHavePieceOfCorrectOwner() {
        Player owner = Player.WHITE;
        for (int i = 0; i < board.getBoard().length; i++) {
            if (i == 2) {
                i = 5;
                owner = Player.BLACK;
                continue;
            }

            for (int j = 0; j < board.getBoard()[0].length; j++) {
                assertEquals(owner, board.getSquare(j, i).getPiece().getOwner());
            }
        }
    }

    public void testThatThereIsNoPiecesBetweenrowsTwoAndFive() {
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                assertFalse(board.getSquare(j, i).containsAPiece());
            }
        }
    }

    public void putPieceOnBoardPutsCorrectPieceInCorrectSpot() {
        PawnMover pawn = new PawnMover(board.getSquare(5, 4), Player.WHITE);
        putPieceOnBoard(board, pawn);
        assertTrue(board.getSquare(5, 4).containsAPiece());
        assertEquals(PawnMover.class, board.getSquare(5, 4).getPiece().getClass());
        assertEquals(Player.WHITE, board.getSquare(5, 4).getPiece().getOwner());
    }
}
