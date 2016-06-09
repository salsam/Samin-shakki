package chess.logic.pieces;

import chess.logic.board.ChessBoardLogic;
import java.util.Set;
import chess.logic.board.Player;
import chess.logic.board.Square;
import chess.logic.board.ChessBoardInitializer;
import chess.logic.board.EmptyBoardInitializer;
import chess.logic.board.SquareTest;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author sami
 */
public class KnightTest {

    private Knight knight;
    private ChessBoardLogic board;
    private ChessBoardInitializer init;
    private Set<Square> possibleMoves;

    public KnightTest() {
    }

    @Before
    public void setUp() {
        board = new ChessBoardLogic();
        init = new EmptyBoardInitializer();
        init.initialise(board);
        knight = new Knight(board.getSquare(4, 4), Player.WHITE);
        init.putPieceOnBoard(board, knight);
        possibleMoves = knight.possibleMoves(board);
    }

    @Test
    public void knightCannotStayStillWhenMoving() {
        assertFalse(possibleMoves.contains(new Square(4, 4)));
    }

    @Test
    public void knightCanMoveToEveryPossibleSquare() {
        int[] columns = new int[]{2, 2, 3, 3, 5, 5, 6, 6};
        int[] rows = new int[]{3, 5, 2, 6, 2, 6, 3, 5};

        SquareTest.testMultipleSquares(columns, rows, possibleMoves);
    }

    @Test
    public void knightCannotMoveOverTheEdge() {
        knight = new Knight(board.getSquare(0, 0), Player.WHITE);
        init.putPieceOnBoard(board, knight);
        assertFalse(knight.possibleMoves(board).contains(new Square(-1, -2)));
        assertFalse(knight.possibleMoves(board).contains(new Square(1, -2)));
        assertFalse(knight.possibleMoves(board).contains(new Square(-1, 2)));
    }

}
