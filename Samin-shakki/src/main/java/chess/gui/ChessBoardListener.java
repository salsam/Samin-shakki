package chess.gui;

import chess.logic.board.ChessBoardLogic;
import chess.logic.board.Player;
import chess.logic.board.Square;
import chess.logic.game.Game;
import chess.logic.game.LegalityChecker;
import chess.logic.pieces.Piece;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author sami
 */
public class ChessBoardListener implements MouseListener {

    private ChessBoardDrawer board;

    public ChessBoardListener(ChessBoardDrawer board) {
        this.board = board;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = (e.getX()) / 30;
        int y = (e.getY()) / 30;
        Game game = board.getGame();
        ChessBoardLogic cbl = game.getChessBoard();
        ChessBoardLogic backUp = cbl.copy();
        LegalityChecker checker = game.getChecker();
        Player player = game.whoseTurn();

        if (cbl.withinTable(x, y)) {
            if (board.getChosen() != null && board.getPossibilities().contains(new Square(x, y))) {
                board.getChosen().move(cbl.getSquare(x, y), cbl);

                if (game.checkIfChecked(player)) {
                    cbl = backUp;
                    return;
                }

                board.setChosen(null);
                board.setPossibilities(null);
                game.nextTurn();
            } else if (checker.checkPlayerOwnsAPieceOnTheTargetSquare(game.whoseTurn(), x, y)) {
                Piece piece = cbl.getSquare(x, y).getPiece();
                board.setChosen(piece);
            }
            board.repaint();
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
