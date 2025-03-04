import java.util.Scanner;

public class ArrayTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] array = new String[10];

        running: while (true) {
            // Display menu
            System.out.println("\nMenu:");
            System.out.println("1. Enter 1 Strings");
            System.out.println("2. Display all Strings");
            System.out.println("3. Display a specific String");
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
                    System.out.println("Enter 10 Strings:");
                    for (int i = 0; i < 10; i++) {
                        System.out.print("String " + (i + 1) + ": ");
                        array[i] = scanner.nextLine();
                    }
                    break;
                case 2:
                    System.out.println("Array contents:");
                    for (String str : array) {
                        System.out.println(str);
                    }
                    break;
                case 3:
                    System.out.print("Enter an index (1-10): ");
                    int index = scanner.nextInt();
                    if (index >= 1 && index <= 10) {
                        System.out.println("String at index " + index + ": " + array[index - 1]);
                    } else {
                        System.out.println("Invalid index! Must be between 1 and 10.");
                    }
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