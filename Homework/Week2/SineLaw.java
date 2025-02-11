package Homework.Week2;

import java.util.Scanner;

public class SineLaw {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEnter the angle A: ");
        double angleA = scanner.nextDouble();

        System.out.println("Enter the length of side a: ");
        double sideA = scanner.nextDouble();

        System.out.println("Enter the length of side b: ");
        double sideB = scanner.nextDouble();
        scanner.close();

        double angleB = Math.asin((Math.sin(Math.toRadians(angleA)) * sideB) / sideA);
        double height = sideA * Math.sin(sideA);
        System.out.println(angleB);
        if (sideB < sideA) {
            System.out.println("No solution.");
        } else if (sideB == height) {
            System.out.println("Right side triangle.");
        } else if (sideB > height && sideA < sideA) {
            System.out.println();
        } else {
            System.out.println();
        }

    }
}
