package chess.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author sami
 */
public class GameRestarter implements MouseListener {

    private EndingScreen screen;

    public GameRestarter(EndingScreen screen) {
        this.screen = screen;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        screen.getGame().reset();
        screen.dispose();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

}
