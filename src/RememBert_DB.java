import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RememBert_DB {
    static final String DB_URL = "jdbc:mysql://localhost:3306/task_tracker";
    static final String USER = "root";
    static final String PASS = "XCD_REMEMBERT";

    public static void main(String[] args) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();)
        {
            String sql = "CREATE DATABASE VRD";
            stmt.executeUpdate(sql);
            System.out.println("Database created succesfully. . .");
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
