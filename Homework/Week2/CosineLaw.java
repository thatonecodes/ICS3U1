package Homework.Week2;
import java.util.Scanner;

public class CosineLaw {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nInput side a:");
        double aValue = scanner.nextDouble();

        System.out.println("\nInput side b :");
        double bValue = scanner.nextDouble();

        System.out.println("\nInput angleC: ");
        double cValue = scanner.nextDouble();
        scanner.close();

        double sideC = Math.sqrt(Math.pow(aValue, 2) + Math.pow(bValue, 2) - 2 * aValue * bValue * Math.cos(Math.toRadians(cValue)));

        System.out.printf("Your side is: %.2f", sideC);
    }
}
