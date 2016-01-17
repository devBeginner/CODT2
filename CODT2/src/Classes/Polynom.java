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

    /**
     * Erstellt das Polynom
     *
     * @param iField    Feld in dem gerechnet werden soll
     * @param iLength   Anzahl der Koeffizienten, die das Polynom besitzen soll
     * @param index     Zahldarstellung des Polynoms
     */
    public Polynom(long iField, long iLength, long index) {

        long index_save = index;
        data = new ArrayList<>();

        // Polynom mit 0en initialisieren
        for (int i = 0; i < iLength; i++) {
            data.add(0);
        }

        // ermitteln, wie oft die einzelnen exponenten im polynom vorkommen
        // und als liste speichern (der hoechste exponent ist hier bei index 0)
        for (long i = iLength - 1; i >= 0; i--) {

            // aktuellen Wert mit dem Exponenten ermitteln und pruefen, 
            // wie oft der Wert in den restlichen Index passt.
            long pow = (long) (Math.pow((double) iField, (double) i));
            long count = (index_save / pow);

            // Wenn der ermittelte Wert in den Index passt, die Anzahl der 
            // Vorkommen in das Polynom eintragen und dann das Vielfache des 
            data.set((int) i, (int) count);
            index_save -= pow * count;
        }
        
//        System.out.println("generated polynom for iField=" + iField + " iLength=" + iLength + " index="+ index + " : " + this);
    
    }

    /**
     *
     * Erstellt ein Polynom des angegebnenen Grades. Jeder Koeffizient ist 0
     *
     * @param iDeg Grad des Polynoms, das erstellt werden soll
     */
    public Polynom(int iDeg) {
        data = new ArrayList<>();
        for (int i = 0; i <= iDeg; i++) {
            data.add(0);
        }
    }

    /**
     * Wandelt einen uebergebenen String in ein Polynom um, dass die
     * Koeffizienten entsprechend der Zahlen innerhalb des Strings besitzt. Der
     * Koeffizient des niedrigsten Exponenten ist im String ganz rechts.
     *
     * @param strPoly String Repraesentation des Polynoms
     */
    public Polynom(String strPoly) {
        
//        System.out.println("Create polynom from string: " + strPoly);
        
        data = new ArrayList<>();
        for (int i = strPoly.length() - 1; i >= 0; i--) {
            data.add(Integer.parseInt(String.valueOf(strPoly.charAt(i))));
        }
    }

    /**
     * Ermittelt den Grad des Polynoms (hoechster Exponent) Der Grad entspricht
     * der Anzahl der Koeffizienten des Polynoms -1, da der Grad des ersten
     * Exponenten 0 ist.
     *
     * @return
     */
    public int getDegree() {

        int ret = 0;

        // Sicherstellen, dass mindestens 1 Element vorhanden ist
        if (data.size() == 0) {
            data.add(0);
        } else {

            // Den hoechsten Grad mit Koeffizient != 0 ermitteln
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i) > 0) {
                    ret = i;
                }
            }
        }
        
        return ret;
    }

    /**
     * Kürzt den Grad, wenn die obersten Exponenten nicht verwendet werden Die
     * Funktion entfernt alle Koeffizienten, die 0 sind, begonnen am hoechsten
     * solang bis ein Koeffizient != 0 gefunden wird.
     */
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

    /**
     * Vergleicht das uebergebene Polynom mit diesem, indem alle Koeffizienten
     * einzeln verglichen werden.
     *
     * @param pComp Vergleichspolynom
     * @return Gibt zurueck, ob die Polynome gleich sind
     */
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

    /**
     * Die Funktion berechnet den Wert, den das Polynom repraesentiert. Es
     * werden alle Koeffizienten durchlaufen und mithilfe der uebergebenen Basis
     * und dem Exponenten fuer diesen Koeffizienten, kann dann ein Wert
     * berechnet werden.
     *
     * BSP: Das Polynom 203 mit der Basis 4 entspricht folgendem Wert: 3 * (4^0)
     * + 0 * (4^1) + 2 * (4^2) = 3 + 0 + 32 = 35
     *
     * @param base Die Basis, die verwendet wird, um den Rueckgabewert zu
     * bestimmen
     * @return String des bestimmten Wertes
     */
    public String getStringValue(int base) {

        int val = 0;

//        for (int i = data.size()-1 ; i >= 0 ; i--){
        for (int i = 0; i < data.size(); i++) {
            val += (double) data.get(i) * (int) Math.pow(base, (double) i);
        }

        return "" + val;
    }

}
