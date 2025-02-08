import java.util.Scanner;

public class DecimalInput {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a decimal number: ");

        int integerToDouble = (int) input.nextDouble();
        input.close();
        System.out.println(
            "The decimal number you entered as an int is: " + integerToDouble
        );
    }
}
