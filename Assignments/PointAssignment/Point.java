package PointAssignment;

public class Point {

    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        this(0.0, 0.0);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void displayCoordinate() {
        System.out.printf("%nThe x-coordinate is: %s%nThe y-coordinate is: %s", x, y);
    }

    public void linearEquation(Point point) {
        String yString = (yIntercept(point) > 0) ? "+ " + Math.abs(yIntercept(point))
                : "- " + Math.abs(yIntercept(point));
        System.out.printf("%ny = %.2fx %s", slope(point), yString);
    }

    public Point difference(Point point) {
        return new Point(x - point.getX(), y - point.getY());
    }

    public double distance(Point point) {
        return Math.sqrt(Math.pow(point.getX() - x, 2) + Math.pow(point.getY() - y, 2));
    }

    public Point midpoint(Point point) {
        return new Point((point.getX() + x) / 2, (point.getY() + y / 2));
    }

    public double slope(Point point) {
        return (point.getY() - y) / (point.getX() - x);
    }

    public Point sum(Point point) {
        return new Point(point.getX() + x, point.getY() + y);
    }

    public double yIntercept(Point point) {
        return y - x * slope(point);
    }
}