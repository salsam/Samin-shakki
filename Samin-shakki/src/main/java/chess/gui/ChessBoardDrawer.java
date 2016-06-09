package chess.gui;

import chess.logic.board.ChessBoardLogic;
import chess.logic.board.Square;
import chess.logic.game.Game;
import chess.logic.pieces.Piece;
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

    public ChessBoardDrawer() {
    }

    public ChessBoardDrawer(Game game) {
        this.game = game;
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
        ChessBoardLogic board = game.getChessBoard();
        if (chosen != null) {
            possibilities = chosen.possibleMoves(board);
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (possibilities != null && possibilities.contains(new Square(i, j))) {
                    graphics.setColor(Color.red);
                } else {
                    if ((i + j) % 2 == 0) {
                        graphics.setColor(Color.LIGHT_GRAY);
                    } else {
                        graphics.setColor(Color.DARK_GRAY);
                    }
                }
                graphics.fillRect(30 * i, 30 * j, 30, 30);

                if (board.getSquare(i, j).containsAPiece()) {
                    board.getSquare(i, j).getPiece().draw(graphics);
                }
            }
        }
    }

}
