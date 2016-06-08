package chess.gui;

import chess.logic.game.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author sami
 */
public class GameStarter implements ActionListener {

    private Game game;
    private JFrame main;

    public GameStarter(Game game, JFrame main) {
        this.game = game;
        this.main = main;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        main.setVisible(false);
        GameWindow window = new GameWindow(game);
    }

}
