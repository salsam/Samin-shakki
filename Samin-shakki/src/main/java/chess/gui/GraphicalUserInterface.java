package chess.gui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author sami
 */
public class GraphicalUserInterface implements Runnable {

    private JFrame mainFrame;

    public GraphicalUserInterface() {
    }

    @Override
    public void run() {
        mainFrame = new MainFrame();
        mainFrame.setPreferredSize(new Dimension(666, 666));
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createComponents(mainFrame.getContentPane());
        mainFrame.pack();
        mainFrame.setVisible(true);

    }

    private void createComponents(Container container) {
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }
}
