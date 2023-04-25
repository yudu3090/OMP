package da;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class Algorithms {

    static boolean isCalced = false;
    static double minLength = 0;

    public static void calc() {
        if (!isCalced) {
            if (DA.points.size() > 0) {
                //jei uzduotos pradzios ir pabaigos virsunes
                if (DA.startP != null && DA.finishP != null) {
                    //jei neapskaiciuota
                    algorithm();
                } else {
                    //jei ne uzduotos pradzia ir pabaiga
                    JOptionPane.showMessageDialog(null, "Kelio pradžia ir pabaiga neužduotos!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Užduokite taškus!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Šiai konfiguracijai sprendimas rastas,\n minimali reikšmė " + minLength);
        }
    }

    private static void algorithm() {
        isCalced = true;
        minLength = 0;
        ArrayList<Integer> mondatoryIndex = new ArrayList<>();
        for (int i = 0; i < DA.mandatoryPoints.size(); i++) {
            mondatoryIndex.add(DA.points.indexOf(DA.mandatoryPoints.get(i)));
        }
        int size = DA.points.size();
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            Node current = DA.points.get(i);
            for (Way way : current.ways) {
                if (way.first == current) {
                    matrix[i][DA.points.indexOf(way.second)] = way.length;
                    
                } else {
                    matrix[i][DA.points.indexOf(way.first)] = way.length;
                }
            }
        }
        int currentStart = DA.points.indexOf(DA.startP);
        int end = DA.points.indexOf(DA.finishP);
        double[] shortest = new double[size];
        boolean[] visited = new boolean[size];
        int[] back = new int[size];
		
		//algoritmas pats
        while (true) {
            boolean isNotFisited = true;
            int countVisited = 0;
            for (int i = 0; i < visited.length; i++) {
                shortest[i] = 1d / 0d;
                visited[i] = false;
            }
            shortest[currentStart] = 0;
            while (isNotFisited) {
                int currentIndexOfMin = -1;
                double currentMin = Double.MAX_VALUE;
                for (int i = 0; i < size; i++) {
                    if (shortest[i] < currentMin && !visited[i]) {
                        currentMin = shortest[i];
                        currentIndexOfMin = i;
                    }
                }
                if (currentIndexOfMin != -1) {
                    for (int i = 0; i < size; i++) {
                        if (matrix[currentIndexOfMin][i] != 0) {
                            double temp = currentMin + matrix[currentIndexOfMin][i];
                            if (temp < shortest[i]) {
                                shortest[i] = temp;
                                back[i] = currentIndexOfMin;
                            }
                        }
                    }
                    visited[currentIndexOfMin] = true;
                }
                countVisited++;
                isNotFisited = false;
                for (int i = 0; i < size; i++) {
                    if (!visited[i]) {
                        isNotFisited = true;
                    }
                }
                if (countVisited > size) {
                    JOptionPane.showMessageDialog(null, "Klaida!"); 
                    return;
                }
            }
        //ieskome minimumo trumpaiusiui keliui ( su privalomais taskais)
            int minMondatory = -1;
            double tempMin = Double.MAX_VALUE;
            for (int i = 0; i < shortest.length; i++) {
                if (shortest[i] < tempMin && mondatoryIndex.contains(i)) {
                    minMondatory = i;
                    tempMin = shortest[i];
                }
            }
            int currentEnd;
            if (minMondatory == -1) {
                currentEnd = end;
            } else {
                currentEnd = minMondatory;
            }
            int memory = currentEnd;
            minLength += shortest[currentEnd];
            while (currentEnd != currentStart) {
                mondatoryIndex.remove((Integer) currentEnd);
                for (Way way : DA.points.get(currentEnd).ways) {
                    if (way.first == DA.points.get(back[currentEnd]) || way.second == DA.points.get(back[currentEnd])) {
                       
                        DA.crossed.add(way);
                    }
                }
                currentEnd = back[currentEnd];
            }
            mondatoryIndex.remove((Integer) currentEnd);
            if (memory == end) {
                break;
            }
            currentStart = memory;
        }
       
        JOptionPane.showMessageDialog(null, "Minimalus kelio ilgis: " + minLength);
       
        DA.frame.repaint();
    }

    public static void clearConfiguration() {
        DA.startP = DA.finishP = DA.memory1 = DA.memory2 = null;
        DA.mandatoryPoints.clear();
        DA.crossed.clear();
        isCalced = false;
    }

    public static void clearAll() {
        clearConfiguration();
        DA.points.forEach((point) -> {
            Frame.getMainPanel().remove(point);
        });
        DA.ways.forEach((way) -> {
            Frame.getMainPanel().remove(way);
        });
        DA.points.clear();
        DA.ways.clear();
    }

    public static void randomGraph() {
        int size = Except.inputNum("Įvęskite viršūnių kiekį atsitiktiniam grafui.");
        if (size == -1) {
            return;
        }
        int count = 0;
        addPoint(0, 0);
        count++;
        int rad = 0;
        while (count < size) {
            rad += 100;
            int maxOnCurrCircle = (int) (Math.PI * rad / 100d);
            double angleDiff = 360d / (double) maxOnCurrCircle;
            int countOnCurrCircle = 0;
            double currAngle = count * 10;
            while (countOnCurrCircle < maxOnCurrCircle && count < size) {
                currAngle += angleDiff;
                addPoint((int) (Math.cos(Math.toRadians(currAngle)) * rad), (int) (Math.sin(Math.toRadians(currAngle)) * rad));
                countOnCurrCircle++;
                count++;
            }
        }
        for (int i = 0; i < DA.points.size(); i++) {
            int randomIndex = i;
            while (randomIndex == i) {
                randomIndex = (int) (Math.random() * DA.points.size());
            }
            addWay(DA.points.get(i), DA.points.get(randomIndex));
            for (int j = 0; j < DA.points.size(); j++) {
                if (i != j) {
                    double random = Math.random();
                    if (random > 0.8) {
                        addWay(DA.points.get(i), DA.points.get(j));
                    }
                }
            }
        }
    }

    private static void addPoint(int x, int y) {
        Node newNode = new Node(new Point(x + (Frame.getMainPanel().getWidth() / 2), y + (Frame.getMainPanel().getHeight() / 2)));
        DA.points.add(newNode);
        Frame.getMainPanel().add(newNode);
    }

    private static void addWay(Node first, Node second) {
        int length = (int) (Math.hypot(second.getX() - first.getX(), second.getY() - first.getY()) / 10d);
        Way newWay = new Way(first, second, length);
        DA.ways.add(newWay);
        Frame.getMainPanel().add(newWay);
    }

    public static void setCorrectColors() {
        DA.points.forEach((point) -> {
            point.setForeground(Color.white);
        });
        DA.ways.forEach((way) -> {
            way.setForeground(Color.white);
        });
        DA.mandatoryPoints.forEach((mP) -> {
            mP.setForeground(Color.green);
        });
        DA.crossed.forEach((queue) -> {
            queue.setForeground(Color.red);
        });
        Algorithms.setColor(DA.startP, Color.blue);
        Algorithms.setColor(DA.finishP, Color.blue);
        Algorithms.setColor(DA.memory1, Color.red);
        Algorithms.setColor(DA.memory2, Color.red);
        setColorB(BML.current);
        setColorB(LML.current);
        DA.frame.repaint();
    }

    private static void setColor(JComponent component, Color color) {
        if (component != null) {
            component.setForeground(color);
        }
    }

    private static void setColorB(JComponent component) {
        if (component != null) {
            component.setForeground(component.getForeground().darker().darker());
        }
    }

}
