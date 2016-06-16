package chess.gui.boarddrawing;

import chess.gui.boarddrawing.PieceDrawer;
import chess.logic.game.Game;
import chess.logic.inputprocessing.InputProcessor;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author sami
 */
public class ChessBoardDrawer extends JPanel {

    private Game game;
    private InputProcessor guiLogic;
    private int sideLength;
    private PieceDrawer pieceDrawer;

    public ChessBoardDrawer(InputProcessor guiLogic, Game game, int sideLength) {
        this.game = game;
        this.guiLogic = guiLogic;
        this.sideLength = sideLength;
        this.pieceDrawer = new PieceDrawer();
        super.setBackground(Color.CYAN);
    }

    public Game getGame() {
        return game;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponents(graphics);

        for (int i = 0; i < game.getChessBoard().getTable().length; i++) {
            for (int j = 0; j < game.getChessBoard().getTable()[0].length; j++) {
                if (guiLogic.getPossibilities() != null && guiLogic.getPossibilities().contains(game.getChessBoard().getSquare(i, j))) {
                    graphics.setColor(Color.red);
                } else if ((i + j) % 2 == 0) {
                    graphics.setColor(Color.LIGHT_GRAY);
                } else {
                    graphics.setColor(Color.DARK_GRAY);
                }
                graphics.fillRect(sideLength * i, sideLength * j, sideLength, sideLength);

                if (game.getChessBoard().getSquare(i, j).containsAPiece()) {
                    pieceDrawer.draw(game.getChessBoard().getSquare(i, j).getPiece(), graphics, sideLength);
                }
            }
        }
    }

}
