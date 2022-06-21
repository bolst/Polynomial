import java.io.*;
import java.util.*;

public class Quadratic extends Polynomial {

    private double root1;
    private double root2;
    Polynomial quadratic;

    public Quadratic(int a, int b, int c) {
        TreeMap<Integer, Integer> quad = new TreeMap<Integer, Integer>(Collections.reverseOrder());

        quad.put(2, a);
        quad.put(1, b);
        quad.put(0, c);

        quadratic = new Polynomial(quad);
    }

    public double getRoot1() {
        return root1;
    }

    public double getRoot2() {
        return root2;
    }

    public boolean roots() {
        int a = quadratic.polynomial.get(2);
        int b = quadratic.polynomial.get(1);
        int c = quadratic.polynomial.get(0);
        double discriminant = Math.pow(b, 2) - (double) (4 * a * c);

        if (discriminant < 0.0) {
            return false;
        }

        if (discriminant == 0.0) {
            root1 = (-b + Math.sqrt(discriminant)) / (double) (2 * a);
            root2 = root1;
            return true;
        }

        root1 = (-(double) b + Math.sqrt(discriminant)) / (double) (2 * a);
        root2 = (-(double) b - Math.sqrt(discriminant)) / (double) (2 * a);

        return true;

    }

    public String toString() {
        return quadratic.toString();
    }
}
