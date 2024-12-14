![remembert_banner](https://github.com/hubertsangil/RememBert/blob/main/images/remembert_banner.png)

# RememBert: Task Tracker 📋  

Your personal buddy for keeping track of tasks. Never forget, just **RememBert**.


## Patch Notes
- Added `removeTask` method.
- Updated `displayAllTasks` method.
- Added progress bar on `showTaskStats` method.
- Revised `README`.


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

### SDG #8: Decent Work and Economic Growth 
RememBert promotes productivity by allowing individuals to keep track of their tasks contributing to a better work performance.

 





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


## Program Details

### Installation and Running Instructions  

### Prerequisites  
- **Java Development Kit (JDK)** installed on your machine.  
- **MySQL** installed in your machine. As well as the appropriate **MySQL Connector**.
- **Any IDE or Code Editor** to manipulate code.

### Steps  
1.  **Download** the folder from this repository.
2.  **Extract** the folder from the **zip file**.
3.  **Open** the folder in your **Code Editor** or **IDE**.
4.  **Check** if the **file structure** is correct.
5.  **Change** the **JDBC Credentials** to your **MySQL Credentials**.

```java 
public class RememBert_DB {
    static final String DB_URL = "jdbc:mysql://localhost:3306/";
    static final String DB_NAME = "task_tracker";
    static final String USER = "root";
    static final String PASS = "XCD_REMEMBERT"; 

```

   



### File Structure

        task-tracker/
    ├── src/
    │ ├── database/
    │ │ └── RememBert_DB.java # Contains database information and functions
    │ ├── main/
    │ │ └── Main.java # Main program file (entry-point)
    │ ├── managers/
    │ │ └── TaskManager.java # Handles task operations
    │ ├── menus/
    │ │ ├── LoginMenu.java # Login menu
    │ │ └── MainMenu.java # Main menu
    │ ├── models/
    │ │ ├── Task.java # Abstract class for tasks
    │ │ ├──  QuizTask.java # Represents a quiz task
    │ │ └──  ActivityTask.java # Represents an activity task
    │ └── utils/
    │   └── ConsoleUtils.java # Contains utility methods
    ├── bin/ # Compiled .class files (auto-generated)
    └── README.md # Documentation



### Features  
- Register and log in to user accounts.
- Add, remove, and display tasks.
- Categorize tasks as **Quiz** or **Activity**. 
- Track deadlines and view the number of days remaining.  
- Mark tasks as completed.  
- View task statistics (total, completed, pending). 




### Classes

| Class Name       | Description                                                                 |
|-------------------|-----------------------------------------------------------------------------|
|  **Main**            | Program entry point. |
| **TaskManager**    | Manages the collection of tasks, including adding, displaying, and tracking task statuses. |
| **Task**           | Abstract base class representing a generic task with attributes like title, due date, and completion status. |
| **QuizTask**      | Subclass of `Task` for tasks categorized as quizzes. Adds specific behavior for quizzes. |
| **ActivityTask**   | Subclass of `Task` for tasks categorized as activities. Adds specific behavior for activities. |
| **RememBert_DB**   | Contains MySQL query syntax needed for database initialization. |
| **ConsoleUtils** | Contains utility methods such as `clearConsole`. |
| **LoginMenu** | The login menu for the program, contains register, login, and exit options. |
| **MainMenu** | The main menu for the program, contains the options for adding, displaying, removing, marking tasks as complete, and log-out. |










