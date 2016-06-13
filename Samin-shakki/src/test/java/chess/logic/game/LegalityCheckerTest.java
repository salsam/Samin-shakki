package chess.logic.game;

import static chess.logic.board.ChessBoardInitializer.putPieceOnBoard;
import chess.logic.board.ChessBoard;
import chess.logic.board.Player;
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
    public void inputIsNotValidIfLocationNotOnBoard() {
        assertFalse(checker.inputIsInAllowedForm(10, 0, true));
    }

    @Test
    public void inputIsInvalidIfTargetSquareEmptyWhenNotAllowed() {
        assertFalse(checker.inputIsInAllowedForm(1, 0, false));
    }

    @Test
    public void inputIsValidIfTargetSquareEmptyWhenAllowed() {
        assertTrue(checker.inputIsInAllowedForm(1, 0, true));
    }

    @Test
    public void checkPlayerOwnsAPieceOnTargetSquareReturnsFalseIfSquareEmpty() {
        assertFalse(checker.checkPlayerOwnsAPieceOnTheTargetSquare(Player.WHITE, 3, 3));
    }

    @Test
    public void checkPlayerOwnsAPieceOnTargetSquareReturnsFalseIfTargetSquareContainsOpposingPiece() {
        putPieceOnBoard(board, new Pawn(2, 2, Player.WHITE));
        assertFalse(checker.checkPlayerOwnsAPieceOnTheTargetSquare(Player.BLACK, 2, 2));
    }

    @Test
    public void checkPlayerOwnsAPieceOnTargetSquareReturnsTrueIfTargetSquareContainsOwnPiece() {
        putPieceOnBoard(board, new Pawn(2, 2, Player.WHITE));
        assertTrue(checker.checkPlayerOwnsAPieceOnTheTargetSquare(Player.WHITE, 2, 2));
    }
}
