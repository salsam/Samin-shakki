package chess.gui;

import chess.logic.game.Game;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author sami
 */
public class ChessBoardDrawer extends JPanel {

    private Game game;

    public ChessBoardDrawer(Game game) {
        this.game = game;
        super.setBackground(Color.LIGHT_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(30 * i, 30 * j, 30, 30);
            }
        }
    }

}
