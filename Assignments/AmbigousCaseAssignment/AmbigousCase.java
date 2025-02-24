package Assignments.AmbigousCaseAssignment;

import java.util.Scanner;

public class AmbigousCase {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEnter the angle A (in degrees): ");
        double angleA = scanner.nextDouble();

        System.out.println("Enter the length of side a: ");
        double sideA = scanner.nextDouble();

        System.out.println("Enter the length of side b: ");
        double sideB = scanner.nextDouble();
        scanner.close();


        double height = sideA * Math.sin(Math.toRadians(angleA));

        if (angleA <= 90){
            if (sideA > height && sideA > sideB) {
                System.out.println("No solution (triangle cannot exist).");
            } else if (sideA > sideB){
                System.out.println("There is one possible triangle (obtuse).");
            }else if (sideB == height) {
                System.out.println("Right triangle.");
            } else {
                System.out.println("There are two possible triangles (ambigous case).");
            }
        }else{
            if (sideA > sideB){
                System.out.println("One possible triangle.");
            }else{
                System.out.println("There are two possible triangles.");
            }
        }
    }
}
