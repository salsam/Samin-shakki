package chess.logic.pieces;

import chess.logic.pieces.Pawn;
import chess.logic.pieces.Queen;
import chess.logic.pieces.Piece;
import chess.logic.board.ChessBoardLogic;
import chess.logic.board.ChessBoardInitializer;
import chess.logic.board.Player;
import chess.logic.board.StandardBoardInitializer;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author sami
 */
public class PieceTest {

    private Piece piece;
    private Piece pawn;
    private static ChessBoardLogic board;
    private static ChessBoardInitializer init;

    public PieceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        board = new ChessBoardLogic();
        init = new StandardBoardInitializer();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        init.initialise(board);
        piece = new Queen(board.getSquare(3, 4), Player.WHITE);
        pawn = new Pawn(board.getSquare(3, 6), Player.BLACK);
        init.putPieceOnBoard(board, pawn);
        init.putPieceOnBoard(board, piece);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getOwnerReturnCorrectPlayer() {
        assertEquals(Player.WHITE, piece.getOwner());
        assertEquals(Player.BLACK, pawn.getOwner());
    }

    @Test
    public void locationCorrectAfterCreation() {
        assertEquals(board.getSquare(3, 4), piece.getLocation());
    }

    @Test
    public void movingChangesLocationCorrectly() {
        piece.move(board.getSquare(3, 5), board);
        assertEquals(board.getSquare(3, 5), piece.getLocation());
    }

    @Test
    public void movingRemovesPieceFromPreviousSquare() {
        piece.move(board.getSquare(3, 5), board);
        assertEquals(null, board.getSquare(3, 4).getPiece());
    }

    @Test
    public void movingAddsPieceToTargetSquare() {
        piece.move(board.getSquare(3, 5), board);
        assertEquals(piece, board.getSquare(3, 5).getPiece());
    }

}
