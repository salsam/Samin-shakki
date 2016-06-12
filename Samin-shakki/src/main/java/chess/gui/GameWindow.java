package chess.gui;

import chess.logic.game.Game;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
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

    public GameWindow(Game game) throws HeadlessException {
        this.game = game;
        this.setPreferredSize(new Dimension(666, 666));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initComponents(this.getContentPane());
        this.pack();
        this.setVisible(true);
    }

    private void initComponents(Container container) {
        JLabel text = new JLabel("Aaaaa");
        ChessBoardDrawer panel = new ChessBoardDrawer(game, 30);
        panel.setPreferredSize(new Dimension(240, 240));
        panel.addMouseListener(new ChessBoardListener(panel, text, 30));
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(text);
        container.add(panel);
    }

}
