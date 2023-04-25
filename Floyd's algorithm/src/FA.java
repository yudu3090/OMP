package fa;

import java.util.ArrayList;
import javax.swing.JComponent;

public class FA {

    //Panele - kur viska paisysime
    static Frame frame;
    //cia saugosime duomenys apie 2 virsunes, tarp kuriu bus briauna
    static Node memory1, memory2 = null;
    //visu virsuniu masyvas
    static ArrayList<Node> points = new ArrayList<>();
    //visu briaunu masyvas
    static ArrayList<Way> ways = new ArrayList<>();
    //centru masyvas ir ju tolimiausiu virsuniu
    static ArrayList<int[]> founded = new ArrayList<>();
    //komponentu masyvas
    static ArrayList<JComponent> crossed = new ArrayList<>();

    public static void main(String[] args) {
        
        frame = new Frame();
        frame.init();
        frame.setVisible(true);
    }

}
