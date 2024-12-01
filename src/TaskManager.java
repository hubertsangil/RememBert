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

    private int currentUserId;

    public void setCurrentUserId(int userId) {
        this.currentUserId = userId;
    }

    public int getCurrentUserId() {
        return this.currentUserId;
    }

    public boolean registerUser(String username, String password) {
        try {
            String hashedPassword = hashPassword(password);
            String query = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, hashedPassword);
            preparedStatement.executeUpdate();
            System.out.println("Registration successful!");
            return true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println("Username already exists. Please choose another.");
            } else {
                e.printStackTrace();
            }
            return false;
        }
    }

    public boolean loginUser(String username, String password) {
        try {
            String query = "SELECT password FROM users WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                if (checkPassword(password, storedPassword)) {
                    System.out.println("Login successful!");
                    return true;
                } else {
                    System.out.println("Incorrect password.");
                }
            } else {
                System.out.println("Username not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String hashPassword(String password) {
        return Integer.toHexString(password.hashCode());
    }

    private boolean checkPassword(String password, String hashedPassword) {
        return hashPassword(password).equals(hashedPassword);
    }

    public void addTask(Task task) {
        try {
            String query = "INSERT INTO tasks (title, due_date, is_completed, user_id, type) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setDate(2, Date.valueOf(task.getDueDate()));
            preparedStatement.setBoolean(3, task.isCompleted());
            preparedStatement.setInt(4, task.getUserId());
    
            String taskType = task instanceof QuizTask ? "QuizTask" : "ActivityTask";
            preparedStatement.setString(5, taskType);
    
            preparedStatement.executeUpdate();
            System.out.println("Task added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public List<Task> getTasksByUser(int userId) {
        List<Task> tasks = new ArrayList<>();
    
        String query = "SELECT * FROM tasks WHERE user_id = ?";
    
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task_tracker", "root", "XCD_REMEMBERT");
             PreparedStatement statement = connection.prepareStatement(query)) {
    
            statement.setInt(1, userId);
    

            ResultSet resultSet = statement.executeQuery();
    

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                LocalDate dueDate = resultSet.getDate("due_date").toLocalDate();
                boolean completed = resultSet.getBoolean("is_completed");
                String type = resultSet.getString("type"); 
    
                Task task = null;
    
                if ("QuizTask".equalsIgnoreCase(type)) {
                    task = new QuizTask(title, dueDate, userId);
                } else if ("ActivityTask".equalsIgnoreCase(type)) {
                    task = new ActivityTask(title, dueDate, userId);
                }
    
                if (task != null) {
                    task.setCompleted(completed);
                    tasks.add(task);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return tasks;
    }
    
    public void markTaskAsCompleted(String title) {
        try {
            String query = "UPDATE tasks SET is_completed = true WHERE title = ? AND user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, currentUserId);
            int rowsUpdated = preparedStatement.executeUpdate();
        
            if (rowsUpdated > 0) {
                System.out.println("Task marked as completed.");
            } else {
                System.out.println("Task not found or does not belong to you.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void displayAllTasks(int userId) {
        List<Task> tasks = getTasksByUser(userId);
    
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {

            System.out.printf("%-20s %-15s %-12s %-10s%n", "Title", "Due Date", "Status", "Type");
            System.out.println("----------------------------------------------------------------");
    

            for (Task task : tasks) {
                String status = task.isCompleted() ? "Completed" : "Pending";
                System.out.printf("%-20s %-15s %-12s %-10s%n",
                        task.getTitle(), task.getDueDate(), status, task.getClass().getSimpleName());
            }
        }
    }
    
    

    public void showTaskStats() {
        try {
            String query = "SELECT COUNT(*) AS total, SUM(is_completed) AS is_completed FROM tasks";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                int total = resultSet.getInt("total");
                int completed = resultSet.getInt("is_completed");
                int pending = total - completed;

                System.out.println("Total Tasks: " + total);
                System.out.println("Completed Tasks: " + completed);
                System.out.println("Pending Tasks: " + pending);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getUserId(String username) {
        try {
            String query = "SELECT id FROM users WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
