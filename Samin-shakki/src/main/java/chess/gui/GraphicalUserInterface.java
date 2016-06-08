package chess.gui;

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
        controller.setPreferredSize(new Dimension(666, 666));
        controller.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createComponents(controller.getContentPane());
        controller.pack();
        controller.setVisible(false);

    }

    private void createComponents(Container container) {
//        MainFrame main = new MainFrame(game);
//        GameWindow window = new GameWindow(game);
//        container.add(main);
//        container.add(window);

    }

    public JFrame getMainFrame() {
        return controller;
    }
}
