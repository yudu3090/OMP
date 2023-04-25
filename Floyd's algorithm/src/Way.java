package fa;

import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JLabel;

public class Way extends JLabel {

    //taskai, jungiantis vienas kita
    final Node first, second;
    //briaunos ilgis
    final int length;
    final boolean up;
    final Point firstC, secondC;

    //konstruojame
    Way(Node first, Node second, int length) {
        this.first = first;
        this.second = second;
        this.length = length;
        firstC = first.getCenter();
        secondC = second.getCenter();
        up = (secondC.x - firstC.x) * (secondC.y - firstC.y) > 0;
        //Inicializuojame
        init();
    }

    private void init() {
       
        setSize(Math.max(Math.abs(secondC.x - firstC.x), 15), Math.max(Math.abs(secondC.y - firstC.y), 15));
        int minX = Math.min(firstC.x, secondC.x);
        int minY = Math.min(firstC.y, secondC.y);
        int maxY = Math.max(firstC.y, secondC.y);
        if (up) {
            setLocation(minX, minY);
        } else {
            setLocation(minX, maxY - getHeight());
        }
        //Teksto nustatymai
        setVerticalAlignment(CENTER);
        setHorizontalAlignment(CENTER);

        setText(length + "");

        Way deleted = null;
        //tikriniame ar nepasikartoja briaunos
        for (Way way1 : first.ways) {
            for (Way way2 : second.ways) {
                if (way1 == way2) {
                    //jei radome, tai triname
                    deleted = way1;
                }
            }
        }
        if (deleted != null) {
           //triname
            first.remove(deleted);
            second.remove(deleted);
            Frame.getMainPanel().remove(deleted);
        }
        //listener
        LML lml = new LML();
        //pridedame prie briaunos
        addMouseListener(lml);
       
        first.ways.add(this);
        second.ways.add(this);
    }

  
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
       
        if (up) {
            g.drawLine(0, 0, getWidth(), getHeight());
        } else {
            g.drawLine(0, getHeight(), getWidth(), 0);
        }
    }

}
