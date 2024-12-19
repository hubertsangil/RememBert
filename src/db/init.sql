-- Initialize the database and create tables for the task tracker program

-- Create the database
CREATE DATABASE IF NOT EXISTS task_tracker;
USE task_tracker;

-- Users table
DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
);

-- Tasks table
DROP TABLE IF EXISTS tasks;
CREATE TABLE tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    due_date DATE NOT NULL,
    is_completed BOOLEAN DEFAULT FALSE,
    type VARCHAR(50) NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Initial data for users
INSERT INTO users (username, password) VALUES
('user1', 'password_for_user1'),
('user2', 'password_for__user2');

-- Initial data for tasks
INSERT INTO tasks (title, due_date, is_completed, type, user_id) VALUES
('Object-Oriented Programming Quiz', '2024-12-20', FALSE, 'QuizTask', 1),
('Science, Technology and Society Activity 1', '2024-12-25', FALSE, 'ActivityTask', 1),
('Discrete Math Exercise Set 1', '2024-12-22', TRUE, 'ActivityTask', 2);
