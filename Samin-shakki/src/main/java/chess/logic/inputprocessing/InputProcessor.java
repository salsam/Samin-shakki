package chess.logic.inputprocessing;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardCopier;
import chess.logic.board.chessboardinitializers.ChessBoardInitializer;
import static chess.logic.board.chessboardinitializers.ChessBoardInitializer.putPieceOnBoard;
import static chess.domain.board.Player.getOpponent;
import chess.domain.board.Square;
import chess.domain.GameSituation;
import chess.domain.pieces.Pawn;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Queen;
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

    public Piece getChosen() {
        return chosen;
    }

    public void setChosen(Piece chosen) {
        this.chosen = chosen;
    }

    public void setFrames(Map<String, JFrame> frames) {
        this.frames = frames;
    }

    public void setTextArea(JLabel textArea) {
        this.textArea = textArea;
    }

    public Set<Square> getPossibilities() {
        return possibilities;
    }

    public void setPossibilities(Set<Square> possibilities) {
        this.possibilities = possibilities;
    }

    /**
     * Processes input given by ChessBoardListener to do correct action in the
     * game.
     *
     * @param column column that was clicked
     * @param row row that was clicked
     * @param game game which is going on
     */
    public void processClick(int column, int row, GameSituation game) {
        if (!game.getContinues()) {
            return;
        }

        if (game.getChessBoard().withinTable(column, row)) {
            if (chosen != null && possibilities.contains(game.getChessBoard().getSquare(column, row))) {
                moveToTargetLocation(column, row, game);
            } else if (game.getChecker().checkPlayerOwnsPieceOnTargetSquare(game.whoseTurn(), column, row)) {
                setChosen(game.getChessBoard().getSquare(column, row).getPiece());
            }
            if (chosen != null) {
                possibilities = game.getChessBoard().getMovementLogic().possibleMoves(chosen, game.getChessBoard());
            }
        }
    }

    private void moveToTargetLocation(int column, int row, GameSituation game) {
        ChessBoard backUp = ChessBoardCopier.copy(game.getChessBoard());
        Square target = game.getChessBoard().getSquare(column, row);

        game.getChessBoard().getMovementLogic().move(chosen, target, game.getChessBoard());
        chosen = null;
        possibilities = null;

        if (game.checkIfChecked(game.whoseTurn())) {
            game.getChessBoard().makeFieldsEqualTo(backUp);
            return;
        }

        promote(target, game);
        startNextTurn(game);
    }

    private void promote(Square target, GameSituation game) {
        ChessBoard cbl = game.getChessBoard();
        Piece piece = target.getPiece();
        if (piece.getClass() == Pawn.class) {
            Pawn chosenPawn = (Pawn) piece;
            if (chosenPawn.opposingEnd() == target.getRow()) {
                String queensPieceCode = chosenPawn.getOwner().toString().toLowerCase().charAt(0) + "q" + chosenPawn.getPieceCode().charAt(2);
                putPieceOnBoard(cbl, new Queen(chosenPawn.getColumn(), chosenPawn.getRow(), chosenPawn.getOwner(), queensPieceCode));
                ChessBoardInitializer.removePieceFromOwner(chosenPawn, cbl);
            }
        }
    }

    private void startNextTurn(GameSituation game) {
        game.nextTurn();
        textArea.setText(game.whoseTurn() + "'s turn.");
        if (game.checkIfChecked(game.whoseTurn())) {
            textArea.setText(textArea.getText() + " Check!");
            if (game.checkMate(game.whoseTurn())) {
                textArea.setText("Checkmate! " + getOpponent(game.whoseTurn()) + " won!");
                frames.get("endingScreen").setVisible(true);
            }
        } else if (game.stalemate(game.whoseTurn())) {
            textArea.setText("Stalemate! Game ended as a draw!");
            frames.get("endingScreen").setVisible(true);
        }
    }

}
