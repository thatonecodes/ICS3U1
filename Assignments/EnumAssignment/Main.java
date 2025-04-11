package EnumAssignment;

public class Main {

    public static void main(String[] args) {
        System.out.println("\n\nPrinting out all the http status codes:");
        for (HttpStatus value : HttpStatus.values()) {
            System.out.println(value.getCode() + " " + value.getDescription());
            System.out.println("No Error(can continue): " + value.canContinue());
        }
    }
}