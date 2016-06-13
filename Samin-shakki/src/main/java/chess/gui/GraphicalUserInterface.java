package chess.gui;

import chess.logic.board.StandardBoardInitializer;
import chess.logic.game.Game;
import javax.swing.JFrame;

/**
 *
 * @author sami
 */
public class GraphicalUserInterface implements Runnable {

    private JFrame mainFrame;
    private JFrame gameWindow;
    private Game game;

    public GraphicalUserInterface() {
        game = new Game(new StandardBoardInitializer());
        this.gameWindow = new GameWindow(game);
        this.mainFrame = new MainFrame(gameWindow);
        this.gameWindow.setVisible(false);
    }

    @Override
    public void run() {
        mainFrame.setVisible(true);
    }
}
