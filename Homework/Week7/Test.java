package Week7;

public class Test {
    public static void main(String[] args) {
        System.out.println("Testing with default quadratic. (a=1, b=5, c=6)"); 
        Quadratic quadratic = new Quadratic(1, 5, 6);
        quadratic.getStandardForm();
        quadratic.getVertexForm();
        quadratic.getFactoredForm();
    }
}
