package da;

import java.util.ArrayList;

public class DA {

    
    static Frame frame;
    static Node memory1, memory2 = null;
    static Node startP, finishP = null;
    static ArrayList<Node> mandatoryPoints = new ArrayList<>();
    static ArrayList<Node> points = new ArrayList<>();
    static ArrayList<Way> ways = new ArrayList<>();
    static ArrayList<Way> crossed = new ArrayList<>();

    public static void main(String[] args) {
        frame = new Frame();
        frame.init();
        frame.setVisible(true);
    }

}
