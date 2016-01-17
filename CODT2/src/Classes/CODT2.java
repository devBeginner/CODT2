package Classes;

import java.text.DecimalFormat;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author fisch
 */
public class CODT2 {

    public static DecimalFormat decForm = new DecimalFormat("000000");

    // Programmstart
    public static void main(String[] args) {

        // ++++++++++++++++++++++++++++++++++++++
        // Aufgabe 3
        if (false) {
            System.out.println("AUFGABE 3:");
            aufgabe3(2, 4);
        }

        // ++++++++++++++++++++++++++++++++++++++
        // Aufgabe 4
        if (false) {
            System.out.println("AUFGABE 4:");
            aufgabe4(2, 2, new Polynom("111"));
            aufgabe4(2, 3, new Polynom("1101"));
            aufgabe4(2, 4, new Polynom("11001"));
            aufgabe4(3, 2, new Polynom("112"));
            aufgabe4(5, 2, new Polynom("112"));
            aufgabe4(5, 3, new Polynom("1032"));
        }
        // ++++++++++++++++++++++++++++++++++++++
        // Aufgabe 5
        if (false) {
            System.out.println("AUFGABE 5:");
            aufgabe5(8, 2, 3, new Polynom("1101"));
            aufgabe5(9, 3, 2, new Polynom("101"));
        }
    }

    /**
     * Die Funktion ist das Gerüst fuer Aufgabe 3
     *
     * @param basis Basis, mit der gerechnet wird (wird zur Modulorechnung der
     * Koeffizienten verwendet)
     * @param elemente Anzahl der Elemente(Koeffizienten) in dem Polynom
     */
    public static void aufgabe3(long basis, long elemente) {

        // Anzahl der Elemente bestimmen, die durch die uebergabe Parameter erzeugt werden koennen.
        // entspricht der Basis ^ Elemente(des Polynoms)
        long pExpD = (long) Math.pow((double) basis, (double) elemente);

        // Anzzahl der Fehler bei der Multiplikation
        long countWrong = 0;

        // Formatstring fuer die Ausgabe der Tabelle 
        String strFormat = "| %4s | %4s | %4s | %" + elemente + "s | %" + elemente + "s | %" + elemente + "s | %" + 2 * elemente + "s | %" + 2 * elemente + "s | %" + elemente * 3 + "s | %" + elemente * 3 + "s |";

        // Initialausgabe mit dem Setup
        System.out.println("Number of polynoms for elemente=" + elemente + " and basis=" + basis + ": " + pExpD);
        System.out.println(String.format(strFormat, "Ai", "Bi", "Ci", "A", "B", "C", "AB", "BC", "A_BC", "AB_C"));
        System.out.println();

        // Alle Indizes der Polynome durchlaufen fuer A
        for (long Ai = 0; Ai < pExpD; Ai++) {

            // neues Polynom erstellen mit den Eingabeparametern und dem aktuellen Index
            Polynom A = new Polynom(basis, elemente, Ai);

            // Alle Indizes der Polynome durchlaufen fuer B
            for (long Bi = 0; Bi < pExpD; Bi++) {

                // neues Polynom erstellen mit den Eingabeparametern und dem aktuellen Index
                Polynom B = new Polynom(basis, elemente, Bi);

                // Alle Indizes der Polynome durchlaufen fuer C
                for (long Ci = 0; Ci < pExpD; Ci++) {

                    // neues Polynom erstellen mit den Eingabeparametern und dem aktuellen Index
                    Polynom C = new Polynom(basis, elemente, Ci);

                    // AB und BC berechnen
                    Polynom AB = Arithmetik.multiply(A, B, (int) basis);
                    Polynom BC = Arithmetik.multiply(B, C, (int) basis);

                    // AB*C und A*BC berechnen
                    Polynom AB_C = Arithmetik.multiply(AB, C, (int) basis);
                    Polynom A_BC = Arithmetik.multiply(A, BC, (int) basis);

                    // Ausgabe der Tabellenzeile
                    System.out.println(String.format(strFormat, Ai, Bi, Ci, A, B, C, AB, BC, A_BC, AB_C));

                    // Falls (AB)C nicht A(BC) ist, den Zaehler erhoehen
                    if (!(AB_C.compareTo(A_BC))) {
                        countWrong++;
                    }

                }
            }
        }

        System.out.println("All polynomes were multiplied and compared!");
        System.out.println("Association is " + ((countWrong > 0) ? "not " : "") + "given!");

    }

