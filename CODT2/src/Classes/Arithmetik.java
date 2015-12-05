package Classes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author fisch
 */
public class Arithmetik {

    // Multiplikation von Polynomen
    public static Polynom multiply(Polynom p1, Polynom p2, Polynom pRed, int intF) {

        // Polynom erstellen, dass den Grad beider Eingangspolynome multiplizert ist
        Polynom polyRe = multiply(p1, p2, intF);

        // Jetzt solange mit dem Reduktionspolynom erweitern, bis ein Polynom kleiner
        // dem Reduktionspolynom herauskommt. Dazu immer mit Vielfachen des 
        // Reduktionspolynoms addieren und dann das entstandene Polynom verkleinern
        while (polyRe.getDegree() >= pRed.getDegree()) {

            // Wert am höchsten Exponenten holen
            int deg = polyRe.getDegree();
            int iCE = polyRe.data.get(deg);

            // Polynom erstellen, das mit dem reduktionspolynom multipliziert wird
            Polynom pMul = new Polynom(deg - pRed.getDegree());
            pMul.data.set(pMul.getDegree(), intF - iCE);

            // Vielfaches des Reduktionspolynoms erstellen und mit der Rueckgabe 
            // addieren
            polyRe = add(multiply(pMul, pRed, intF), polyRe, intF);

            // Verkleinern
            polyRe.reduce();
        }

        // Polynomdatenstrucktur auf tatsaechliche Polynomlaenge kuerzen, dann zurueckgeben
        return polyRe;

    }

    // Multiplikation von Polynomen
    public static Polynom multiply(Polynom p1, Polynom p2, int intF) {

        // Polynom erstellen, dass den Grad beider Eingangspolynome multiplizert ist
        Polynom polyRe = new Polynom(p1.getDegree() + p2.getDegree());

        // Arithmetik alle Exponenten multiplizieren und sukzessive aufaddieren
        for (int iG1 = 0; iG1 <= p1.getDegree(); iG1++) {
            int i1 = p1.data.get(iG1);
            
            if (i1 > 0) {
                for (int iG2 = 0; iG2 <= p2.getDegree(); iG2++) {
                    int i2 = p2.data.get(iG2);

                    if (i2 > 0) {
                        int i3 = polyRe.data.get(iG1 + iG2);

                        polyRe.data.set(iG1 + iG2, (i1 * i2 + i3)%intF);
                    }
                }
            }
            
        }
        // Polynom ggf. reduzieren auf die tatsächlichen Exponenten
        polyRe.reduce();
        return polyRe;

    }

    // Addition von Polynomen
    public static Polynom add(Polynom p1, Polynom p2, int intF) {

        // Polynom erstellen von dem höchsten Grad
        int iMax = Math.max(p1.data.size(), p2.data.size());
        Polynom polyRe = new Polynom(iMax);

        // Arithmetik
        for (int i = 0; i < iMax; i++) {
            int i1 = (i < p1.data.size()) ? p1.data.get(i) : 0;
            int i2 = (i < p2.data.size()) ? p2.data.get(i) : 0;
            polyRe.data.set(i, (i1 + i2) % intF);
        }

        // Polynomdatenstrucktur auf tatsaechliche Polynomlaenge kuerzen, dann zurueckgeben
        polyRe.reduce();
        return polyRe;

    }

}
