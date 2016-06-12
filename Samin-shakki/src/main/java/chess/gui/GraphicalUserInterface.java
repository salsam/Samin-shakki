package chess.gui;

import chess.logic.board.StandardBoardInitializer;
import chess.logic.game.Game;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
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
        controller.setPreferredSize(new Dimension(450, 500));
        controller.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createComponents(controller.getContentPane());
        controller.pack();
        controller.setVisible(true);

    }

    private void createComponents(Container container) {
        ChessBoardDrawer window = new ChessBoardDrawer(game, 50);
        window.setPreferredSize(new Dimension(400, 400));
        window.setAlignmentX(SwingConstants.CENTER);
        window.setAlignmentY(SwingConstants.BOTTOM);

        JLabel text = new JLabel("White's turn.");
        text.setFont(new Font("Serif", Font.PLAIN, 30));
        text.setPreferredSize(new Dimension(100, 50));
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setVerticalAlignment(SwingConstants.NORTH);

        window.addMouseListener(new ChessBoardListener(window, text, 50));
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(text);
        container.add(window);

    }

    public JFrame getMainFrame() {
        return controller;
    }
}
