package Builder;

public class BuilderClass {

    public static void main(String[] args) {
        Car car = new Car.Builder().setEngine("V8").setWheels(4).build();
        System.out.println(car);
    }
}
