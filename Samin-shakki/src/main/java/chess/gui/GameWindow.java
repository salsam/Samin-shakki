package chess.gui;

import chess.logic.game.Game;
import chess.logic.guilogic.GUILogic;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author sami
 */
public class GameWindow extends JFrame {

    private Game game;
    private GUILogic guiLogic;

    public GameWindow(GUILogic guiLogic, Game game) {
        this.game = game;
        this.guiLogic = guiLogic;
        this.setPreferredSize(new Dimension(450, 500));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createComponents(this.getContentPane());
        this.pack();
        this.setVisible(false);
    }

    public Game getGame() {
        return game;
    }

    private void createComponents(Container container) {
        JLabel text = new JLabel("WHITE's turn.");
        text.setFont(new Font("Serif", Font.PLAIN, 30));
        text.setPreferredSize(new Dimension(100, 50));
        text.setAlignmentX(CENTER_ALIGNMENT);
        text.setAlignmentY(TOP_ALIGNMENT);
        guiLogic.setTextArea(text);

        ChessBoardDrawer window = new ChessBoardDrawer(guiLogic, game, 50);
        window.setPreferredSize(new Dimension(400, 400));
        window.setAlignmentX(CENTER_ALIGNMENT);
        window.setAlignmentY(CENTER_ALIGNMENT);
        window.addMouseListener(new ChessBoardListener(guiLogic, window, 50));

        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(text);
        container.add(window);
    }

}
