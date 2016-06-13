package chess.gui;

import chess.logic.game.Game;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author samisalo
 */
public class GameWindow extends JFrame {

    private Game game;

    public GameWindow(Game game) {
        this.game = game;
        this.setPreferredSize(new Dimension(450, 500));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createComponents(this.getContentPane());
        this.pack();
        this.setVisible(false);
    }

    public Game getGame() {
        return game;
    }

    private void createComponents(Container container) {
        ChessBoardDrawer window = new ChessBoardDrawer(game, 50);
        window.setPreferredSize(new Dimension(400, 400));
        window.setAlignmentX(CENTER_ALIGNMENT);
        window.setAlignmentY(CENTER_ALIGNMENT);

        JLabel text = new JLabel("WHITE's turn.");
        text.setFont(new Font("Serif", Font.PLAIN, 30));
        text.setPreferredSize(new Dimension(100, 50));
        text.setAlignmentX(CENTER_ALIGNMENT);
        text.setAlignmentY(TOP_ALIGNMENT);

        window.addMouseListener(new ChessBoardListener(window, text, 50));
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(text);
        container.add(window);
    }

}
