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
            double coeffValue = Double.parseDouble(coeffes[i]) * Double.parseDouble(degreesArr[i]);
            int pow = Integer.parseInt(degreesArr[i]) - 1;

            if (coeffValue == 0) continue; 

            if (!deriv1.isEmpty()) {
                deriv1 += (coeffValue < 0 ? " - " : " + ");
            } else if (coeffValue < 0) {
                deriv1 += "-";
            }
            deriv1 += Math.abs(coeffValue) + "x^" + pow;
            degreesArr[i] = String.valueOf(pow);
            coeffes[i] = String.valueOf(coeffValue);
        }
        String deriv2 = "";
        for (int i = 0; i < coeffes.length; i++) {
            double coeffValue = Double.parseDouble(coeffes[i]) * Double.parseDouble(degreesArr[i]);
            int pow = Integer.parseInt(degreesArr[i]) - 1;

            if (coeffValue == 0) continue; 

            if (!deriv2.isEmpty()) {
                deriv2 += (coeffValue < 0 ? " - " : " + ");
            } else if (coeffValue < 0) {
                deriv2 += "-";
            }
            deriv2 += Math.abs(coeffValue) + "x^" + pow;
        }
        System.out.println("The derivative of the function you entered is: " + deriv1.replace("x^0", ""));
        System.out.println("The 2nd derivative of the function you entered is: " + deriv2.replace("x^0", ""));
    }
}
