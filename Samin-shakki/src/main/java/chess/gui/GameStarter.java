package chess.gui;

import chess.logic.game.Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author sami
 */
public class GameStarter implements ActionListener {

    private Game game;

    public GameStarter(Game game) {
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.start();
    }

}
