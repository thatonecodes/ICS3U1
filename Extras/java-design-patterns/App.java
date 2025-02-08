import Builder.Car;
import Factory.FactoryClass;
import Observer.NotificationService;
import Observer.Observer;
import Observer.User;
import Singleton.DatabaseConnection;

public class App {

    public static void main(String[] args) {
        //creating an object of FactoryClass (based on the factory pattern)
        FactoryClass shapeFactory = new FactoryClass();
        shapeFactory.create("CIRCLE");

        //builder pattern
        Car car = new Car.Builder().setEngine("V8").setWheels(4).build();
        System.out.println(car);

        //database connection, singleton
        DatabaseConnection firstConnection = DatabaseConnection.getInstance();
        firstConnection.query("SELECT * FROM users");

        DatabaseConnection secondConnection = DatabaseConnection.getInstance();
        secondConnection.query("SELECT * FROM products");

        System.out.println(
            "\nSingleton testing: Are two object memory locations the same?"
        );
        System.out.printf(
            "FirstCon and secondCon same: %b",
            firstConnection == secondConnection
        );
        System.out.println();
        //observer pattern
        NotificationService service = new NotificationService();

        Observer user1 = new User("Alice");
        Observer user2 = new User("Bob");
        service.addObserver(user1);
        service.addObserver(user2);

        service.notifyAllObservers("New update available!");
    }
}
