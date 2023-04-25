package fa;

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
        //cia viska sumesta i viena vieta, kad nereiketu kviesti daug kartu
        Algorithms.setCorrectColors();
    }
    //konstantos
    //lango dydis
    private final static Dimension FS = new Dimension(800, 600);
    private final static Dimension LS = new Dimension(300, 20);
    //naujas listeneris
    private final static FML FML = new FML();
    //label
    private static JLabel label1, label2, label3, label4, label5;
    //panele
    private static JPanel textPanel;
    //pagrindine panele
    private static JPanel mainPanel;

    public void init() {
        //ekrano dydziai
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //dedame programele centre
        setBounds((screenSize.width - FS.width) / 2, (screenSize.height - FS.height) / 2, FS.width, FS.height);
        //programeles pavadinimas
        setTitle("Floyd's algorithm");
        setResizable(false);
        //kad uzsidarytu (jei spaudi kryziuka)
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setMainPanel(new JPanel());
        getMainPanel().setLayout(null);
        getMainPanel().setBackground(Color.darkGray);
        getMainPanel().setSize(FS);
        getMainPanel().setLocation(0, 0);
        //inicializacija
        initTextPanel();
        //teksto panele, kur instrukcijas butu
        getMainPanel().add(textPanel);
        //pridedame listenerius
        addMouseListener(FML);
        addMouseMotionListener(FML);
        addKeyListener(FML);

        add(getMainPanel());
    }

    //Pati panele
    private void initTextPanel() {
        //grid'as
        textPanel = new JPanel(new GridLayout(5, 1));
        //dydziai
        textPanel.setSize(LS.width, LS.height * 5);
        //Lokacija
        textPanel.setLocation(6, getHeight() - LS.height * 5 - 40);
        //Spalva
        textPanel.setBackground(Color.darkGray);
        //Musu instrukcijos. Pridedame i panele
        label1 = new JLabel("LMClick - pridėti naują viršūnę/briauną");
        label1.setSize(LS.width, LS.height);
        label1.setForeground(Color.white);
        textPanel.add(label1);

        label2 = new JLabel("SPACE - apskaičiuoti rezultatą");
        label2.setSize(LS.width, LS.height);
        label2.setForeground(Color.white);
        textPanel.add(label2);

        label3 = new JLabel("ESC -   iš naujo   DEL - trinti viršūnę/briauną");
        label3.setSize(LS.width, LS.height);
        label3.setForeground(Color.white);
        textPanel.add(label3);

        label4 = new JLabel("BACKSPACE - viską išvalyti");
        label4.setSize(LS.width, LS.height);
        label4.setForeground(Color.white);
        textPanel.add(label4);

        label5 = new JLabel("CNTRL - Atsitiktinis grafas");
        label5.setSize(LS.width, LS.height);
        label5.setForeground(Color.white);
        textPanel.add(label5);
    }

    public static JPanel getMainPanel() {
        return mainPanel;
    }

    public static void setMainPanel(JPanel aMainPanel) {
        mainPanel = aMainPanel;
    }
}
