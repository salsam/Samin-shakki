package chess.gui;

import chess.logic.board.ChessBoard;
import chess.logic.board.ChessBoardInitializer;
import static chess.logic.board.ChessBoardInitializer.putPieceOnBoard;
import chess.logic.board.Player;
import static chess.logic.board.Player.getOpponent;
import chess.logic.game.Game;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Queen;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

/**
 *
 * @author sami
 */
public class ChessBoardListener implements MouseListener {

    private ChessBoardDrawer board;
    private JLabel textArea;
    private int sideLength;

    public ChessBoardListener(ChessBoardDrawer board, JLabel textArea, int sideLength) {
        this.board = board;
        this.textArea = textArea;
        this.sideLength = sideLength;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int column = (e.getX()) / sideLength;
        int row = (e.getY()) / sideLength;

        if (board.getGame().getChessBoard().withinTable(column, row)) {
            if (board.getChosen() != null && board.getPossibilities().contains(board.getGame().getChessBoard().getSquare(column, row))) {
                moveToTargetLocation(column, row, board.getGame(), board.getGame().whoseTurn());
            } else if (board.getGame().checkPlayerOwnsAPieceOnTargetSquare(board.getGame().whoseTurn(), column, row)) {
                Piece piece = board.getGame().getChessBoard().getSquare(column, row).getPiece();
                board.setChosen(piece);
            }

            board.repaint();
        }
    }

    private void moveToTargetLocation(int column, int row, Game game, Player player) {
        ChessBoard backUp = game.getChessBoard().copy();

        game.getChessBoard().getMovementLogic().move(board.getChosen(), game.getChessBoard().getSquare(column, row), game.getChessBoard());
        board.setChosen(null);
        board.setPossibilities(null);
        if (game.checkIfChecked(player)) {
            game.setChessBoard(backUp);
            return;
        }
        promote(column, row);
        startNextTurn(game);
    }

    private void promote(int column, int row) {
        ChessBoard cbl = board.getGame().getChessBoard();
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
        Player player = game.whoseTurn();
        textArea.setText(player + "'s turn");
        if (game.checkIfChecked(player)) {
            textArea.setText(textArea.getText() + ". Check!");
            if (game.checkMate(player)) {
                textArea.setText("Checkmate!" + getOpponent(player) + " won!");
            }
        } else if (game.stalemate(player)) {
            textArea.setText("Stalemate! Game ended as a draw!");
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

}
