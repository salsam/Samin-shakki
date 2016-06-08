package chess.gui;

import chess.logic.board.ChessBoardLogic;
import chess.logic.pieces.Piece;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author sami
 */
public class ChessBoardListener implements MouseListener {

    private ChessBoard board;

    public ChessBoardListener(ChessBoard board) {
        this.board = board;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        ChessBoardLogic cbl = board.getGame().getChessBoard();

        if (cbl.withinTable(x / 30, y / 30)) {
            if (cbl.getSquare(x / 30, y / 30).containsAPiece()) {
                Piece piece = cbl.getSquare(x/30, y/30).getPiece();
                board.setPossibilities(piece.possibleMoves(cbl));
                board.repaint();
            }
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
