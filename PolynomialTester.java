import java.util.TreeMap;

/**
 * A class to to test the Polynomial class.
 */
public class PolynomialTester {
    public static void main(String[] args) {
        System.out.println("Creating P1(x) with the terms (0,-9), (2,-2), (6,8), and (4,6) ...");
        Polynomial p1 = new Polynomial(new TreeMap<Integer, Integer>() {
            {
                put(0, -9);
                put(2, -2);
                put(6, 8);
                put(4, 6);
            }
        });

        System.out.println("P1(x) = " + p1);
        System.out.println("Degree of P1(x) = " + p1.degree());
        System.out.println("Coefficient of x^2 in P1(x) = " + p1.coefficient(2));
        p1.changeCoefficient(6, 3);
        p1.removeTerm(4);
        System.out.println("P1(x) after changing coefficient of term 6 and removing term 4");
        System.out.println("P1(x) = " + p1);

        System.out.println("Creating P2(x) with the terms (-2,5), (0,4), (2,1), and (1,3) ...");
        Polynomial p2 = new Polynomial(new TreeMap<Integer, Integer>() {
            {
                put(-2, 5);
                put(0, 4);
                put(2, 1);
                put(1, 3);
            }
        });
        System.out.println("P2(x) = " + p2);

        System.out.println("Create a copy of P2(x) ...");
        Polynomial p2c = new Polynomial(p2);
        System.out.println("Copy of P2(x) = " + p2c);

        System.out.println("Adding P2(x) to P1(x)...");
        p1.add(p2);
        System.out.println("P1(x) = P1(x) + P2(x) = " + p1);

        System.out.println("Subtracting P1(x) from P2(x) and store in P2(x)...");
        p2.subtract(p1);
        System.out.println("P2(x) = P2(x) - P1(x) = " + p2);

        System.out.println("Multiplying P1(x) by P2(x) and store it into Q(x) ...");
        Polynomial q = p1.multiply(p2);
        System.out.println("Q(x) = P1(x) * P2(x) = " + q);

        System.out.println("P2(5) = " + p2.evaluate(5));

        System.out.println("P1(x) = " + p1);
        System.out.println("P2(x) = " + p2);
        System.out.println("P1(x) " + (p1.equals(p2) ? "is equal to P2(x)" : "is not equal to P2(x)"));

        System.out.println("Add P1(x) and P2(x) and store it into P3(x) ...");
        Polynomial p3 = Polynomial.add(p1, p2);
        System.out.println("P3(x) = " + p3);

        System.out.println("Subtracting P1(x) from P2(x) and store it into P4(x) ...");
        Polynomial p4 = Polynomial.subtract(p2, p1);
        System.out.println("P4(x) = " + p4);

        if (p3.compareTo(p4) > 0)
            System.out.println("P3(x) is greater than P4(x)");
        if (p3.compareTo(p4) < 0)
            System.out.println("P3(x) is less than P4(x)");

        Quadratic quad = new Quadratic(2, 5, -3);
        System.out.println("Q(x) = " + quad);
        if (quad.roots())
            System.out.printf("Roots of quadratic Q(x)=5x^2+10x+3: Root1=%10.3f , Root2=%10.3f\n", quad.getRoot1(),
                    quad.getRoot2());
        else
            System.out.println("This quadratic polynomial has no real roots. (Delta < 0)");

        quad = new Quadratic(10, 5, 3);
        System.out.println("Q(x) = " + quad);
        if (quad.roots())
            System.out.printf("Roots of quadratic Q(x)=5x^2+10x+3: Root1=%10.3f , Root2=%10.3f\n", quad.getRoot1(),
                    quad.getRoot2());
        else
            System.out.println("This quadratic polynomial has no real roots. (Delta < 0)");
    }
}