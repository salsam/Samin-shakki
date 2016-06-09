package chess.gui;

import chess.logic.board.StandardBoardInitializer;
import chess.logic.game.Game;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author sami
 */
public class GraphicalUserInterface implements Runnable {

    private JFrame controller;
    private Game game;

    public GraphicalUserInterface() {
    }

    @Override
    public void run() {
        controller = new JFrame();
        game = new Game(new StandardBoardInitializer());
        controller.setPreferredSize(new Dimension(666, 666));
        controller.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createComponents(controller.getContentPane());
        controller.pack();
        controller.setVisible(true);

    }

    private void createComponents(Container container) {
        ChessBoardDrawer window = new ChessBoardDrawer(game);
        window.addMouseListener(new ChessBoardListener(window));
        container.add(window);

    }

    public JFrame getMainFrame() {
        return controller;
    }
}
