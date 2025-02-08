package Singleton;

public class DatabaseConnection {

    private static DatabaseConnection instance; // Single instance only allowed

    private DatabaseConnection() {
        System.out.println("Database Connection Established!");
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void query(String sql) {
        System.out.println("Executing query: " + sql);
    }
}
