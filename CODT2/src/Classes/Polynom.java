/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;

/**
 *
 * @author fisch
 */
public class Polynom {

    public ArrayList<Integer> data = new ArrayList<>();

    private Polynom() {
    }

    public Polynom(long p, long d, long index) {

//        System.out.println("Generating polynom for p=" + p + " d=" + d + " index="+ index);
        long save = index;
        ArrayList<Integer> tmp = new ArrayList<>();
        data = new ArrayList<>();

        // ermitteln, wie oft die einzelnen exponenten im polynom vorkommen
        // und als liste speichern (der hoechste exponent ist hier bei index 0)
        for (long i = d - 1; i >= 0; i--) {
            long pow = (long)(Math.pow((double) p, (double) i));
            long count = (save / pow);
            tmp.add((int) count);
            if (save >= pow) {
                save -= pow * count;
            }
        }

        // erstelle liste rueckwaerts in die eigentliche liste schreiben
        for (int i = tmp.size() - 1; i >= 0; i--) {
            data.add(tmp.get(i));
        }

    }

    public Polynom(int iDeg) {
        data = new ArrayList<>();
        for (int i = 0; i <= iDeg; i++) {
            data.add(0);
        }
    }

    public Polynom(String strPoly) {
        System.out.println("Create polynom from string: " + strPoly);
        data = new ArrayList<>();
        for (int i = strPoly.length() - 1; i >= 0; i--) {
            data.add(Integer.parseInt(String.valueOf(strPoly.charAt(i))));
        }
    }

    //Grad des Polynoms
    public int getDegree() {
        return data.size() - 1;
    }

    // Kürzt den Grad, wenn die obersten Exponenten nicht verwendet werden
    public void reduce() {
        // Polynom vom Groessten Exponentan an durchlaufen und wenn 0 den 
        // Eintrag loeschen, bis ein Eintrag != 0 gefunden wird.
        int i = data.size();
        while (--i >= 0 && data.get(i) == 0) {
            data.remove(i);
        }
    }

    @Override
    public String toString() {

        String strRet = "";//"Polynom (degree " + getDegree() + "):\n";
        int i = data.size();
        while (--i >= 0) {
            strRet += data.get(i);
        }
        return strRet;
    }

    public boolean compareTo(Polynom pComp) {

        int max = Math.max(data.size(), pComp.data.size());

        // Alle Stellen vergleichen und falls eine unterschiedliche gefunden 
        // wurde, false zurueck geben. Wenn der index bei einem array nicht 
        // existiert, wird das durch eine 0 vertreten
        for (int i = 0; i < max; i++) {
            int i1 = (i < data.size()) ? data.get(i) : 0;
            int i2 = (i < pComp.data.size()) ? pComp.data.get(i) : 0;
            if (i1 != i2) {
                return false;
            }
        }

        return true;

    }

}
