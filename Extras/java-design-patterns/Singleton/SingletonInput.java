package Singleton;

import java.util.Scanner;

public class SingletonInput {

    private static Scanner scanner = new Scanner(System.in);

    private SingletonInput() {} // prevent instantiation from other classes

    public static String input() {
        System.out.print("Enter your input: ");
        return "\033[31m" + scanner.nextLine() + "\033[0m"; //add red escape sequence
    }

    public static void closeScanner() {
        scanner.close();
    }

    public static void main(String[] args) {
        String input1 = SingletonInput.input();
        System.out.println("You have entered first input.");
        String input2 = SingletonInput.input();
        System.out.println("You have entered second input.");
        SingletonInput.closeScanner();
        System.out.printf("Your inputs were: %s and %s\n", input1, input2);
    }
}
