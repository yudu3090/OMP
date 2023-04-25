package da;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JLabel;

public class Node extends JLabel {
//dimensiojs
    private final static Dimension BS = new Dimension(40, 40);
    private final static Dimension DIV = new Dimension(8, 32);
    private final Point location;
    public final ArrayList<Way> ways;

    Node(Point location) {
        this.location = location;
        ways = new ArrayList<>();
        init();
    }

    public Point getCenter() {
        return new Point(getX() + getWidth() / 2, getY() + getHeight() / 2);
    }
//inicijavimai, lokalizacija
    private void init() {
        setBounds(location.x - BS.width / 2 - DIV.width, location.y - BS.height / 2 - DIV.height, BS.width, BS.height);
      
        setVisible(true);
        setEnabled(false);
        repaint();
        addMouseListener(new BML());
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(TOP);
        setText(DA.points.size() + "");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawOval(2, 2, BS.width - 3, BS.height - 3);
        g.drawOval(1, 1, BS.width - 2, BS.height - 2);
        g.drawOval(0, 0, BS.width - 1, BS.height - 1);
    }
}
