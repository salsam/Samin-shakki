package chess.logic.guilogic;

import chess.gui.EndingScreen;
import chess.gui.GameWindow;
import chess.logic.board.ChessBoard;
import chess.logic.board.ChessBoardCopier;
import chess.logic.board.chessboardinitializers.ChessBoardInitializer;
import static chess.logic.board.chessboardinitializers.ChessBoardInitializer.putPieceOnBoard;
import static chess.logic.board.Player.getOpponent;
import chess.logic.board.Square;
import chess.logic.game.Game;
import chess.logic.pieces.Pawn;
import chess.logic.pieces.Piece;
import chess.logic.pieces.Queen;
import java.util.Map;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * This is responsible for connecting graphical user interface to other logic
 * classes. Class offers methods update text given to players and process
 * information given by graphical user interface to move pieces accordingly in
 * game.
 *
 * @author sami
 */
public class InputProcessor {

    private JLabel textArea;
    private Map<String, JFrame> frames;
    private Piece chosen;
    private Set<Square> possibilities;

    /**
     * Creates a new GUILogic-object.
     */
    public InputProcessor() {
    }

    public void setFrames(Map<String, JFrame> frames) {
        this.frames = frames;
    }

    public JLabel getTextArea() {
        return textArea;
    }

    public void setTextArea(JLabel textArea) {
        this.textArea = textArea;
    }

    public Set<Square> getPossibilities() {
        return possibilities;
    }

    /**
     * Processes input given by ChessBoardListener to do correct action in the
     * game.
     *
     * @param column column that was clicked
     * @param row row that was clicked
     * @param game game which is going on
     */
    public void processClick(int column, int row, Game game) {
        if (!game.getContinues()) {
            return;
        }

        if (game.getChessBoard().withinTable(column, row)) {
            if (chosen != null && possibilities.contains(game.getChessBoard().getSquare(column, row))) {
                moveToTargetLocation(column, row, game);
            } else if (game.getChecker().checkPlayerOwnsAPieceOnTheTargetSquare(game.whoseTurn(), column, row)) {
                chosen = game.getChessBoard().getSquare(column, row).getPiece();
            }
            if (chosen != null) {
                possibilities = game.getChessBoard().getMovementLogic().possibleMoves(chosen, game.getChessBoard());
            }
        }
    }

    private void moveToTargetLocation(int column, int row, Game game) {
        ChessBoard backUp = ChessBoardCopier.copy(game.getChessBoard());

        game.getChessBoard().getMovementLogic().move(chosen, game.getChessBoard().getSquare(column, row), game.getChessBoard());
        chosen = null;
        possibilities = null;

        if (game.checkIfChecked(game.whoseTurn())) {
            game.setChessBoard(backUp);
            return;
        }

        promote(column, row, game);
        startNextTurn(game);
    }

    private void promote(int column, int row, Game game) {
        ChessBoard cbl = game.getChessBoard();
        Piece piece = cbl.getSquare(column, row).getPiece();
        if (piece.getClass() == Pawn.class) {
            Pawn chosenPawn = (Pawn) piece;
            if (chosenPawn.opposingEnd() == row) {
                putPieceOnBoard(cbl, new Queen(chosenPawn.getColumn(), chosenPawn.getRow(), chosenPawn.getOwner()));
                ChessBoardInitializer.removePieceFromOwner(chosenPawn, cbl);
            }
        }
    }

    private void startNextTurn(Game game) {
        game.nextTurn();
        textArea.setText(game.whoseTurn() + "'s turn.");
        if (game.checkIfChecked(game.whoseTurn())) {
            textArea.setText(textArea.getText() + " Check!");
            if (game.checkMate(game.whoseTurn())) {
                textArea.setText("Checkmate!" + getOpponent(game.whoseTurn()) + " won!");
                frames.get("endingScreen").setVisible(true);
            }
        } else if (game.stalemate(game.whoseTurn())) {
            textArea.setText("Stalemate! Game ended as a draw!");
            frames.get("endingScreen").setVisible(true);
        }
    }

}
