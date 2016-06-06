package chess.gui;

import chess.logic.board.StandardBoardInitializer;
import chess.logic.game.Game;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author sami
 */
public class GraphicalUserInterface implements Runnable {

    private JFrame mainFrame;
    private ChessBoardDrawer drawer;
    private Game game;

    public GraphicalUserInterface() {
        game = new Game(new StandardBoardInitializer());
        drawer = new ChessBoardDrawer(game);
        game.setChessBoardDrawer(drawer);
    }

    @Override
    public void run() {
        mainFrame = new JFrame("Chess");
        mainFrame.setPreferredSize(new Dimension(666, 666));
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createComponents(mainFrame.getContentPane());
        mainFrame.pack();
        mainFrame.setVisible(true);

    }

    private void createComponents(Container container) {
        container.setLayout(new BorderLayout());
        JButton start = new JButton("New game");
        start.addActionListener(new GameStarter(game));
        container.add(start, BorderLayout.NORTH);
        container.add(drawer, BorderLayout.CENTER);
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }
}
