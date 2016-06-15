package chess.gui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author sami
 */
public class MainFrame extends JFrame {

    private GameWindow gameWindow;

    public MainFrame(JFrame gameWindow) {

        this.gameWindow = (GameWindow) gameWindow;
        this.setPreferredSize(new Dimension(450, 500));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initComponents(this.getContentPane());
        this.pack();
        this.setVisible(false);

    }

    public GameWindow getGameWindow() {
        return gameWindow;
    }

    private void initComponents(Container container) {
        JLabel head = new JLabel("Chess");
        head.setMaximumSize(new Dimension(100, 100));
        head.setAlignmentX(CENTER_ALIGNMENT);
        head.setAlignmentY(TOP_ALIGNMENT);

        JButton start = new JButton("New game");
        start.setMaximumSize(new Dimension(300, 300));
        start.setAlignmentX(CENTER_ALIGNMENT);
        start.addActionListener(new GameStarter(this));
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(head);
        container.add(start);
    }

}
