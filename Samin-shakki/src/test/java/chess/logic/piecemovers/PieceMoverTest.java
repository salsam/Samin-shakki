package chess.logic.piecemovers;

import chess.logic.board.ChessBoard;
import chess.logic.board.ChessBoardInitializer;
import static chess.logic.board.ChessBoardInitializer.putPieceOnBoard;
import chess.logic.board.Player;
import chess.logic.board.StandardBoardInitializer;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Queen;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author sami
 */
public class PieceMoverTest {

    private Piece piece;
    private Piece pawn;
    private static ChessBoard board;
    private static ChessBoardInitializer init;

    public PieceMoverTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        board = new ChessBoard();
        init = new StandardBoardInitializer();
    }

    @Before
    public void setUp() {
        init.initialise(board);
        piece = new Queen(3, 4, Player.WHITE);
        pawn = new Pawn(3, 6, Player.BLACK);
        putPieceOnBoard(board, pawn);
        putPieceOnBoard(board, piece);
    }

    @Test
    public void getOwnerReturnCorrectPlayer() {
        assertEquals(Player.WHITE, piece.getOwner());
        assertEquals(Player.BLACK, pawn.getOwner());
    }

    @Test
    public void locationCorrectAfterCreation() {
        assertEquals(board.getSquare(3, 4), board.getSquare(piece.getColumn(), piece.getRow()));
    }

    @Test
    public void movingChangesLocationCorrectly() {
        board.getMovementLogic().move(piece, board.getSquare(3, 5), board);
        assertEquals(board.getSquare(3, 5), board.getSquare(piece.getColumn(), piece.getRow()));
    }

    @Test
    public void movingRemovesPieceFromPreviousSquare() {
        board.getMovementLogic().move(piece, board.getSquare(3, 5), board);
        assertEquals(null, board.getSquare(3, 4).getPiece());
    }

    @Test
    public void movingAddsPieceToTargetSquare() {
        board.getMovementLogic().move(piece, board.getSquare(3, 5), board);
        assertEquals(piece, board.getSquare(3, 5).getPiece());
    }

}
