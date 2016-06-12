package chess.gui;

import chess.logic.board.ChessBoard;
import chess.logic.board.Player;
import static chess.logic.board.Player.getOpponent;
import chess.logic.game.Game;
import chess.logic.pieces.Piece;
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
        int x = (e.getX()) / sideLength;
        int y = (e.getY()) / sideLength;
        Game game = board.getGame();
        ChessBoard cbl = game.getChessBoard();
        ChessBoard backUp = cbl.copy();
        Player player = game.whoseTurn();

        if (cbl.withinTable(x, y)) {
            if (board.getChosen() != null && board.getPossibilities().contains(cbl.getSquare(x, y))) {
                moveToTargetLocation(cbl, x, y, game, player, backUp);
            } else if (game.checkPlayerOwnsAPieceOnTargetSquare(game.whoseTurn(), x, y)) {
                Piece piece = cbl.getSquare(x, y).getPiece();
                board.setChosen(piece);
            }

            board.repaint();
        }
    }

    private void moveToTargetLocation(ChessBoard cbl, int x, int y, Game game, Player player, ChessBoard backUp) {
        board.getChosen().move(cbl.getSquare(x, y), cbl);
        board.setChosen(null);
        board.setPossibilities(null);
        if (game.checkIfChecked(player)) {
            game.setChessBoard(backUp);
            return;
        }
        startNextTurn(game);
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
