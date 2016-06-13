package chess.gui;

import chess.logic.board.Square;
import chess.logic.game.Game;
import chess.pieces.Piece;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Set;
import javax.swing.JPanel;

/**
 *
 * @author sami
 */
public class ChessBoardDrawer extends JPanel {

    private Game game;
    private Set<Square> possibilities;
    private Piece chosen;
    private int sideLength;
    private PieceDrawer pieceDrawer;

    public ChessBoardDrawer(Game game, int sideLength) {
        this.game = game;
        this.sideLength = sideLength;
        this.pieceDrawer = new PieceDrawer();
        super.setBackground(Color.CYAN);
    }

    public Piece getChosen() {
        return chosen;
    }

    public void setChosen(Piece chosen) {
        this.chosen = chosen;
    }

    public Game getGame() {
        return game;
    }

    public Set<Square> getPossibilities() {
        return this.possibilities;
    }

    public void setPossibilities(Set<Square> possibilities) {
        this.possibilities = possibilities;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponents(graphics);
        if (chosen != null) {
            possibilities = game.getChessBoard().getMovementLogic().possibleMoves(chosen, game.getChessBoard());
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (possibilities != null && possibilities.contains(new Square(i, j))) {
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
