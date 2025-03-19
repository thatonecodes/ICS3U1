import java.util.Scanner;

public class NewtonsMethod {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter the intial root guess: ");

            double root = scanner.nextDouble();
            double prevRoot = 0.0;

            do {
                prevRoot = root;

                double rootPow2 = Math.pow(root, 2);
                double rootPow3 = rootPow2 * root;

                // Newton's method
                root -= (6 * rootPow3 * root - 13 * rootPow3 - 18 * rootPow2 + 7 * root + 6) /
                        (24 * rootPow3 - 39 * rootPow2 - 36 * root + 7);

                System.out.println("Your next root is: " + root);

            } while(Math.abs(root - prevRoot) > 0.000000000000001); 

            System.out.println("\nApproximated root is: " + root);
            System.out.println("Would you like to try again? (y/n)");

            if (scanner.next().toLowerCase().charAt(0) == 'n') {
                scanner.close();
                break;
            }
        }
    }
}