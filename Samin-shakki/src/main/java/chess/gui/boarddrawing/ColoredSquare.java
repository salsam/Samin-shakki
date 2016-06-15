package chess.gui.boarddrawing;

import chess.logic.board.Square;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author sami
 */
public class ColoredSquare extends JPanel {

    private Color color;
    private int sideLength;
    private Square location;
    private PieceDrawer pieceDrawer;

    public ColoredSquare(Square square, int sideLength, boolean isRed) {
        this.sideLength = sideLength;
        this.location = square;
        this.pieceDrawer = new PieceDrawer();

        if (isRed) {
            color = Color.RED;
        } else if ((square.getColumn() + square.getRow()) % 2 == 0) {
            color = Color.LIGHT_GRAY;
        } else {
            color = Color.DARK_GRAY;
        }
        this.setBorder(new LineBorder(Color.BLACK));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(sideLength * location.getColumn(), sideLength * location.getRow(), sideLength, sideLength);
        if (location.containsAPiece()) {
            pieceDrawer.draw(location.getPiece(), g, sideLength);
        }
    }

}
