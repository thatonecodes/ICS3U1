package Week3;

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

        Boolean gate1 = aValue && bValue;
        Boolean gate2 = cValue && dValue;
        Boolean gate3 = aValue && cValue;
        Boolean gate4 = aValue && dValue;
        Boolean gate5 = dValue && bValue;
        Boolean gate6 = bValue && cValue;

        Boolean gate1Or = gate1 || gate2;
        Boolean gate2Or = gate3 || gate4;
        Boolean gate3Or = gate5 || gate6;
        Boolean output = (gate1Or || gate2Or) || (gate2Or || gate3Or);

        System.out.printf("Your output is: %b", output);
    }
}
