package Assignments.BooleanTestAssignment;

import java.util.Scanner;

public class BooleanTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nPlease enter the a value(boolean): ");
        Boolean aValue = scanner.nextBoolean();

        System.out.println("Please enter the b value(boolean): ");
        Boolean bValue = scanner.nextBoolean();

        System.out.println("Please enter the c value(boolean): ");
        Boolean cValue = scanner.nextBoolean();

        System.out.println("Please enter the d value(boolean): ");
        Boolean dValue = scanner.nextBoolean();
        scanner.close();

        Boolean output = (bValue && !dValue) ||
                (!bValue && !cValue && dValue) ||
                (!aValue && !bValue && !cValue) ||
                (aValue && cValue && !dValue);
        System.out.printf("Your output is: %b", output);
    }
}
