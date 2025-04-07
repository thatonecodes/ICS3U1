package PointAssignment;

public class Main {
    public static void main(String[] args) {
        Point p1 = new Point(-1, -8); 
        Point p2 = new Point(-4, -2); 
        System.out.println();
        System.out.println(p1.distance(p2));
        Point diff = p1.difference(p2);
        System.out.printf("X: " + diff.getX() + " Y: " + diff.getY());
        p1.linearEquation(p2);
        p1.displayCoordinate();
    }    
}
