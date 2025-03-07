package Assignments.BooleanTestAssignment;

import java.util.Scanner;

public class BooleanTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nPlease enter the a value(boolean): ");
        boolean a = scanner.nextBoolean();

        System.out.println("Please enter the b value(boolean): ");
        boolean b = scanner.nextBoolean();

        System.out.println("Please enter the c value(boolean): ");
        boolean c = scanner.nextBoolean();

        System.out.println("Please enter the d value(boolean): ");
        boolean d = scanner.nextBoolean();
        scanner.close();

        System.out.println("Your output is: " + (b && !d || !b && !c && d || !a && !b && !c || a && c && !d));
    }
}
