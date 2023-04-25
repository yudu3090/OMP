package fa;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JOptionPane;

public class FML implements MouseListener, MouseMotionListener, KeyListener {

    //dabartine pelytes padetis 
    static Point location = new Point(0, 0);

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    //klavisa nepaspausta
    @Override
    public void mouseReleased(MouseEvent e) {
        //jei vis dar neapskaiciuotas algoritmas
        if (!Algorithms.isCalced) {
            //naujas taska su nauja peles lokacija
            Node newNode = new Node(location);
            //pridedame i masyva ir i panele
            FA.points.add(newNode);
            Frame.getMainPanel().add(newNode);
            //triname atminti
            FA.memory1 = FA.memory2 = BML.current = null;
        } else {
            //jei sekmingai apskaiciuota viskas
            JOptionPane.showMessageDialog(null, "Для изменения конфигурации нажмите Esc");
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

    //keiciame pelytes lokacija
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

    //klaviaturos paspaudimai
    @Override
    public void keyReleased(KeyEvent e) {
        //Jei paspausta - delete
        if (e.getKeyCode() == KeyEvent.VK_DELETE) {
            Algorithms.clearConfiguration();
            try {
                //Jei virsune neturi briaunu
                if (BML.current.ways.isEmpty()) {
                    //Triname is masyvo ir paneles
                    FA.points.remove(BML.current);
                    Frame.getMainPanel().remove(BML.current);
                } else {
                    BML.current.ways.forEach((way) -> {
                        //is paneles
                        Frame.getMainPanel().remove(way);
                    });
                    //valome masyva
                    BML.current.ways.clear();
                    FA.points.remove(BML.current);
                    Frame.getMainPanel().remove(BML.current);
                }
            } catch (Exception ex) {
            }
            try {
                //triname briauna is masyvu ir paneles
                FA.ways.remove(LML.current);
                Frame.getMainPanel().remove(LML.current);
                LML.current.first.ways.remove(LML.current);
                LML.current.second.ways.remove(LML.current);
            } catch (Exception ex) {
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            //skaiciavimai
            Algorithms.calc();
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            //valome konfiguracija
            Algorithms.clearConfiguration();
        }
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            //valome viska
            Algorithms.clearAll();
        }
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            //valome viska
            Algorithms.clearAll();
            //kuriame atsitiktini grafa
            Algorithms.randomGraph();
        }
    }
}
