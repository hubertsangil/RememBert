package managers;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import models.ActivityTask;
import models.QuizTask;
import models.Task;

public class TaskManager {
    private Connection connection;

    public TaskManager() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task_tracker", "root", "XCD_REMEMBERT");
        } catch (SQLException e) {
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
    
    public void markTaskAsCompleted(int taskId, int userId) {
        String checkQuery = "SELECT is_completed FROM tasks WHERE id = ? AND user_id = ?";
        String updateQuery = "UPDATE tasks SET is_completed = TRUE WHERE id = ? AND user_id = ?";
    
        try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {

            checkStmt.setInt(1, taskId);
            checkStmt.setInt(2, userId);
            ResultSet rs = checkStmt.executeQuery();
    
            if (rs.next()) {
                boolean isCompleted = rs.getBoolean("is_completed");
    
                if (isCompleted) {
                    System.out.println("Task is already marked as completed.");
                    return;
                }
    
                try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                    updateStmt.setInt(1, taskId);
                    updateStmt.setInt(2, userId);
                    int rowsUpdated = updateStmt.executeUpdate();
    
                    if (rowsUpdated > 0) {
                        System.out.println("Task marked as completed.");
                    } else {
                        System.out.println("Task not found or does not belong to you.");
                    }
                }
            } else {
                System.out.println("Task not found or does not belong to you.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while marking the task as completed.");
            e.printStackTrace();
        }
    }
    
    
    public void displayAllTasks(int userId) {
        String query = "SELECT id, title, due_date, is_completed FROM tasks WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
    
            if (!rs.isBeforeFirst()) { 
                System.out.println("No tasks found.");
                return;
            }
    
            System.out.println("+----+-----------------------------------+------------+-----------+");
            System.out.printf("| %-2s | %-33s | %-10s | %-9s |%n", "ID", "Title", "Due Date", "Status");
            System.out.println("+----+-----------------------------------+------------+-----------+");
    
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                LocalDate dueDate = rs.getDate("due_date").toLocalDate();
                boolean completed = rs.getBoolean("is_completed");
    
                System.out.printf("| %-2d | %-33s | %-10s | %-9s |%n",
                                  id, title, dueDate, completed ? "Completed" : "Pending");
            }
    
            System.out.println("+----+-----------------------------------+------------+-----------+");
        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving tasks.");
            e.printStackTrace();
        }
    }
    
    public void showTaskStats(int userId) {
        String query = "SELECT COUNT(*) AS total, SUM(is_completed) AS completed " +
                       "FROM tasks WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet resultSet = stmt.executeQuery();
    
            if (resultSet.next()) {
                int total = resultSet.getInt("total");
                int completed = resultSet.getInt("completed");
                int pending = total - completed;
    
                System.out.println("Total Tasks: " + total);
                System.out.println("Completed Tasks: " + completed);
                System.out.println("Pending Tasks: " + pending);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while fetching task statistics.");
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
