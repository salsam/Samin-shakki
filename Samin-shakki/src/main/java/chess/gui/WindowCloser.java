package chess.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import javax.swing.JFrame;

/**
 *
 * @author sami
 */
public class WindowCloser implements MouseListener {

    private Collection<JFrame> frames;
    private EndingScreen screen;

    public WindowCloser(Collection<JFrame> frames, EndingScreen endingScreen) {
        this.frames = frames;
        this.screen = endingScreen;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        frames.stream().forEach(frame -> {
            frame.dispose();
        });
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
