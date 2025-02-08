package Observer;

public class User implements Observer {

    private String name;

    // Public constructor
    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received: " + message);
    }
}
