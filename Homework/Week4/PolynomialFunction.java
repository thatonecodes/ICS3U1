package Homework.Week4;

import java.util.Scanner;

public class PolynomialFunction {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nInput the coeffecients of the polynomial seperated by a space: ");
        String coeffecientInput = scanner.nextLine();
        String[] coeffes = coeffecientInput.split(" ");

        System.out.println("Input the degrees of the polynomial seperated by a space: ");
        String degreesInput = scanner.nextLine();
        String[] degreesArr = degreesInput.split(" ");

        System.out.println("Input the x-value of the polynomial: ");
        double x = scanner.nextDouble();
        scanner.close();

        double sum = 0;
        String poly = "";
        for (int i = 0; i < degreesArr.length; i++) {
            sum += Double.parseDouble(coeffes[i]) * Math.pow(x, Double.parseDouble(degreesArr[i]));
            //TODO make a ternary operator for coeffes to check if neg or pos.
            poly += coeffes[i] + "x^" + degreesArr[i] + " +";
        }
        System.out.println("The polynomial is: " + poly);
        System.out.println("Your answer is " + sum);
    }
}
