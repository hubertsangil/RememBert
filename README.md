![remembert_banner](https://github.com/hubertsangil/RememBert/blob/main/images/remembert_banner.png)

# RememBert: Task Tracker 📋  

Your personal buddy for keeping track of tasks. Never forget, just **RememBert**.

---
## Patch Notes
- Revised README.md
- `Quiz`, `Activity` initial task types, will add more later.

---
## Table of Contents
- [Patch Notes](#patch-notes)
- [Overview](#overview)
- [SDG Alignment](#sdg-alignment)  
- [OOP Principles Applied](#oop-principles-applied)  
  - [Abstraction](#abstraction)  
  - [Encapsulation](#encapsulation)  
  - [Inheritance](#inheritance)  
  - [Polymorphism](#polymorphism)  
- [Installation and Running Instructions](#installation-and-running-instructions)
- [File Structure](#file-structure)
- [Features](#features)
- [Classes](#classes)



---

## Overview  

**RememBert** allows users to:  
- Log tasks such as quizzes, activities, or exams.  
- Set deadlines and categorize tasks by type.  
- Monitor completion status and view task statistics.  

By integrating Object-Oriented Programming principles, the program maintains modularity and readability, ensuring scalability and ease of maintenance.

---

## SDG Alignment  

### SDG #4: Quality Education  
This project addresses **SDG #4** by encouraging students to stay organized, manage deadlines, and prioritize tasks effectively, leading to better educational outcomes.  

**Key Contributions:**  
- Improves time management skills.  
- Fosters productivity and reduces academic stress.  
- Provides an easy-to-use digital platform for task tracking.  



---

## OOP Principles Applied  

### Abstraction  
- The **Task** class serves as a base class that hides implementation details of specific task types (e.g., `QuizTask`, `ActivityTask`). This ensures that users interact with tasks in a simplified manner without worrying about internal workings.  

### Encapsulation  
- Attributes such as `title`, `dueDate`, `userId`, and `completed` are private in the `Task` class. Getter and setter methods control access to these properties, enforcing data integrity and security.  

### Inheritance  
- Specialized classes (`QuizTask`, `ActivityTask`, etc.) inherit from the `Task` class, reusing its properties and methods while extending functionality to suit specific task types.  

### Polymorphism  
- The `displayInfo()` method is overridden in each subclass, providing tailored behavior depending on the type of task. For example:
  - A `QuizTask` might display details relevant to quizzes, while an `ActivityTask` shows details related to activities.  

---
## Program Details

### Installation and Running Instructions  

### Prerequisites  
- **Java Development Kit (JDK)** installed on your machine.  

### Steps  
1. Clone the repository:  
   ```bash
   git clone https://github.com/hubertsangil/RememBert.git
   
2. Navigate to the project directory:
    ```bash
    cd RememBert

3. Compile the Java files:
    ```bash
    javac -d bin src/**/*.java

4. Run the program:
    ```bash
    java -cp bin Main

---

### File Structure

        task-tracker/
    ├── src/
    │ ├── database/
    │ │ ├── RememBert_DB.java # Contains database information and functions
    │ ├── main/
    │ │ ├── Main.java # Main program file
    │ ├── managers/
    │ │ ├── TaskManager.java # Handles task operations
    │ ├── models/
    │ ├── Task.java # Abstract class for tasks
    │ ├── QuizTask.java # Represents a quiz task
    │ ├── ActivityTask.java # Represents an activity task
    ├── bin/ # Compiled .class files (auto-generated)
    └── README.md # Documentation

---

### Features  
- Add and display tasks.
- Categorize tasks as **Quiz** or **Activity**. 
- Track deadlines and view the number of days remaining.  
- Mark tasks as completed.  
- View task statistics (total, completed, pending).  

---

### Classes

| Class Name       | Description                                                                 |
|-------------------|-----------------------------------------------------------------------------|
| `Main`           | Contains the main method and handles user input, menu navigation, and program logic. |
| `TaskManager`    | Manages the collection of tasks, including adding, displaying, and tracking task statuses. |
| `Task`           | Abstract base class representing a generic task with attributes like title, due date, and completion status. |
| `QuizTask`       | Subclass of `Task` for tasks categorized as quizzes. Adds specific behavior for quizzes. |
| `ActivityTask`   | Subclass of `Task` for tasks categorized as activities. Adds specific behavior for activities. |
| `RememBert_DB`   | Contains MySQL query syntax needed for database initialization. |










