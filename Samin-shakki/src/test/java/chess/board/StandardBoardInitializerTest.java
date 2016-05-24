package chess.board;

import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;
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
        init.initialize(board);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void pawnsOnCorrectSquares() {
        int[] files = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
        int[] ranks = new int[]{1, 6};

        testThatSquaresHavePieceOfCorrectClass(ranks, files, new Pawn(board.getSquare(0, 1), Player.WHITE));
    }

    @Test
    public void rooksOnCorrectSquares() {
        int[] files = new int[]{0, 7, 0, 7};
        int[] ranks = new int[]{0, 0, 7, 7};

        testThatSquaresHavePieceOfCorrectClass(ranks, files, new Rook(board.getSquare(0, 0), Player.WHITE));
    }

    @Test
    public void knightsOnCorrectSquares() {
        int[] files = new int[]{1, 6, 1, 6};
        int[] ranks = new int[]{0, 0, 7, 7};

        testThatSquaresHavePieceOfCorrectClass(ranks, files, new Knight(board.getSquare(1, 0), Player.WHITE));
    }

    @Test
    public void bishopsOnCorrectSquares() {
        int[] files = new int[]{2, 5, 2, 5};
        int[] ranks = new int[]{0, 0, 7, 7};

        testThatSquaresHavePieceOfCorrectClass(ranks, files, new Bishop(board.getSquare(2, 0), Player.WHITE));
    }

    @Test
    public void kingsOnCorrectSquares() {
        int[] files = new int[]{4, 3};
        int[] ranks = new int[]{0, 7};

        testThatSquaresHavePieceOfCorrectClass(ranks, files, new King(board.getSquare(4, 0), Player.WHITE));
    }

    @Test
    public void queensOnCorrectSquares() {
        int[] files = new int[]{3, 4};
        int[] ranks = new int[]{0, 7};

        testThatSquaresHavePieceOfCorrectClass(ranks, files, new Queen(board.getSquare(3, 0), Player.WHITE));
    }

    private void testThatSquaresHavePieceOfCorrectClass(int[] ranks, int[] files, Piece piece) {
        for (int i = 0; i < ranks.length; i++) {
            for (int j = 0; j < files.length; j++) {
                assertEquals(piece.getClass(), board.getSquare(files[i], ranks[i]).getPiece().getClass());
            }
        }
    }

    private void testThatSquaresHavePieceOfCorrectOwner() {
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

    private void testThatThereIsNoPiecesBetweenRanksTwoAndFive() {
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                assertFalse(board.getSquare(j, i).containsAPiece());
            }
        }
    }
}
