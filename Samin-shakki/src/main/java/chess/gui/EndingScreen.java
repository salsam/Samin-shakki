package chess.gui;

import chess.logic.game.Game;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.Collection;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author sami
 */
public class EndingScreen extends JFrame {

    private Game game;

    public EndingScreen(Game game, Collection<JFrame> windows) throws HeadlessException {
        this.setPreferredSize(new Dimension(300, 300));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initComponents(this.getContentPane(), windows);
        this.pack();
        this.setVisible(true);
    }

    public Game getGame() {
        return game;
    }

    private void initComponents(Container container, Collection<JFrame> windows) {
        JButton playAgain = new JButton("Play again");
        playAgain.addMouseListener(new GameRestarter(this));
        JButton exit = new JButton("Exit");
        exit.addMouseListener(new WindowCloser(windows, this));

        container.setPreferredSize(new Dimension(300, 300));
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(playAgain);
        container.add(exit);
    }

}
