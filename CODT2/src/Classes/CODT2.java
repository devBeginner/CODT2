package Classes;

import java.text.DecimalFormat;


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

//        Polynom test = new Polynom("1000");
//        Polynom test2 = new Polynom("20011");       
//        System.out.println(Arithmetik.multiply(new Polynom("1000"), new Polynom("20011"), 3));
//        System.out.println(Arithmetik.multiply(new Polynom("0020"), new Polynom("20011"), 3));
//        System.out.println(Arithmetik.multiply(new Polynom("0001"), new Polynom("20011"), 3));
//        System.out.println(Arithmetik.multiply(new Polynom("1021"), new Polynom("20011"), 3));
//        System.out.println(Arithmetik.multiply(new Polynom("20011"), new Polynom("1021"), 3));
//        System.out.println(Arithmetik.multiply(new Polynom("11"), new Polynom("11"), new Polynom("111"), 2));
//        System.out.println(Arithmetik.add(test, test2, 2));
//        
//        aufgabe3(2 , 2, new Polynom("111"));
//        aufgabe3(2, 3, new Polynom("1101"));
//        aufgabe3(2 , 4, new Polynom("11001"));
//        aufgabe3(3 , 2, new Polynom("112"));
//        aufgabe3(5 , 2, new Polynom("112"));
//        aufgabe3(5 , 3, new Polynom("1032"));
        aufgabe5(7);
//        aufgabe5(9);
    }

    public static void aufgabe5(int iField) {

        String strFormat = " %1s ";
        String strHeader = String.format(strFormat, "\\");
        String strOut = "";
        
        // Addition
        System.out.println("Addition Table:\n");
        for (int i = 0; i < iField; i++) {
            
            strHeader += String.format(strFormat, i);
            strOut += String.format(strFormat, i);
            
            for (int i2 = 0; i2 < iField; i2++) {
                strOut += String.format(strFormat, (i + i2) % iField);
            }
            strOut += "\n";
        }
        
        System.out.println(strHeader + "\n" +strOut);
        
        // Multiplikation
        strHeader = String.format(strFormat, "\\");
        strOut = "";
        
        System.out.println("Multiplication Table:\n");
        for (int i = 0; i < iField; i++) {
            
            strHeader += String.format(strFormat, i);
            strOut += String.format(strFormat, i);
            
            for (int i2 = 0; i2 < iField; i2++) {
                strOut += String.format(strFormat, (i * i2) % iField);
            }
            strOut += "\n";
        }
        
        System.out.println(strHeader + "\n" +strOut);
        
    }

    public static void aufgabe3(long p, long d, Polynom FvonX) {

        long pExpD = (long) Math.pow((double) p, (double) d);
        long countWrong = 0;

        System.out.println("Number of polynoms for d=" + d + " and p=" + p + ": " + pExpD);
        System.out.println(String.format("| %4s | %4s | %4s | %8s | %8s | %8s | %8s | %8s | %8s | %8s |", "Ai", "Bi", "Ci", "A", "B", "C", "AB", "BC", "A_BC", "AB_C"));
        System.out.println();
        for (long Ai = 0; Ai < pExpD; Ai++) {
            Polynom A = new Polynom(p, d, Ai);

            for (long Bi = 0; Bi < pExpD; Bi++) {
                Polynom B = new Polynom(p, d, Bi);

                for (long Ci = 0; Ci < pExpD; Ci++) {
                    Polynom C = new Polynom(p, d, Ci);

                    Polynom AB = Arithmetik.multiply(A, B, FvonX, (int) p);
                    Polynom BC = Arithmetik.multiply(B, C, FvonX, (int) p);
                    Polynom AB_C = Arithmetik.multiply(AB, C, FvonX, (int) p);
                    Polynom A_BC = Arithmetik.multiply(A, BC, FvonX, (int) p);

                    System.out.println(String.format("| %4s | %4s | %4s | %8s | %8s | %8s | %8s | %8s | %8s | %8s |", Ai, Bi, Ci, A, B, C, AB, BC, A_BC, AB_C));
//                    System.out.println("Ai=" + Ai + "\tBi=" + Bi + "\tCi=" + Ci + "\tA=" + String.format("%10s",A) + "\tB=" + String.format("%10s",B) + "\tC=" + String.format("%10s",C) + "\tAB_C=" + String.format("%10s",AB_C) + "\tA_BC=" + String.format("%10s",A_BC));

                    if (!(AB_C.compareTo(A_BC))) {
                        countWrong++;
                    }

                }
            }
        }

        System.out.println("All polynomes were multiplied and compared!");
        System.out.println("Association is " + ((countWrong > 0) ? "not " : "") + "given!");
    }
}
