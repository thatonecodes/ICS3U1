package PolynomialEvaluatorAssignment;

import java.util.Scanner;
import java.util.StringTokenizer;

public class PolynomialEvaluator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter a polynomial in the form f(x)=3x^3 -5x^2 +1x^4 +9x^6 +3.1x^1 +2: ");
        StringTokenizer tokenizer = new StringTokenizer(scanner.nextLine().replace("f(x)=", "").replaceAll("\\s+", ""),
                "+-", true);
        System.out.println("Enter an x value: ");
        double x = scanner.nextDouble();
        scanner.close();

        double result = 0.0;
        String sign = "+";
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            sign = (token.equals("+") || token.equals("-")) ? token : sign;

            int xPos = token.indexOf("x"); // indexOf returns -1 if null
            double coef = (xPos > 0) ? Double.parseDouble(sign + token.substring(0, xPos))
                    : Double.parseDouble(sign + "1");
            double exp = (xPos != -1)
                    ? (token.contains("^") ? Double.parseDouble(token.substring(token.indexOf("^") + 1)) : 1.0)
                    : 0;

            result += coef * Math.pow(x, exp);
        }

        System.out.printf("f(%.1f) = %.2f%n", x, result);
    }
}