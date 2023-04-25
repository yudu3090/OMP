package fa;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;

public class Except {

    //skaiciaus ivedimas
    public static int inputNum(String text) {
        int num = 0;
        boolean isTrue = false;
        //ciklas, kol true = true
        while (!isTrue) {
            String numString = input(text);
            //jei eilute tuscia arba neuzduota -1
            if (numString == null || "".equals(numString)) {
                return -1;
            } else {
                try {
                    //gauti skaiciu is eilutes
                    num = Integer.parseInt(numString);
                    //klaida
                } catch (HeadlessException | NumberFormatException e) {
                    //isvedame pranesima
                    JOptionPane.showMessageDialog(null, "Klaida! Įveskite skaičių!");
                    break;
                }
                //jei iki siol viskas gerai
                if (num <= 1) {
                    //pranesimas
                    JOptionPane.showMessageDialog(null, "Klaida! Skaičius turi būti didesnis nei 1!");
                } else {
                     //jei ir cia gerai, reiksme true
                    isTrue = true;
                }
            }
        }
        //graziname skaiciu
        return num;
    }

    public static String input(String text) {
        String string;
        string = JOptionPane.showInputDialog(text);
        //graziname
        return string;
    }
}
