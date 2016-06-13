package chess.logic.guilogic;

import chess.logic.board.ChessBoard;
import chess.logic.board.ChessBoardInitializer;
import static chess.logic.board.ChessBoardInitializer.putPieceOnBoard;
import chess.logic.board.Player;
import static chess.logic.board.Player.getOpponent;
import chess.logic.board.Square;
import chess.logic.game.Game;
import chess.logic.pieces.Pawn;
import chess.logic.pieces.Piece;
import chess.logic.pieces.Queen;
import java.util.Set;
import javax.swing.JLabel;

/**
 *
 * @author sami
 */
public class GUILogic {

    private JLabel textArea;
    private Piece chosen;
    private Set<Square> possibilities;

    public GUILogic() {
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

    public void processClick(int column, int row, Game game) {

        if (game.getChessBoard().withinTable(column, row)) {
            if (chosen != null && possibilities.contains(game.getChessBoard().getSquare(column, row))) {
                moveToTargetLocation(column, row, game);
            } else if (game.getChecker().checkPlayerOwnsAPieceOnTheTargetSquare(game.whoseTurn(), row, row)) {
                chosen = game.getChessBoard().getSquare(column, row).getPiece();
            }
            if (chosen != null) {
                possibilities = game.getChessBoard().getMovementLogic().possibleMoves(chosen, game.getChessBoard());
            }
        }
    }

    private void moveToTargetLocation(int column, int row, Game game) {
        ChessBoard backUp = game.getChessBoard().copy();

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
        textArea.setText(game.whoseTurn() + "'s turn");
        if (game.checkIfChecked(game.whoseTurn())) {
            textArea.setText(textArea.getText() + ". Check!");
            if (game.checkMate(game.whoseTurn())) {
                textArea.setText("Checkmate!" + getOpponent(game.whoseTurn()) + " won!");
            }
        } else if (game.stalemate(game.whoseTurn())) {
            textArea.setText("Stalemate! Game ended as a draw!");
        }
    }

}
