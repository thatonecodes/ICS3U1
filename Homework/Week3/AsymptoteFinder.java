package Homework.Week3;

import java.util.Scanner;

public class AsymptoteFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nInput m value:");
        int mValue = scanner.nextInt();

        System.out.println("Input n value:");
        int nValue = scanner.nextInt();
        scanner.close();

        if (mValue == nValue) {
            System.out.printf("The asymptote is horizontal: x = %d", mValue);
        } else if (nValue > mValue) {
            System.out.println("The asymptote is the x-axis. ");
        } else {
            String[] names = { "Linear", "Quadratic", "Cubic", "Quartic", "Quintic", "Sextic", "Septic", "Octic",
                    "Nonic", "Decic" };
            System.out.printf("The asymptote is to the degree of: %s", names[mValue - nValue - 1]);
        }
    }
}
