package Week8;

public class Recursion {
    public static void main(String[] args) {
        System.out.println("Running fibonacci sequence:");
        for (int i = 0; i < 10; i++) {
            System.out.println("fib(" + i + ") = " + fib(i));
        }
        System.out.println("\nRunning factorial:");
        for (int i = 0; i < 10; i++) {
            System.out.println("factorial(" + i + ") = " + factorial(i));
        }
    }

    public static int fib(int n){
        if (n <= 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public static int factorial (int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}