package da;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JOptionPane;

public class FML implements MouseListener, MouseMotionListener, KeyListener {

    static Point location = new Point(0, 0);

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!Algorithms.isCalced) {
            Node newNode = new Node(location);
            DA.points.add(newNode);
            Frame.getMainPanel().add(newNode);
            DA.memory1 = DA.memory2 = BML.current = null;
        } else {
            JOptionPane.showMessageDialog(null, "Jeigu norite pakeisti nustatymus/konfiguracijas, spauskite Esc");
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        location = e.getPoint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //jei paspausta delete
        if (e.getKeyCode() == KeyEvent.VK_DELETE) {
            Algorithms.clearConfiguration();
            try {
                //jei nera briaunu
                if (BML.current.ways.isEmpty()) {
                    DA.points.remove(BML.current);
                    Frame.getMainPanel().remove(BML.current);
                } else {
                    BML.current.ways.forEach((way) -> {
                        Frame.getMainPanel().remove(way);
                    });
                    BML.current.ways.clear();
                    DA.points.remove(BML.current);
                    Frame.getMainPanel().remove(BML.current);
                }
            } catch (Exception ex) {
            }
            try {
                DA.ways.remove(LML.current);
                Frame.getMainPanel().remove(LML.current);
                LML.current.first.ways.remove(LML.current);
                LML.current.second.ways.remove(LML.current);
            } catch (Exception ex) {
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            Algorithms.calc();
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Algorithms.clearConfiguration();
        }
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            Algorithms.clearAll();
        }
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            Algorithms.clearAll();
            Algorithms.randomGraph();
        }
    }
}
