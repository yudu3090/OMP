package fa;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

public class BML implements MouseListener {

    static Node current;

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    //klavisa paspausta
    public void mouseReleased(MouseEvent e) {
        //jei algoritmas neapskaiciuotas
        if (!Algorithms.isCalced) {
            //priklauso nuo to ka paspaudeme
            switch (e.getButton()) {
                case 1:
                    if (FA.memory1 == null) {
                        FA.memory1 = current;
                    } else if (current != FA.memory1) {
                        FA.memory2 = current;
                        int length = Except.inputNum("Įveskite briaunos ilgį");
                        if (length == -1) {
                            //valome
                            FA.memory1 = null;
                            FA.memory2 = null;
                            return;
                        }
                        //kelias nuo vienos atminties iki antros
                        Way way = new Way(FA.memory1, FA.memory2, length);
                        //pridedame i masyva
                        FA.ways.add(way);
                        //pridedame i panele
                        Frame.getMainPanel().add(way);
                        //valome
                        FA.memory1 = null;
                        FA.memory2 = null;
                    } else {
                        //valome
                        FA.memory1 = null;
                        FA.memory2 = null;
                    }
                    break;
            }
        } else {
            //jei rezultatas apskaiciuotas
            JOptionPane.showMessageDialog(null, "Paspauskite Esc, kad pakeisti nustatymus.");
        }
    }

 
    @Override
    public void mouseEntered(MouseEvent e) {
        current = (Node) e.getComponent();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        current = null;
    }
}