    /**
     * Die Funktion ist das Gerüst fuer Aufgabe 4 (Wie 3 nur mit Reduktion)
     *
     * @param basis Basis, mit der gerechnet wird (wird zur Modulorechnung der
     * Koeffizienten verwendet)
     * @param elemente Anzahl der Elemente(Koeffizienten) in dem Polynom
     * @param FvonX Verwendetes Reduktionspolynom
     */
    public static void aufgabe4(long basis, long elemente, Polynom FvonX) {

        String strout = "";

        // Anzahl der Elemente bestimmen, die durch die uebergabe Parameter erzeugt werden koennen.
        // entspricht der Basis ^ Elemente(des Polynoms)
        long pExpD = (long) Math.pow((double) basis, (double) elemente);

        // Anzzahl der Fehler bei der Multiplikation
        long countWrong = 0;

        // Formatstring fuer die Ausgabe der Tabelle 
        String strFormat = "| %4s | %4s | %4s | %" + elemente + "s | %" + elemente + "s | %" + elemente + "s | %" + 2 * elemente + "s | %" + 2 * elemente + "s | %" + elemente * 2 + "s | %" + elemente * 2 + "s |";

        // Initialausgabe mit dem Setup
        System.out.println("Number of polynoms for elemente=" + elemente + " and basis=" + basis + ": " + pExpD);
        System.out.println(String.format(strFormat, "Ai", "Bi", "Ci", "A", "B", "C", "AB", "BC", "A_BC", "AB_C"));
        System.out.println();

        // Alle Indizes der Polynome durchlaufen fuer A
        for (long Ai = 0; Ai < pExpD; Ai++) {

            // neues Polynom erstellen mit den Eingabeparametern und dem aktuellen Index
            Polynom A = new Polynom(basis, elemente, Ai);

            // Alle Indizes der Polynome durchlaufen fuer B
            for (long Bi = 0; Bi < pExpD; Bi++) {

                // neues Polynom erstellen mit den Eingabeparametern und dem aktuellen Index
                Polynom B = new Polynom(basis, elemente, Bi);

                // Alle Indizes der Polynome durchlaufen fuer C
                for (long Ci = 0; Ci < pExpD; Ci++) {

                    // neues Polynom erstellen mit den Eingabeparametern und dem aktuellen Index
                    Polynom C = new Polynom(basis, elemente, Ci);

                    // AB und BC berechnen
                    Polynom AB = Arithmetik.multiply(A, B, FvonX, (int) basis);
                    Polynom BC = Arithmetik.multiply(B, C, FvonX, (int) basis);

                    // AB*C und A*BC berechnen
                    Polynom AB_C = Arithmetik.multiply(AB, C, FvonX, (int) basis);
                    Polynom A_BC = Arithmetik.multiply(A, BC, FvonX, (int) basis);

                    // Ausgabe der Tabellenzeile
                    strout += String.format(strFormat, Ai, Bi, Ci, A, B, C, AB, BC, A_BC, AB_C) + "\n";

                    // Falls (AB)C nicht A(BC) ist, den Zaehler erhoehen
                    if (!(AB_C.compareTo(A_BC))) {
                        countWrong++;
                    }

                }
            }
            System.out.println(strout);
            strout = "";

        }
        System.out.println("All polynomes were multiplied and compared!");
        System.out.println("Association is " + ((countWrong > 0) ? "not " : "") + "given!");

    }

    /**
     *
     *
     *
     * @param iField
     * @param field_base
     * @param length
     * @param poly_Reduce
     */
    public static void aufgabe5(int iField, int field_base, int length, Polynom poly_Reduce) {
        
        String strFormat = " %1s ";

        // ++++++++++++++++++++++++++++++++++++++
        // Alle polynome erstellen fuer die uebergebenen Parameter
        ArrayList<Polynom> polys = new ArrayList<>();
        for (int i = 0; i < iField; i++) {
            polys.add(new Polynom(field_base, length, i));
        }

        // ++++++++++++++++++++++++++++++++++++++
        // Additionstabelle erstellen
        
        String strHeader = "\nAddition Table:\n" + String.format(strFormat, "\\");
        String strOut = "";

        // Alle erstellten Polynome durchlaufen...
        for (int i = 0; i < iField; i++) {

            // Header ausgeben
            strHeader += String.format(strFormat, i);
            strOut += String.format(strFormat, i);

            // ...und mit jedem anderen Polynom addieren und zur Ausgabe hinzufuegen
            for (int i2 = 0; i2 < iField; i2++) {

                // Die beiden Polynome addieren
                Polynom poly_add = Arithmetik.add(polys.get(i), polys.get(i2), field_base);

                // Ergebnis zur Zeile hinzufuegen
                strOut += String.format(strFormat, poly_add.getStringValue(field_base));
            }

            // Additionszeile abschliessen
            strOut += "\n";
        }
        System.out.println(strHeader + "\n" + strOut);

        // ++++++++++++++++++++++++++++++++++++++
        // Multiplikationstabelle
        strHeader = "\nMultiplication Table:\n" +  String.format(strFormat, "\\");
        strOut = "";
        
        // Alle Polynome durchlaufen und...
        for (int i = 0; i < iField; i++) {
            
            // Tabellen Header ausgeben
            strHeader += String.format(strFormat, i);
            strOut += String.format(strFormat, i);
            
            // ... mit allen anderen multiplizieren
            for (int i2 = 0; i2 < iField; i2++) {
                
                // Mulktiplikation ausfuehren
                Polynom poly_mul = Arithmetik.multiply(polys.get(i), polys.get(i2), poly_Reduce, field_base);
                
                // Zur Tabellenzeile hinzufuegen
                strOut += String.format(strFormat, poly_mul.getStringValue(field_base));

            }
            // Tabellenzeile abschließen
            strOut += "\n";
        }

        System.out.println(strHeader
                + "\n" + strOut);

    }

}
