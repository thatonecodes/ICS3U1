package Assignments.PolynomialFunctionAssignment;
import java.util.Scanner;


public class PowerRule {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\nInput the coeffecients of the polynomial seperated by a space: ");
        String[] coeffes = scanner.nextLine().split(" ");

        System.out.print("\nInput the degrees of the polynomial seperated by a space: ");
        String[] degreesArr = scanner.nextLine().split(" ");
        scanner.close();

        if (coeffes.length != degreesArr.length){
            System.out.println("The amount of coeffecicients and degrees are not the same.");
            System.exit(1);
        }

        String deriv1 = "";
        for (int i = 0; i < coeffes.length; i++) {
            double coeff = Double.parseDouble(coeffes[i]);
            deriv1 += " " + String.valueOf(coeff * Double.parseDouble(degreesArr[i])) + "x^" + String.valueOf(Double.parseDouble(degreesArr[i]) - 1);
            if (coeff < 0 ){
                deriv1 += " -";
            } else{
                deriv1 += " +";
            }
        }
        System.out.println(deriv1);
    }
}
