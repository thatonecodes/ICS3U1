package HeronFormulaAssignment;

import java.util.Scanner;

public class HeronsFormula {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEnter the length of side A: ");
        double a = scanner.nextDouble();

        System.out.println("Enter the length of side B: ");
        double sideB = scanner.nextDouble();

        System.out.println("Enter the length of side C: ");
        double sideC = scanner.nextDouble();
        scanner.close();

        double area = Math
                .sqrt(4 * a * a * sideB * sideB - Math.pow(a * a + sideB * sideB - sideC * sideC, 2))
                / 4;

        System.out.printf("The area of the triangle is: %.4f", area);
    }
}
