package chess.gui;

import chess.logic.game.Game;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
        initComponents();
        this.pack();
        this.setVisible(true);
    }

    public void initComponents() {
        JLabel text = new JLabel();
        JPanel panel = new ChessBoard(game);
        this.add(text);
        this.add(panel);
    }

}
