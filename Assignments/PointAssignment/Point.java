package PointAssignment;

public class Point {
    private double x = 0.0;
    private double y = 0.0;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void displayCoordinate() {
        System.out.printf("The x-coordinate is: %s The y-coordinate is: %s", x, y);
    }

    public void linearEquation(Point point) {
        System.out.printf("y = mx + b");
    }

    public Point difference(Point point) {
        return new Point(x - point.x, y - point.y)
    }
    
    public double distance(Point point) {
        return Math.sqrt(Math.pow(point.x - x, 2) + Math.pow(point.y - y, 2));
    }
    
    public Point midpoint(Point point) {
        return new Point((point.x + x) / 2, (point.y + y / 2));
    }
    
    public double slope(Point point) {
        return (point.y - y) / (point.x - x);
    }
    
    public Point sum(Point point) {
        return new Point(point.x + x, point.y + y);
    }
    
    public double yIntercept(Point point) {

    }
}
