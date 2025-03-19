package AmbigousCaseAssignment;

import java.util.Scanner;

public class AmbiguousCase {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEnter the angle A (in degrees): ");
        double angle = scanner.nextDouble();

        System.out.println("Enter the length of side a: ");
        double a = scanner.nextDouble();

        System.out.println("Enter the length of side b: ");
        double b = scanner.nextDouble();
        scanner.close();

        double h = b * Math.sin(Math.toRadians(angle));

        if (angle <= 90) {
            if (a < h && a > b) {
                System.out.println("No solution (triangle cannot exist).");
            } else if (a > b) {
                System.out.println("There is one possible triangle (obtuse).");
            } else if (b == h) {
                System.out.println("Right triangle.");
            } else {
                System.out.println("There are two possible triangles (ambiguous case).");
            }
        } else {
            if (a > b) {
                System.out.println("One possible triangle.");
            } else {
                System.out.println("There are two possible triangles.");
            }
        }
    }
}
