package fa;

import java.awt.Color;
import java.awt.Point;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class Algorithms {

    static boolean isCalced = false;
    static double minLength = 0;

    public static void calc() {
        if (!isCalced) {
            if (FA.points.size() > 0) {
                //skaiciujame
                algorithm();
            } else {
                JOptionPane.showMessageDialog(null, "Užduokite taškus!");
            }
        } else {
            //Jeigu algoritmas apskaiciuotas, isvedame
            JOptionPane.showMessageDialog(null, "Šiai konfiguracijai sprendimas rastas,\n minimali reikšmė " + minLength);
        }
    }

    private static void algorithm() {
        
        isCalced = true;
        //gauname matricos dydi
        int size = FA.points.size();
        int[][] back = new int[size][size];
        //sukuriame ja
        double[][] matrix = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                back[i][j] = j;
                //uzpildome ja begalybe
                matrix[i][j] = 1d / 0d;
                //dedame 0 (istrizaine)
                if (i == j) {
                    matrix[i][j] = 0d;
                }
            }
        }
        //Kopijuojame reiksmes is visu list'u
        for (int i = 0; i < size; i++) {
            Node current = FA.points.get(i);
            //visioms briaunoms
            for (Way way : current.ways) {
                //jei pirma lygi current
                if (way.first == current) {
                    //matrix[i][2-ojo indexas] = briaunos ilgis
                    matrix[i][FA.points.indexOf(way.second)] = way.length;
                    //arba	
                } else {
                    //matrix[i][1-jo indexas] = briaunos ilgis
                    matrix[i][FA.points.indexOf(way.first)] = way.length;
                }
            }
        }
        //pats Floid'o algoritmas, kaip ir nieko sunkaus
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; ++j) {
                    //jei kelias (is i i k) + (is k i j) maziau nei kelias (is i i j) is matricos 
                    //kelias (is i i j) == (is i i k) + (is k i j)
                    if (matrix[i][k] + matrix[k][j] < matrix[i][j]) {
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                        back[i][j] = back[j][i] = k;
                    }
                }
            }
        }
        //atnaujiname dabartini maziausia ilgi
        minLength = Integer.MAX_VALUE;
      //einame per visas eilutes
        for (int i = 0; i < size; i++) {
            //max atnaujiname
            double maxLength = Double.MIN_VALUE;
            int end = -1;
            //einame per stulpelius - ieskome max kelio
            for (int j = 0; j < size; j++) {
                if (maxLength <= matrix[i][j]) {
                    maxLength = matrix[i][j];
                    end = j;
                }
            }
            if (maxLength == 1d / 0d) {
                JOptionPane.showMessageDialog(null, "Klaida!");
                return;
            }
            if (maxLength < minLength) {
                minLength = maxLength;
                FA.founded.clear();
                FA.founded.add(new int[]{i, end});
            } else if (maxLength == minLength) {
                FA.founded.add(new int[]{i, end});
            }
        }
        //pereiname per masyva
        FA.founded.forEach((founded) -> {
            int start = founded[0]; //centras
            //tolimiausia virsune
            int end = founded[1];
            while (end != start) {
                //einame atbuliniu keliu
                int currBack = back[end][start];
                FA.crossed.add(FA.points.get(end));
                for (Way way : FA.points.get(end).ways) {
                    if (way.first == FA.points.get(currBack) || way.second == FA.points.get(currBack)) {
                        FA.crossed.add(way);
                    }
                }
                end = currBack;
            }
        });
        JOptionPane.showMessageDialog(null, "Sprendimas rastas, grafo centras ir kelias nuo jų iki maksimumo paryškintas,  \nminimali optimali reikšmė " + minLength);
    }

    //spalvos
    public static void setCorrectColors() {
        //briaunos ir virsunes - pilkos
        FA.points.forEach((point) -> {
            point.setForeground(Color.white);
        });
        FA.ways.forEach((way) -> {
            way.setForeground(Color.white);
        });
        //Вaplankytos - zalios
        FA.crossed.forEach((crossed) -> {
            crossed.setForeground(Color.green);
        });
        //centras - raudonas
        FA.founded.forEach((int[] queue) -> {
            FA.points.get(queue[0]).setForeground(Color.red);
        });
        Algorithms.setColor(FA.memory1, Color.red);
        Algorithms.setColor(FA.memory2, Color.red);
        setDarkerColor(BML.current);
        setDarkerColor(LML.current);
        FA.frame.repaint();
    }

    private static void setColor(JComponent component, Color color) {
        if (component != null) {
            component.setForeground(color);
        }
    }

    private static void setDarkerColor(JComponent component) {
        //metodo pavadinimas kalba apie save
        if (component != null) {
            component.setForeground(component.getForeground().darker().darker());
        }
    }

    //valymas; null
    public static void clearConfiguration() {
        FA.memory1 = FA.memory2 = null;
        FA.founded.clear();
        isCalced = false;
    }

    public static void clearAll() {
        clearConfiguration();
        //Viska isvalome
        FA.points.forEach((point) -> {
            Frame.getMainPanel().remove(point);
        });
        FA.ways.forEach((way) -> {
            Frame.getMainPanel().remove(way);
        });
        FA.points.clear();
        FA.ways.clear();
    }

    public static void randomGraph() {
        int size = Except.inputNum("Įveskite viršūnių kiekį atsitiktiniam grafui.");
        if (size == -1) {
            return;
        }
        int count = 0;
        //centrine virsune
        addPoint(0, 0);
        count++;
        int rad = 0;
        while (count < size) {
            
            rad += 100;
            //apskritimo ilgi PI*R daliname is norimo virsuniu kiekio 100
            int maxOnCurrCircle = (int) (Math.PI * rad / 100d);
            double angleDiff = 360d / (double) maxOnCurrCircle;
            int countOnCurrCircle = 0;
            //смещение
            double currAngle = count * 10;
            while (countOnCurrCircle < maxOnCurrCircle && count < size) {
                currAngle += angleDiff;
                addPoint((int) (Math.cos(Math.toRadians(currAngle)) * rad), (int) (Math.sin(Math.toRadians(currAngle)) * rad));
                countOnCurrCircle++;
                count++;
            }
        }
        //random keliai
        for (int i = 0; i < FA.points.size(); i++) {
            //Kiekvienas grafas turi tureti bent viena briauna
            //pirmam i = atsitiktinis indeksas
            int randomIndex = i;
            while (randomIndex == i) {
                randomIndex = (int) (Math.random() * FA.points.size());
            }
            //pridedame briauna prie atsitiktines virsunes
            addWay(FA.points.get(i), FA.points.get(randomIndex));
            for (int j = 0; j < FA.points.size(); j++) {
                if (i != j) {
                    double random = Math.random();
                    if (random > 0.8) {
                        addWay(FA.points.get(i), FA.points.get(j));
                    }
                }
            }
        }
    }

    private static void addPoint(int x, int y) {
        //sukureme nauja virsune, ir nutempeme ja i centra mazdaug
        Node newNode = new Node(new Point(x + (Frame.getMainPanel().getWidth() / 2), y + (Frame.getMainPanel().getHeight() / 2)));
        //pridejome i array ir i fram'a pagrindini
        FA.points.add(newNode);
        Frame.getMainPanel().add(newNode);
    }

    private static void addWay(Node first, Node second) {
        int length = (int) (Math.hypot(second.getX() - first.getX(), second.getY() - first.getY()) / 10d);
        //naujas lankas
        Way newWay = new Way(first, second, length);
        //pridejome i array ir i fram'a pagrindini
        FA.ways.add(newWay);
        Frame.getMainPanel().add(newWay);
    }

}
