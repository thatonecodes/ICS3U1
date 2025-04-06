package Week7;

public class Quadratic {
    private double a;
    private double b;
    private double c;

    public Quadratic(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    private static String getSign(double value) {
        return value > 0 ? " + " + Math.abs(value) : "- " + Math.abs(value);
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public void setA(double a) {
        this.a = a;
    }

    public void setB(double b) {
        this.b = b;
    }

    public void setC(double c) {
        this.c = c;
    }

    public boolean hasRealRoots() {
        return this.getDiscriminant() >= 0;
    }

    public boolean isMaximum() {
        return a > 0;
    }

    public double getDiscriminant() {
        return b * b - 4 * a * c;
    }

    public double[] getRoots() {
        double discriminant = this.getDiscriminant();
        if (discriminant < 0) {
            return null;
        } else if (discriminant == 0) {
            double root = ((-b + Math.sqrt(discriminant)) / 2) * a;
            return new double[] { root };
        } else {
            double root1 = ((-b + Math.sqrt(discriminant)) / 2) * a;
            double root2 = ((-b - Math.sqrt(discriminant)) / 2) * a;
            return new double[] { root1, root2 };
        }
    }

    public double[] getVertex() {
        double xV = -b / 2 * a;
        return new double[] { xV, a * xV * xV + b * xV + c };
    }

    public double[] getXIntercept() {
        return this.getRoots();
    }

    public double[] getYIntercept() {
        return this.getRoots();
    }

    public void getStandardForm() {
        System.out.println(getSign(a) + "x^2" + getSign(b) + "x " + getSign(c));
    }

    public void getVertexForm() {
        String h = getSign(this.getVertex()[0]);
        String k = getSign(this.getVertex()[1]);
        System.out.printf("%s(x %s)^2 + %s%n", a, h, k);
    }

    public void getFactoredForm() {
        double[] roots = this.getRoots();
        String r1 = getSign(roots[0]);
        String r2 = getSign(roots[1]);

        System.out.printf("y = %.2f(x %s)(x %s)%n", a, r1, r2);
    }
}
