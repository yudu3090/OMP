package da;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Frame extends JFrame {

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Algorithms.setCorrectColors();
    }
    private final static Dimension FS = new Dimension(800, 600);
    private final static Dimension LS = new Dimension(300, 20);
    private final static FML FML = new FML();
    private static JLabel label1, label2, label3, label4, label5, label6, label7;
    private static JPanel textPanel;
    private static JPanel mainPanel;

    public void init() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - FS.width) / 2, (screenSize.height - FS.height) / 2, FS.width, FS.height);
        setTitle("Dijkstra's algorithm");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setMainPanel(new JPanel());
        getMainPanel().setLayout(null);
        getMainPanel().setBackground(Color.darkGray);
        getMainPanel().setSize(FS);
        getMainPanel().setLocation(0, 0);
        //inicijavimas
        initTextPanel();
        //Дtekstne panele
        getMainPanel().add(textPanel);
        //listeneriai
        addMouseListener(FML);
        addMouseMotionListener(FML);
        addKeyListener(FML);

        add(getMainPanel());
    }

    //darome panele
    private void initTextPanel() {
        //gridas
        textPanel = new JPanel(new GridLayout(7, 1));
        textPanel.setSize(LS.width, LS.height * 7);
        //lokalizacija
        textPanel.setLocation(6, getHeight() - LS.height * 7 - 40);
        //spalva
        textPanel.setBackground(Color.darkGray);
        //programeles instrukcijos
        label1 = new JLabel("LMClick - pridėti naują viršūnę/briauną");
        label1.setSize(LS.width, LS.height);
        label1.setForeground(Color.white);
        textPanel.add(label1);

        label2 = new JLabel("RMClick - nustatyti kelio pradžią ir pabaigą");
        label2.setSize(LS.width, LS.height);
        label2.setForeground(Color.white);
        textPanel.add(label2);

        label3 = new JLabel("CMClick - nustatyti privalomas viršūnes");
        label3.setSize(LS.width, LS.height);
        label3.setForeground(Color.white);
        textPanel.add(label3);

        label4 = new JLabel("SPACE - apskaičiuoti rezultatą");
        label4.setSize(LS.width, LS.height);
        label4.setForeground(Color.white);
        textPanel.add(label4);

        label5 = new JLabel("ESC -   iš naujo   DEL - trinti viršūnę/briauną");
        label5.setSize(LS.width, LS.height);
        label5.setForeground(Color.white);
        textPanel.add(label5);

        label6 = new JLabel("BACKSPACE - viską išvalyti");
        label6.setSize(LS.width, LS.height);
        label6.setForeground(Color.white);
        textPanel.add(label6);

        label7 = new JLabel("CNTRL - Atsitiktinis grafas");
        label7.setSize(LS.width, LS.height);
        label7.setForeground(Color.white);
        textPanel.add(label7);
    }

    public static JPanel getMainPanel() {
        return mainPanel;
    }

    public static void setMainPanel(JPanel aMainPanel) {
        mainPanel = aMainPanel;
    }
}
