package PolynomialFunctionAssignment;

import java.util.Scanner;

public class PowerRule {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\nInput coefficients: ");
        String[] coeffs = scanner.nextLine().split(" ");
        System.out.print("\nInput degrees: ");
        String[] degrees = scanner.nextLine().split(" ");
        scanner.close();

        if (coeffs.length != degrees.length) {
            System.out.println("Error: Mismatched coefficients and degrees. (no_same_length)");
            System.exit(1);
        }

        String deriv1 = "";
        String deriv2 = "";

        for (int i = 0; i < coeffs.length; i++) {
            int degree = Integer.parseInt(degrees[i]);
            if (degree == 0)
                continue;

            double c1 = Double.parseDouble(coeffs[i]) * degree;

            // First derivative
            if (c1 != 0) { // append each coefficient with correct sign then format exponent if degree > 1  
                deriv1 += (c1 < 0 ? " - " : deriv1.isEmpty() ? "" : " + ") + Math.abs(c1)
                        + ((degree > 1) ? "x" + ((degree > 2) ? "^" + (degree - 1) : "") : "");
            }

            // Second derivative
            if (degree > 1) {
                double c2 = c1 * (degree - 1);
                if (c2 != 0) { // append each coefficient with correct sign then format exponent if degree > 2  
                    deriv2 += (c2 < 0 ? " - " : deriv2.isEmpty() ? "" : " + ") + Math.abs(c2)
                            + (degree > 2 ? "x" + (degree > 3 ? "^" + (degree - 2) : "") : "");
                }
            }
        }

        System.out.println("First derivative: " + (deriv1.isEmpty() ? "0" : deriv1));
        System.out.println("Second derivative: " + (deriv2.isEmpty() ? "0" : deriv2));
    }
}
