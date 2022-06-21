import java.io.*;
import java.util.*;

public class Polynomial implements Comparable<Polynomial> {

    // map in the form of <degree, coefficient>
    protected TreeMap<Integer, Integer> polynomial;
    private int degree;

    // default constructor
    public Polynomial() {
        polynomial = new TreeMap<Integer, Integer>(Collections.reverseOrder());
        polynomial.put(-1, 0);
        degree = -1;
    }

    // second constructor
    public Polynomial(int power, int coefficient) throws IllegalArgumentException {
        try {
            if (power < 0) {
                throw new IllegalArgumentException("Degree must be non-negative.");
            }
            polynomial = new TreeMap<Integer, Integer>(Collections.reverseOrder());
            polynomial.put(power, coefficient);
            degree = power;

        } catch (IllegalArgumentException e) {
            System.out.println("Degree must be non-negative.");
        }
    }

    // third constructor
    public Polynomial(TreeMap<Integer, Integer> treemap) {
        TreeMap<Integer, Integer> tm = new TreeMap<Integer, Integer>(Collections.reverseOrder());
        tm.putAll(treemap);

        int i = 0;

        for (i = tm.firstKey(); i >= tm.lastKey(); i--) {
            try {
                if (tm.containsKey(i)) {
                    if (i < 0) {
                        throw new IllegalArgumentException("Degree must be non-negative.");
                    }
                }
            } catch (IllegalArgumentException e) {
                tm.remove(i);
                System.out.println("Degree cannot be negative");
            }
        }

        polynomial = tm;
        degree = tm.firstKey();
    }

    // copy constructor
    public Polynomial(Polynomial poly) {
        this.polynomial = poly.polynomial;
        degree = poly.polynomial.firstKey();
    }

    // add
    public void add(Polynomial poly) {
        // case where inputted polynomial is longer than set polynomial
        if (poly.polynomial.firstKey() >= this.polynomial.firstKey()) {
            for (int i = poly.polynomial.firstKey(); i >= 0; i--) {
                // can only add values if degrees are equal
                if (poly.polynomial.containsKey(i) && this.polynomial.containsKey(i)) {
                    this.polynomial.put(i, this.polynomial.get(i) + poly.polynomial.get(i));
                }
                // if copying polynomial contains a degree that set polynomial does not
                if (poly.polynomial.containsKey(i) && !this.polynomial.containsKey(i)) {
                    this.polynomial.put(i, poly.polynomial.get(i));
                }
            }
        }

        // case where inputted polynomial is shorter than set polynomial
        if (this.polynomial.firstKey() > poly.polynomial.firstKey()) {
            for (int i = this.polynomial.firstKey(); i >= 0; i--) {
                // can only add values if degrees are equal
                if (poly.polynomial.containsKey(i) && this.polynomial.containsKey(i)) {
                    this.polynomial.put(i, this.polynomial.get(i) + poly.polynomial.get(i));
                }
                // if copying polynomial contains a degree that set polynomial does not
                if (poly.polynomial.containsKey(i) && !this.polynomial.containsKey(i)) {
                    this.polynomial.put(i, poly.polynomial.get(i));
                }
            }
        }
    }

    // overloaded add
    public static Polynomial add(Polynomial poly, Polynomial secondPoly) {

        Polynomial returningPolynomial = new Polynomial();

        int highestDegree = poly.polynomial.firstKey();

        if (secondPoly.polynomial.firstKey() > poly.polynomial.firstKey()) {
            highestDegree = secondPoly.polynomial.firstKey();
        }

        for (int i = poly.polynomial.firstKey(); i >= 0; i--) {
            // can only add values if degrees are equal
            if (poly.polynomial.containsKey(i) && secondPoly.polynomial.containsKey(i)) {
                returningPolynomial.polynomial.put(i,
                        secondPoly.polynomial.get(i) + poly.polynomial.get(i));
            }
            // if first polynomial contains a degree that second polynomial does not
            if (poly.polynomial.containsKey(i) && !secondPoly.polynomial.containsKey(i)) {
                returningPolynomial.polynomial.put(i, poly.polynomial.get(i));
            }

            // if second polynomial contains a degree that the first does not
            if (!poly.polynomial.containsKey(i) && secondPoly.polynomial.containsKey(i)) {
                returningPolynomial.polynomial.put(i, secondPoly.polynomial.get(i));
            }
        }

        return returningPolynomial;
    }

