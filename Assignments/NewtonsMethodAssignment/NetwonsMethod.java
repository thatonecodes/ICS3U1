import java.util.Scanner;

public class NetwonsMethod {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\nEnter the intial root guess: ");
            double root = scanner.nextDouble();

            while (Math.abs(root - 3) > 0.00001) {
                double fx = 6 * Math.pow(root, 4) - 13 * Math.pow(root, 3) - 18 *Math.pow( root, 2) + 7 * root + 6 ;
                double fd = 24 * Math.pow(root, 3) - 39 *Math.pow(root, 2) - 36 * root + 7;
                root = root -  fx / fd;
            }
            System.out.println(root);
            System.out.println("Would you like to try again? ");
            char usrInput = scanner.next().charAt(0);
            if (usrInput == 'n' || usrInput == 'N'){
                scanner.close();
                break;
            }
        }

    }
}
