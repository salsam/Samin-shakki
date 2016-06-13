package chess.gui;

import chess.logic.game.Game;
import chess.logic.guilogic.GUILogic;
import javax.swing.JFrame;

/**
 *
 * @author sami
 */
public class GraphicalUserInterface implements Runnable {

    private JFrame mainFrame;
    private JFrame gameWindow;

    public GraphicalUserInterface(GUILogic guiLogic, Game game) {
        this.gameWindow = new GameWindow(guiLogic, game);
        this.mainFrame = new MainFrame(gameWindow);
    }

    @Override
    public void run() {
        mainFrame.setVisible(true);
    }
}
