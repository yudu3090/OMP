package da;

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
    public void mouseReleased(MouseEvent e) {
        if (!Algorithms.isCalced) {
            //priklausomai nuo paspaustos klavisos
            switch (e.getButton()) {
                case 1:
                    if (DA.memory1 == null) {
                        DA.memory1 = current;
                    } else if (current != DA.memory1) {
                        DA.memory2 = current;
                        int length = Except.inputNum("Įvęskite briaunos ilgį.");
                        if (length == -1) {
                            DA.memory1 = null;
                            DA.memory2 = null;
                            return;
                        }
                        Way way = new Way(DA.memory1, DA.memory2, length);
                        DA.ways.add(way);
                        Frame.getMainPanel().add(way);
                        DA.memory1 = null;
                        DA.memory2 = null;
                    } else {
                        DA.memory1 = null;
                        DA.memory2 = null;
                    }
                    break;
                case 2:
                    if (!DA.mandatoryPoints.contains(current)) {
                        DA.mandatoryPoints.add(current);
                    } else {
                        DA.mandatoryPoints.remove(current);
                    }
                    break;
                case 3:
                    if (DA.startP == current || DA.finishP == current) {
                        if (DA.startP == current) {
                            DA.startP = null;
                        }
                        if (DA.finishP == current) {
                            DA.finishP = null;
                        }
                    } else {
                        if (DA.startP == null) {
                            DA.startP = current;
                        } else if (DA.finishP == null) {
                            DA.finishP = current;
                        }
                    }
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Jeigu norite pakeisti nustatymus/konfiguracijas, spauskite Esc");
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
