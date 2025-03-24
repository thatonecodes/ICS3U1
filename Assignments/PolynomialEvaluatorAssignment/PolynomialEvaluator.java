package PolynomialEvaluatorAssignment;

import java.util.Scanner;
import java.util.StringTokenizer;

public class PolynomialEvaluator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEnter a polynomial in the form f(x)=3x^3 -5x^2 +1x^4 +9x^6 +3.1x^1 +2: ");
        // Using StringTokenizer to split by "+/-" while keeping delimiters
        // Docs:
        // https://docs.oracle.com/javase/8/docs/api/java/util/StringTokenizer.html#StringTokenizer-java.lang.String-java.lang.String-boolean-
        StringTokenizer tokenizer = new StringTokenizer(scanner.nextLine().replace("f(x)=", "").replaceAll("\\s+", ""),
                "+-", true);

        System.out.println("Enter an x value: ");
        double x = scanner.nextDouble();
        scanner.close();

        double result = 0.0;
        String sign = "+";

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (token.equals("+") || token.equals("-")) {
                sign = token;
            } else {
                int xIndex = token.indexOf("x"); // indexOf returns -1 if null
                double coeff = (xIndex != -1) ? (xIndex > 0 ? Double.parseDouble(token.substring(0, xIndex)) : 1.0)
                        : Double.parseDouble(token);
                int exp = (xIndex != -1)
                        ? (token.contains("^") ? Integer.parseInt(token.substring(token.indexOf("^") + 1)) : 1)
                        : 0;
                result += (sign.equals("-") ? -coeff : coeff) * Math.pow(x, exp);
            }
        }
        System.out.printf("f(%.1f) = %.2f%n", x, result); // do %n to remove % cursor
    }
}