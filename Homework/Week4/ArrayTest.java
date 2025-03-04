import java.util.Scanner;

public class ArrayTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] array = new String[10];
        boolean running = true;

        while (running) {
            // Display menu
            System.out.println("\nMenu:");
            System.out.println("1. Enter 10 Strings");
            System.out.println("2. Display all Strings");
            System.out.println("3. Display a specific String");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

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
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
        scanner.close();
    }
}