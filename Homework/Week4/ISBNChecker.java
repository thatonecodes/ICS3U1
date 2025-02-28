import java.util.Scanner;

public class ISBNChecker{
    public static void main(String[] args) {
        System.out.println("\nEnter an ISBN number here: ");

        Scanner scanner = new Scanner(System.in);
        String isbnNum = scanner.nextLine();
        scanner.close();

        if (isbnNum.length() > 13 || !isbnNum.matches( "\\d+")){
            System.out.println("Invalid ISBN Number!");
        }else{
            int checkSum = 0;
            for (int i = 0; i < isbnNum.length(); i++) {
                if (i % 2 == 0){
                    checkSum += (int) isbnNum.charAt(i);
                }else{ 
                    checkSum += (int) isbnNum.charAt(i) * 3;
                }
            }
            if (checkSum % 10 == 0){
                System.out.println("Check sum is valid.");
                System.out.println("Valid ISBN: " + isbnNum);
            }else{
                System.out.println("Invalid ISBN Number: " + isbnNum);
            }
        }
    }
}