    // subtract
    public void subtract(Polynomial poly) {

        int highestDegree = poly.polynomial.firstKey();

        if (this.polynomial.firstKey() > poly.polynomial.firstKey()) {
            highestDegree = this.polynomial.firstKey();
        }

        for (int i = 0; i <= highestDegree; i++) {
            if (this.polynomial.containsKey(i) && poly.polynomial.containsKey(i) && this.polynomial.get(i) != 0) {
                this.polynomial.put(i, this.polynomial.get(i) - poly.polynomial.get(i));
            } else if ((!this.polynomial.containsKey(i) && poly.polynomial.containsKey(i))) {
                this.polynomial.put(i, 0 - poly.polynomial.get(i));
            }
        }
    }

    // overloaded subtract
    public static Polynomial subtract(Polynomial poly, Polynomial secondPoly) {
        Polynomial returningPolynomial = new Polynomial(secondPoly);

        int highestDegree = poly.polynomial.firstKey();

        if (secondPoly.polynomial.firstKey() > poly.polynomial.firstKey()) {
            highestDegree = secondPoly.polynomial.firstKey();
        }

        for (int i = 0; i <= highestDegree; i++) {
            if (secondPoly.polynomial.containsKey(i) && poly.polynomial.containsKey(i)
                    && secondPoly.polynomial.get(i) != 0) {
                returningPolynomial.polynomial.put(i, poly.polynomial.get(i) - secondPoly.polynomial.get(i));
            } else if ((!secondPoly.polynomial.containsKey(i) && poly.polynomial.containsKey(i))) {
                returningPolynomial.polynomial.put(i, 0 - secondPoly.polynomial.get(i));
            }
        }

        return returningPolynomial;
    }

    // multiply
    public Polynomial multiply(Polynomial px) {

        Polynomial qx = new Polynomial();

        for (int i = this.polynomial.firstKey(); i >= 0; i--) {
            if (this.polynomial.containsKey(i)) {
                for (int j = px.polynomial.firstKey(); j >= 0; j--) {
                    if (px.polynomial.containsKey(j)) {
                        if (qx.polynomial.containsKey(i + j)) {
                            qx.polynomial.put(i + j,
                                    this.polynomial.get(i) * px.polynomial.get(j) + qx.polynomial.get(i + j));
                        }
                        if (!qx.polynomial.containsKey(i + j)) {
                            qx.polynomial.put(i + j, this.polynomial.get(i) * px.polynomial.get(j));
                        }
                    }
                }
            }
        }

        return qx;

    }

    // get the degree of the polynomial
    public int degree() {
        return degree;
    }

    // get the coefficient of inputted power
    public int coefficient(int power) {
        return this.polynomial.get(power);
    }

    // change the degree POWER's coefficient
    public void changeCoefficient(int power, int newCoefficient) {
        if (this.polynomial.containsKey(power)) {
            this.polynomial.put(power, newCoefficient);
        }
    }

    // remove the degree POWER's term
    public void removeTerm(int power) {
        if (this.polynomial.containsKey(power)) {
            this.polynomial.remove(power);
        }
    }

    // evaulate polynomial at point x
    public double evaluate(double x) {

        double px = 0;

        for (int i = 0; i <= this.polynomial.firstKey(); i++) {
            if (this.polynomial.containsKey(i)) {
                px += this.polynomial.get(i) * (Math.pow(x, i));
            }
        }

        return px;
    }

