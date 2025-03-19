package Week3;

import java.util.Scanner;

public class NewQuadraticFormula {
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

        if (discriminant < 0) {
            System.out.println("\n\nRoot 1 and root 2 is not real.");
        } else if (discriminant == 0) {
            double root = ((-bValue + Math.sqrt(discriminant)) / 2) * aValue;
            System.out.println("\n\nThere is exactly one real root: " + root);
        } else {
            double root1 = ((-bValue + Math.sqrt(discriminant)) / 2) * aValue;
            double root2 = ((-bValue - Math.sqrt(discriminant)) / 2) * aValue;
            System.out.println("\n\nROOTS ARE: " + root1 + root2);
        }
    }
}
