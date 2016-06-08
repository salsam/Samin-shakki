package chess.gui;

import chess.logic.board.ChessBoardLogic;
import chess.logic.game.Game;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author sami
 */
public class ChessBoard extends JPanel {

    private Game game;

    public ChessBoard() {
    }

    public ChessBoard(Game game) {
        this.game = game;
        super.setBackground(Color.CYAN);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponents(graphics);
        ChessBoardLogic board = game.getChessBoard();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    graphics.setColor(Color.LIGHT_GRAY);
                } else {
                    graphics.setColor(Color.DARK_GRAY);
                }
                graphics.fillRect(30 * i, 30 * j, 30, 30);
                
                if (board.getSquare(i, j).containsAPiece()) {
                    board.getSquare(i, j).getPiece().draw(graphics);
                }
            }
        }
    }

}
