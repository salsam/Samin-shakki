package chess.gui;

import chess.logic.board.StandardBoardInitializer;
import chess.logic.game.Game;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author sami
 */
public class MainFrame extends JFrame {

    private Game game;

    public MainFrame() throws HeadlessException {
        this.game = new Game(new StandardBoardInitializer());
        this.setPreferredSize(new Dimension(666, 666));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initComponents(this.getContentPane());
        this.pack();
        this.setVisible(true);
    }

    private void initComponents(Container container) {
        JLabel head = new JLabel("Chess");
        JButton start = new JButton("New game");
        start.addActionListener(new GameStarter(game, this));
        container.setLayout(new BorderLayout());
        container.add(head, BorderLayout.NORTH);
        container.add(start, BorderLayout.CENTER);
    }

}
