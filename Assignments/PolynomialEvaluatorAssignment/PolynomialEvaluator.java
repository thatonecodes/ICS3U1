import java.util.Scanner;
import java.util.StringTokenizer;

public class PolynomialEvaluator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nPlease input a polynomial in the form of ");
        String polynomial = scanner.nextLine();

        System.out.println("Enter an x value: ");
        double userInput = scanner.nextDouble();
        scanner.close();

        StringTokenizer tokenizer = new StringTokenizer(polynomial);
        int sum = 0;
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            char firstChar = token.charAt(0);
            char xToken = 'x';
            if (firstChar == 'f') { //it must be the first token
                String mainString = token.substring(4);    
                sum += Character.getNumericValue(mainString.charAt(0)) * Math.pow(userInput, Character.getNumericValue(mainString.charAt(3)));
            }else if (!token.contains("x")) {
                Double.parseDouble(token.charAt(1));
            }
            else if (firstChar == '+'){
                Double.parseDouble(token.substring(1, token.indexOf(xToken)));
            }else if (firstChar == '-'){

            }else {
                System.err.println("The string is NOT f(x)=...");
            }
        }
    }
}
