package Assignments.PolynomialEvaluatorAssignment;

import java.util.Scanner;
import java.util.StringTokenizer;

public class PolynomialEvaluator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a polynomial in the form f(x)=3x^3 -5x^2 +1x^4 +9x^6 +3.1x^1 +2: ");
        String polynomial = scanner.nextLine().replace("f(x)=", "").replaceAll("\\s+", ""); // Remove 'f(x)=' and spaces

        System.out.println("Enter an x value: ");

        if (!scanner.hasNextDouble()){
            System.out.println("Invalid x value!");
            System.exit(1);
        } 
        double x = scanner.nextDouble();
        scanner.close();

        //true to RETURN THE DELIMITERS FROM TOKEN
        //see: https://docs.oracle.com/javase/8/docs/api/java/util/StringTokenizer.html#StringTokenizer-java.lang.String-java.lang.String-boolean-
        StringTokenizer tokenizer = new StringTokenizer(polynomial, "+-", true); 
        double result = 0;
        String sign = "+";

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim(); //trim any whitespace
            
            if (token.equals("+") || token.equals("-")) {
                sign = token; //now loop through and see if token is ONE of the signs, set it to the global sign
                continue;
            }

            double coefficient = 1.0;
            double exponent = 0.0;

            if (token.contains("x")) {
                int xIndex = token.indexOf("x");
                if (xIndex > 0) { 
                    coefficient = Double.parseDouble(token.substring(0, xIndex));
                }

                if (token.contains("^")) {
                    exponent = Double.parseDouble(token.substring(token.indexOf("^") + 1));
                } else {
                    exponent = 1.0; 
                }
            } else {
                coefficient = Double.parseDouble(token);
                exponent = 0.0;
            }

            if (sign.equals("-")) {
                coefficient = -coefficient;
            }

            result += coefficient * Math.pow(x, exponent);
        }
        System.out.printf("f(%.1f) = %.2f%n", x, result); //do %n to remove % cursor
    }
}
