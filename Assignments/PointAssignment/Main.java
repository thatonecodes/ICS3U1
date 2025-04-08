package PointAssignment;

public class Main {
    public static void main(String[] args) {
        testConstructorAndGetters();
        testDifference();
        testDistance();
        testMidpoint();
        testSlope();
        testSum();
        testYIntercept();

        System.out.println("All tests passed!");
    }

    static void testConstructorAndGetters() {
        Point p = new Point(3, 4);
        assert p.getX() == 3 : "Expected x to be 3";
        assert p.getY() == 4 : "Expected y to be 4";
    }

    static void testDifference() {
        Point a = new Point(5, 7);
        Point b = new Point(2, 3);
        Point diff = a.difference(b);
        assert diff.getX() == 3 : "Expected difference x to be 3";
        assert diff.getY() == 4 : "Expected difference y to be 4";
    }

    static void testDistance() {
        Point a = new Point(0, 0);
        Point b = new Point(3, 4);
        double dist = a.distance(b);
        assert Math.abs(dist - 5.0) < 1e-6 : "Expected distance to be 5.0";
    }

    static void testMidpoint() {
        Point a = new Point(0, 0);
        Point b = new Point(4, 4);
        Point mid = a.midpoint(b);
        assert mid.getX() == 2.0 : "Expected midpoint x to be 2.0";
        assert mid.getY() == 2.0 : "Expected midpoint y to be 2.0";
    }

    static void testSlope() {
        Point a = new Point(1, 2);
        Point b = new Point(3, 6);
        double slope = a.slope(b);
        assert Math.abs(slope - 2.0) < 1e-6 : "Expected slope to be 2.0";
    }

    static void testSum() {
        Point a = new Point(1, 2);
        Point b = new Point(3, 4);
        Point sum = a.sum(b);
        assert sum.getX() == 4.0 : "Expected sum x to be 4.0";
        assert sum.getY() == 6.0 : "Expected sum y to be 6.0";
    }

    static void testYIntercept() {
        Point a = new Point(1, 2);
        Point b = new Point(3, 6);
        double intercept = a.yIntercept(b);
        assert Math.abs(intercept - 0.0) < 1e-6 : "Expected y-intercept to be 0.0";
    }
}