package chess.logic.inputprocessing;

import chess.logic.board.Square;
import chess.logic.board.chessboardinitializers.ChessBoardInitializer;
import chess.logic.board.chessboardinitializers.StandardBoardInitializer;
import chess.logic.game.Game;
import chess.logic.movementlogic.MovementLogic;
import chess.logic.pieces.Piece;
import javax.swing.JLabel;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author sami
 */
public class InputProcessorTest {

    private InputProcessor inputProcessor;
    private static JLabel output;
    private static Game game;
    private static ChessBoardInitializer init;

    public InputProcessorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        init = new StandardBoardInitializer();
        game = new Game(init, new MovementLogic());
        output = new JLabel("");
    }

    @Before
    public void setUp() {
        game.reset();
        inputProcessor = new InputProcessor();
        inputProcessor.setTextArea(output);
    }

    @Test
    public void ifNoPieceIsSelectedChosenAndPossibilitiesAreNull() {
        assertEquals(null, inputProcessor.getPossibilities());
        assertEquals(null, inputProcessor.getChosen());
    }

    @Test
    public void ifNoPieceIsChosenSelectPieceOnTargetSquare() {
        inputProcessor.processClick(1, 1, game);
        System.out.println(game.whoseTurn());
        assertEquals(game.getChessBoard().getSquare(1, 1).getPiece(), inputProcessor.getChosen());
    }

    @Test
    public void ifNoPieceIsSelectedAndTargetSquareContainsNoPieceIrOpposingPieceNoPieceIsSelected() {
        inputProcessor.processClick(4, 4, game);
        assertEquals(null, inputProcessor.getChosen());
        inputProcessor.processClick(6, 6, game);
        assertEquals(null, inputProcessor.getChosen());
    }

    @Test
    public void whenPieceIsSelectedItsPossibleMovesAreSavedInFieldPossibilities() {
        inputProcessor.processClick(1, 1, game);
        assertTrue(inputProcessor.getPossibilities().contains(new Square(1, 2)));
        assertTrue(inputProcessor.getPossibilities().contains(new Square(1, 3)));
    }

    @Test
    public void ifAnotherPieceOwnedPieceIsClickedItIsSetAsChosen() {
        inputProcessor.processClick(1, 1, game);
        inputProcessor.processClick(1, 0, game);

        assertTrue(inputProcessor.getPossibilities().contains(new Square(0, 2)));
        assertTrue(inputProcessor.getPossibilities().contains(new Square(2, 2)));
        assertFalse(inputProcessor.getPossibilities().contains(new Square(1, 2)));
        assertFalse(inputProcessor.getPossibilities().contains(new Square(1, 3)));
    }

    @Test
    public void ifPieceIsChosenAndThenAPossibilityIsClickedMoveToThatSquare() {
        inputProcessor.processClick(1, 1, game);
        Piece piece = game.getChessBoard().getSquare(1, 1).getPiece();
        inputProcessor.processClick(1, 2, game);
        assertFalse(game.getChessBoard().getSquare(1, 1).containsAPiece());
        assertTrue(game.getChessBoard().getSquare(1, 2).containsAPiece());
        assertEquals(piece, game.getChessBoard().getSquare(1, 2).getPiece());
    }
}
