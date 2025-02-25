package Homework.Week4;

import java.util.Scanner;

public class PiApproximation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nPlease enter your n value: ");
            int n = scanner.nextInt();
            double sum = 0;
            for (int i = 0; i < n; i++) {
                sum += Math.pow(-1, i) / (2 * i + 1);
            }
            sum *= 4;
            System.out.printf("Pi Approximation: %f", sum);
            
            System.out.println("\nWould you like to continue?");

            char usrInput = scanner.next().charAt(0);
            if (usrInput == 'n' || usrInput == 'N'){
                break;
            }
        }
        scanner.close();
    }
}
