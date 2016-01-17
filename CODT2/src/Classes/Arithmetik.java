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

    /**
     * Multipliziert die beiden Eingangspolynome und reduziert anschliessend 
     * mithilfe des Reduktionspolynoms, damit der Grad des Ergebnispolynoms
     * kleiner als der des Reduktionspolynoms ist.
     * 
     * @param poly1
     * @param poly2
     * @param polyReduce
     * @param iField
     * @return
     */
    public static Polynom multiply(Polynom poly1, Polynom poly2, Polynom polyReduce, int iField) {

        // Polynom erstellen, dass den Grad beider Eingangspolynome multiplizert ist
        Polynom polyMult = multiply(poly1, poly2, iField);
        polyMult = reduce(polyMult, polyReduce, iField);

        // Polynomdatenstrucktur auf tatsaechliche Polynomlaenge kuerzen, dann zurueckgeben
        return polyMult;

    }

    // Multiplikation von Polynomen

    /**
     * Multipliziert die zwei polynome, ohne zu reduzieren. Das Polynom, das zurueck
     * gegeben wird, besitzt als Grad die Summe der Grade der Eingangspolynome
     * 
     * @param poly1
     * @param poly2
     * @param iField
     * @return
     */
    public static Polynom multiply(Polynom poly1, Polynom poly2, int iField) {

        // Polynom erstellen, dass den Grad beider Eingangspolynome multiplizert ist
        Polynom polyMult = new Polynom(poly1.getDegree() + poly2.getDegree());

        // Alle Grade des ersten Polynoms durchlaufen        
        for (int grad_P1 = poly1.getDegree(); grad_P1 >= 0; grad_P1--) {

            // Koeffizient ermitteln
            int coeff_P1 = poly1.data.get(grad_P1);

            // wenn der Koeffizient des ersten Polynoms > 0 ist
            if (coeff_P1 > 0) {

                // Alle Grade des zweiten Polynoms durchlaufen
                for (int grad_P2 = poly2.getDegree(); grad_P2 >= 0; grad_P2--) {

                    // Koeffizient ermitteln
                    int coeff_P2 = poly2.data.get(grad_P2);

                    // Wenn der Koeffizient > 0 ist, dann ausmultiplizieren
                    if (coeff_P2 > 0) {

                        // Aktuellen Wert aus dem neuen Polynom ermitteln
                        int coeff_neu = polyMult.data.get(grad_P1 + grad_P2);

                        polyMult.data.set(grad_P1 + grad_P2, (coeff_P1 * coeff_P2 + coeff_neu) % iField);
                    }
                }
            }

        }
        // Polynom ggf. reduzieren auf die tatsächlichen Exponenten
//        polyMult.reduce();
        return polyMult;

    }

    /**
     * Reduziert das uebergebene Polynom mit dem Reduktionspolynom, solange bis der
     * Grad des Polynoms kleiner als der des Reduktionspolynoms ist.
     * 
     * @param poly
     * @param polyReduce
     * @param iField
     */
    public static Polynom reduce(Polynom poly, Polynom polyReduce, int iField) {
        
        // Polynom fuer die Rueckgabe erstellen (Kopie von Poly)
        Polynom poly_Ret = add(new Polynom("0"), poly, iField);
        
        // Grad des Reduktionspolynoms
        int grad_polyRed = polyReduce.getDegree();
        int grad_poly = poly_Ret.getDegree();
        
        // solange, wie der Grad des Polynoms groesser als der Grad des Reduktions-
        // Polynoms ist, muss ein Vielfaches des Reduktionspolynoms auf das Polynom 
        // addiert werden, dass der Grad verschwindet.
        while (grad_poly >= grad_polyRed) {

            // Koeffizienten ermitteln
            int coeff_poly = poly_Ret.data.get(grad_poly);
            int grad_diff = grad_poly - grad_polyRed;
            
            // Polynom erstellen, das mit dem reduktionspolynom multipliziert werden
            // soll, um das vielfache zum Reduzieren zu erhalten
            Polynom polyVielfaches = new Polynom(grad_diff);
            polyVielfaches.data.set(grad_diff, iField - coeff_poly);
            
            polyVielfaches = multiply(polyVielfaches, polyReduce, iField);
            
            // Vielfaches des Reduktionspolynoms erstellen
            poly_Ret = add(polyVielfaches, poly_Ret, iField);

            // neuen Grad ermitteln
            grad_poly = poly_Ret.getDegree();

        }
        
        return poly_Ret;
    }

    /**
     * Addiert alle Koeffizienten der gleichen Grade und rechnet das Ergebnis 
     * Modulo dem uebergebenen Feld
     * 
     * @param poly1
     * @param poly2
     * @param iField
     * @return
     */
    public static Polynom add(Polynom poly1, Polynom poly2, int iField) {
        
        int grad_poly1 = poly1.getDegree();
        int grad_poly2 = poly2.getDegree();
        
        // Hoechsten Grad der beiden Polynome bestimmen
        int grad_max = Math.max(grad_poly1, grad_poly2);
        
        // Ergebnispolynom erstellen mit dem hoechsten Grad
        Polynom poly_erg = new Polynom(grad_max);

        // Alle Grade, bis zum hoechsten durchlaufen
        for (int grad = 0; grad <= grad_max; grad++) {
            
            // Koeffizienten der beiden Polynome an dem Grad ermitteln
            // Falls der Grad des Polynoms kleiner als der aktuelle Grad ist,
            // wird fuer den Koeffizienten dieses Grades eine 0 verwendet
            int coeff_P1 = (grad <= grad_poly1) ? poly1.data.get(grad) : 0;
            int coeff_P2 = (grad <= grad_poly2) ? poly2.data.get(grad) : 0;
            
            // Die beiden Koeffizienten addieren und MOD Field rechnen und in 
            // dem Ergebnispolynom einfuegen
            poly_erg.data.set(grad, (coeff_P1 + coeff_P2) % iField);
        }
        
//        System.out.println("add p1:"+poly1+" p2:"+poly2 +" modulo:" +iField + " == " + poly_erg);
        
        return poly_erg;

    }

}
