import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private Connection connection;

    public TaskManager() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task_tracker", "root", "XCD_REMEMBERT");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTask(Task task) {
        try {
            String query = "INSERT INTO tasks (title, due_date, is_completed, type) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setDate(2, Date.valueOf(task.getDueDate()));
            preparedStatement.setBoolean(3, task.isCompleted());
            preparedStatement.setString(4, task.getClass().getSimpleName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        try {
            String query = "SELECT * FROM tasks";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                LocalDate dueDate = resultSet.getDate("due_date").toLocalDate();
                boolean isCompleted = resultSet.getBoolean("is_completed");
                String type = resultSet.getString("type");

                Task task;
                if (type.equals("QuizTask")) {
                    task = new QuizTask(title, dueDate);
                } else {
                    task = new ActivityTask(title, dueDate);
                }
                task.setCompleted(isCompleted);
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public void markTaskAsCompleted(String title) {
        try {
            String query = "UPDATE tasks SET is_completed = true WHERE title = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("Task not found.");
            } else {
                System.out.println("Task marked as completed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayAllTasks() {
        List<Task> tasks = getTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (Task task : tasks) {
                task.displayInfo();
                System.out.println("----------------------");
            }
        }
    }

    public void showTaskStats() {
        try {
            String query = "SELECT COUNT(*) AS total, SUM(is_completed) AS completed FROM tasks";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                int total = resultSet.getInt("total");
                int completed = resultSet.getInt("completed");
                int pending = total - completed;

                System.out.println("Total Tasks: " + total);
                System.out.println("Completed Tasks: " + completed);
                System.out.println("Pending Tasks: " + pending);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}