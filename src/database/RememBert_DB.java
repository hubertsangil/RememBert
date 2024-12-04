package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RememBert_DB {
    static final String DB_URL = "jdbc:mysql://localhost:3306/";
    static final String DB_NAME = "task_tracker";
    static final String USER = "root";
    static final String PASS = "XCD_REMEMBERT";

    public static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
             
            String createDBQuery = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
            stmt.executeUpdate(createDBQuery);
            System.out.println("Database ensured.");

            String useDBQuery = "USE " + DB_NAME;
            stmt.executeUpdate(useDBQuery);
            System.out.println("Using database: " + DB_NAME);

            String createUsersTable = """
                CREATE TABLE IF NOT EXISTS users (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    username VARCHAR(50) NOT NULL UNIQUE,
                    password VARCHAR(255) NOT NULL
                )
            """;
            stmt.executeUpdate(createUsersTable);
            System.out.println("Users table ensured.");
            
            String createTasksTable = """
                CREATE TABLE IF NOT EXISTS tasks (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    title VARCHAR(255) NOT NULL,
                    due_date DATE NOT NULL,
                    is_completed BOOLEAN DEFAULT FALSE,
                    type VARCHAR(50) NOT NULL,
                    user_id INT,
                    FOREIGN KEY (user_id) REFERENCES users(id)
                )
            """;
            stmt.executeUpdate(createTasksTable);
            System.out.println("Tasks table ensured.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
