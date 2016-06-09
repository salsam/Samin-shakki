package chess.logic.pieces;

import chess.logic.board.ChessBoardLogic;
import chess.logic.board.Player;
import chess.logic.board.Square;
import chess.logic.board.ChessBoardInitializer;
import chess.logic.board.EmptyBoardInitializer;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sami
 */
public class PawnTest {

    private Pawn pawn;
    private ChessBoardLogic board;
    private ChessBoardInitializer init;

    public PawnTest() {
    }

    @Before
    public void setUp() {
        init = new EmptyBoardInitializer();
        board = new ChessBoardLogic();
        init.initialise(board);
        pawn = new Pawn(board.getSquare(2, 1), Player.WHITE);
        init.putPieceOnBoard(board, pawn);
    }

    @Test
    public void startingcolumnCorrect() {
        assertEquals(2, pawn.getLocation().getcolumn());
    }

    @Test
    public void startingrowCorrect() {
        assertEquals(1, pawn.getLocation().getrow());
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
