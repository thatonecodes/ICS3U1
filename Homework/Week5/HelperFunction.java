import java.util.Scanner;

public class HelperFunction {
    static void quadraticFormula (double a, double b, double c) {
        double discriminant = Math.pow(b, 2) - 4 * a * c;

        if (discriminant < 0) {
            System.out.println("\n\nRoot 1 and root 2 is not real.");
        } else if (discriminant == 0) {
            System.out.println("\n\nThere is exactly one real root: " + ((-b + Math.sqrt(discriminant)) / 2) * a);
        } else {
            System.out.println("\n\nROOTS ARE: " + ((-b + Math.sqrt(discriminant)) / 2) * a + " and " + ((-b - Math.sqrt(discriminant)) / 2) * a);
        }
    }

    static double piApproximation (int n) {
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.pow(-1, i) / (2 * i + 1);
        }
        sum *= 4;
        return sum;
    }

    static void asymptoteFinder (int m, int n) {
        if (m == n) {
            System.out.printf("The asymptote is horizontal: x = %d", m);
        } else if (n > m) {
            System.out.println("The asymptote is the x-axis. ");
        } else {
            String[] names = { "Linear", "Quadratic", "Cubic", "Quartic", "Quintic", "Sextic", "Septic", "Octic",
                    "Nonic", "Decic" };
            System.out.printf("The asymptote is to the degree of: %s", names[m - n - 1]);
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        running: while (true) {
            // Display menu
            System.out.println("\nMenu:");
            System.out.println("1. Quadratic Formula");
            System.out.println("2. Pi Approximation");
            System.out.println("3. Asymptote Finder");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            if (!scanner.hasNextInt()){
                System.err.println("Invalid input!");
                System.exit(1);
            }
            int choice = scanner.nextInt();
            scanner.nextLine(); // use this to consume new line
        
            switch (choice) {
                case 1:
                    System.out.println("Input a value:");
                    double a = scanner.nextDouble();
                    System.out.println("Input b value:");
                    double b = scanner.nextDouble();
                    System.out.println("Input c value:");
                    double c = scanner.nextDouble();
                    quadraticFormula(a, b, c);
                    break;
                case 2:
                    System.out.println("Input m value:");
                    System.out.println("Your Pi Approximation is: " + piApproximation(scanner.nextInt()));
                    break;
                case 3:
                    System.out.println("Input m value:");
                    int mValue = scanner.nextInt();
                    System.out.println("Input n value:");
                    int n = scanner.nextInt();
                    asymptoteFinder(mValue, n);
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    break running;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
        scanner.close();
    }
}
