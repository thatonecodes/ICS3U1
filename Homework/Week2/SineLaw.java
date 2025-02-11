package Homework.Week2;

import java.util.Scanner;

public class SineLaw {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEnter the angle A (in degrees): ");
        double angleA = scanner.nextDouble();

        System.out.println("Enter the length of side a: ");
        double sideA = scanner.nextDouble();

        System.out.println("Enter the length of side b: ");
        double sideB = scanner.nextDouble();
        scanner.close();

        double angleB = Math.toDegrees(
            Math.asin((Math.sin(Math.toRadians(angleA)) * sideB) / sideA)
        );

        double height = sideA * Math.sin(Math.toRadians(angleA));

        System.out.println("\nCalculated Angle B: " + angleB + " degrees");
        System.out.println("Calculated Height: " + height);

        if (sideB < height || Double.isNaN(angleB)) {
            System.out.println("No solution (triangle cannot exist).");
        } else if (sideB == height) {
            System.out.println("Right triangle.");
        } else {
            double angleBPrime = 180 - angleB;
            if (angleA + angleBPrime < 180) {
                System.out.println(
                    "Second Possible AngleB: " + angleBPrime + " degrees"
                );
                System.out.println("Two possible triangles exist.");
            } else {
                System.out.println("Only one possible triangle exists.");
            }
        }
    }
}
