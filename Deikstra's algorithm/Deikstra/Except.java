package da;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;

public class Except {

    public static int inputNum(String text) {
        int num = 0;
        boolean isTrue = false;
        while (!isTrue) {
            String numString = input(text);
            if (numString == null || "".equals(numString)) {
                return -1;
            } else {
                try {
                    num = Integer.parseInt(numString);
                } catch (HeadlessException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Klaida! Ivęskite skaičių.");
                    break;
                }
                if (num <= 1) {
                    JOptionPane.showMessageDialog(null, "Klaida! Skaičius turi būti daugiau nei 1.");
                } else {
                    isTrue = true;
                }
            }
        }
        return num;
    }

    public static String input(String text) {
        String string;
        string = JOptionPane.showInputDialog(text);
        return string;
    }
}