    // check if two polynomials are equal
    public boolean equals(Polynomial px) {

        if (px.polynomial.size() != this.polynomial.size()) {
            return false;
        }

        for (int i = 0; i < px.polynomial.firstKey(); i++) {
            if (px.polynomial.containsKey(i)) {
                if (!this.polynomial.containsKey(i)) {
                    return false;
                }
                if (px.polynomial.get(i) != this.polynomial.get(i)) {
                    return false;
                }
            }
        }

        for (int i = 0; i < this.polynomial.firstKey(); i++) {
            if (this.polynomial.containsKey(i)) {
                if (!px.polynomial.containsKey(i)) {
                    return false;
                }
                if (px.polynomial.get(i) != this.polynomial.get(i)) {
                    return false;
                }
            }
        }

        return true;
    }

    // converting polynomial to string for printing
    public String toString() {

        StringBuilder finalString = new StringBuilder();

        // first term if degree is 1
        if (this.polynomial.firstKey() == 1) {
            if (this.polynomial.get(this.polynomial.firstKey()) > 1
                    || this.polynomial.get(this.polynomial.firstKey()) < -1) {
                finalString.append(this.polynomial.get(this.polynomial.firstKey()) + "x");
            }
            if (this.polynomial.get(this.polynomial.firstEntry()) == 1) {
                finalString.append("x");
            }
            if (this.polynomial.get(this.polynomial.firstEntry()) == -1) {
                finalString.append("-x");
            }
        }

        // first term if degree is any other num
        if (this.polynomial.firstKey() != 0 && this.polynomial.firstKey() != 1) {
            if ((this.polynomial.get(this.polynomial.firstKey()) > 1)
                    || (this.polynomial.get(this.polynomial.firstKey()) < -1)) {
                finalString.append(this.polynomial.get(this.polynomial.firstKey()) + "x" + this.polynomial.firstKey());
            }
            if (this.polynomial.get(this.polynomial.firstKey()) == 1) {
                finalString.append("x" + this.polynomial.firstKey());
            }
            if (this.polynomial.get(this.polynomial.firstKey()) == -1) {
                finalString.append("-x" + this.polynomial.firstKey());
            }
        }

        for (int i = this.polynomial.firstKey() - 1; i > 1; i--) {
            if (this.polynomial.containsKey(i)) {
                if (this.polynomial.get(i) > 1) {
                    finalString.append(" +" + this.polynomial.get(i) + "x" + i);
                }
                if (this.polynomial.get(i) < -1) {
                    finalString.append(" " + this.polynomial.get(i) + "x" + i);
                }
                if (this.polynomial.get(i) == 1) {
                    finalString.append(" +x" + i);
                }
                if (this.polynomial.get(i) == -1) {
                    finalString.append(" -x" + i);
                }
            }
        }

        // if degree is 1 after looping
        if (this.polynomial.containsKey(1)) {
            if (this.polynomial.get(1) > 1) {
                finalString.append(" +" + this.polynomial.get(1) + "x");
            }
            if (this.polynomial.get(1) < -1) {
                finalString.append(" " + this.polynomial.get(1) + "x");
            }
            if (this.polynomial.get(1) == 1) {
                finalString.append(" +x");
            }
            if (this.polynomial.get(1) == -1) {
                finalString.append(" -x");
            }
        }

        // if degree is 0 after looping
        if (this.polynomial.containsKey(0)) {
            if (this.polynomial.get(0) > 0) {
                finalString.append(" +" + this.polynomial.get(0));
            }
            if (this.polynomial.get(0) < 0) {
                finalString.append(" " + this.polynomial.get(0));
            }
        }

        return finalString.toString();
    }

    // checking to see if p(0) is greater than set polynomial at x = 0
    public int compareTo(Polynomial px) {

        if (this.evaluate(0.0) > px.evaluate(0.0)) {
            return 1;
        }
        if (this.evaluate(0.0) < px.evaluate(0.0)) {
            return -1;
        }

        return 0;

    }

}
