package Week3;

import java.util.Scanner;

public class NandGate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPlease enter the a value(boolean): ");
        Boolean aValue = scanner.nextBoolean();
        System.out.println("Please enter the b value(boolean): ");
        Boolean bValue = scanner.nextBoolean();
        scanner.close();

        boolean output = !(aValue && bValue);
        System.out.printf("NANDGate output is: %b", output);
    }
}