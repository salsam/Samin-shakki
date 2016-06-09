package chess.gui;

import chess.logic.game.Game;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
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
        ChessBoardDrawer panel = new ChessBoardDrawer(game);
        panel.addMouseListener(new ChessBoardListener(panel));
        container.add(text);
        container.add(panel);
    }

}
