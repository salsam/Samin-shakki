package chess.gui;

import chess.logic.game.Game;
import chess.logic.guilogic.InputProcessor;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;

/**
 *
 * @author sami
 */
public class GraphicalUserInterface implements Runnable {

    private Map<String, JFrame> frames;

    public GraphicalUserInterface(InputProcessor inputProcessor, Game game) {
        frames = new HashMap();
        frames.put("game", new GameWindow(inputProcessor, game));
        frames.put("main", new MainFrame(frames.get("game")));
        frames.put("endingScreen", new EndingScreen(frames));
        inputProcessor.setFrames(frames);
    }

    @Override
    public void run() {
        frames.get("main").setVisible(true);
    }
}
