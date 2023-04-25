package da;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LML implements MouseListener {

    static Way current;

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        current = (Way) e.getComponent();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //current = tuscias
        current = null;
    }

}
