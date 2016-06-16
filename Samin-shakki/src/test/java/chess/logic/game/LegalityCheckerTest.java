package chess.logic.game;

import chess.logic.movementlogic.MovementLogic;
import chess.logic.board.ChessBoard;
import chess.logic.board.Player;
import static chess.logic.board.chessboardinitializers.ChessBoardInitializer.putPieceOnBoard;
import chess.logic.pieces.Pawn;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sami
 */
public class LegalityCheckerTest {

    ChessBoard board;
    LegalityChecker checker;

    public LegalityCheckerTest() {
    }

    @Before
    public void setUp() {
        board = new ChessBoard(new MovementLogic());
        checker = new LegalityChecker(board);
    }

    @Test
    public void checkPlayerOwnsAPieceOnTargetSquareReturnsFalseIfSquareEmpty() {
        assertFalse(checker.checkPlayerOwnsPieceOnTargetSquare(Player.WHITE, 3, 3));
    }

    @Test
    public void checkPlayerOwnsAPieceOnTargetSquareReturnsFalseIfTargetSquareContainsOpposingPiece() {
        putPieceOnBoard(board, new Pawn(2, 2, Player.WHITE));
        assertFalse(checker.checkPlayerOwnsPieceOnTargetSquare(Player.BLACK, 2, 2));
    }

    @Test
    public void checkPlayerOwnsAPieceOnTargetSquareReturnsTrueIfTargetSquareContainsOwnPiece() {
        putPieceOnBoard(board, new Pawn(2, 2, Player.WHITE));
        assertTrue(checker.checkPlayerOwnsPieceOnTargetSquare(Player.WHITE, 2, 2));
    }
}
