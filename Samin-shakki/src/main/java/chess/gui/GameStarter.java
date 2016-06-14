package chess.gui;

import chess.logic.board.chessboardinitializers.ChessBoardInitializer;
import chess.logic.board.chessboardinitializers.StandardBoardInitializer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author sami
 */
public class GameStarter implements ActionListener {

    private MainFrame main;
    private ChessBoardInitializer init;

    public GameStarter(MainFrame main) {
        this.main = main;
        init = new StandardBoardInitializer();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        main.setVisible(false);
        init.initialize(main.getGameWindow().getGame().getChessBoard());
        main.getGameWindow().setVisible(true);
    }

}
