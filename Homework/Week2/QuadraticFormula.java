package Homework.Week2;

import java.util.Scanner;

public class QuadraticFormula {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nInput a value:");
        double aValue = scanner.nextDouble();

        System.out.println("\nInput b value:");
        double bValue = scanner.nextDouble();

        System.out.println("\nInput c value:");
        double cValue = scanner.nextDouble();

        scanner.close();
        double discriminant = Math.pow(bValue, 2) - 4 * aValue * cValue;

        double root1 = (-bValue + Math.sqrt(discriminant)) / 2 * aValue;
        double root2 = (-bValue - Math.sqrt(discriminant)) / 2 * aValue;

        if (Double.isNaN(root1)) {
            System.out.println("\n\nRoot 1 and root 2 is not real.");
        } else {
            System.out.println("\n\nROOTS ARE: " + root1 + root2);
        }

    }
}