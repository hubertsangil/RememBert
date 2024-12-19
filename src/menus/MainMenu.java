package menus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import managers.TaskManager;
import models.ActivityTask;
import models.QuizTask;
import utils.ConsoleUtils;

public class MainMenu {
    private final TaskManager taskManager;

    public MainMenu(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public void display(Scanner scanner) {
        boolean authenticated = true;
        while (authenticated) {
            ConsoleUtils.clearConsole();
            System.out.println("\r\n" + //
                                "██████  ███████ ███    ███ ███████ ███    ███ ██████  ███████ ██████  ████████ \r\n" + //
                                "██   ██ ██      ████  ████ ██      ████  ████ ██   ██ ██      ██   ██    ██    \r\n" + //
                                "██████  █████   ██ ████ ██ █████   ██ ████ ██ ██████  █████   ██████     ██    \r\n" + //
                                "██   ██ ██      ██  ██  ██ ██      ██  ██  ██ ██   ██ ██      ██   ██    ██    \r\n" + //
                                "██   ██ ███████ ██      ██ ███████ ██      ██ ██████  ███████ ██   ██    ██    \r\n" + //
                                "                                                                               \r\n" + //

                                "");
            String username = taskManager.getUsernameById(taskManager.getCurrentUserId());
            String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
            System.out.println("Remembert is a simple program to help with task tracking,\nallowing for easy listing and monitoring of tasks.\nA tool handy for those who frequently forgets or goes off track.");
            System.out.println("\nWelcome, " + username + "!"); 
            System.out.println("Today's Date: " + currentDate);
            System.out.println();
            System.out.println("\n1. Add a Task");
            System.out.println("2. Display All Tasks");
            System.out.println("3. Show Task Statistics");
            System.out.println("4. Mark Task as Completed");
            System.out.println("5. Edit a Task");
            System.out.println("6. Remove a Task");
            System.out.println("0. Logout");
            System.out.print("\nChoose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    ConsoleUtils.clearConsole();
                    System.out.println("\n1. Add a Quiz Task");
                    System.out.println("2. Add an Activity Task");
                    System.out.print("\nChoose an option: ");
                    int nextOption = scanner.nextInt();
                    scanner.nextLine();

                    switch (nextOption) {
                        case 1:
                            ConsoleUtils.clearConsole();
                            System.out.print("Enter Quiz Title: ");
                            String quizTitle = scanner.nextLine();
                            System.out.print("Enter Due Date (YYYY-MM-DD): ");
                            LocalDate quizDueDate = LocalDate.parse(scanner.nextLine());
                            taskManager.addTask(new QuizTask(quizTitle, quizDueDate, taskManager.getCurrentUserId()));
                            break;

                        case 2:
                            ConsoleUtils.clearConsole();
                            System.out.print("Enter Activity Title: ");
                            String activityTitle = scanner.nextLine();
                            System.out.print("Enter Due Date (YYYY-MM-DD): ");
                            LocalDate activityDueDate = LocalDate.parse(scanner.nextLine());
                            taskManager.addTask(new ActivityTask(activityTitle, activityDueDate, taskManager.getCurrentUserId()));
                            break;

                        default:
                            System.out.println("Invalid input, please try again.");
                    }
                    break;

                case 2:
                    ConsoleUtils.clearConsole();
                    taskManager.displayAllTasks(taskManager.getCurrentUserId());
                    break;

                case 3:
                    ConsoleUtils.clearConsole();
                    taskManager.showTaskStats(taskManager.getCurrentUserId());
                    break;

                case 4:
                    ConsoleUtils.clearConsole();
                    taskManager.displayAllTasks(taskManager.getCurrentUserId());
                    System.out.print("\nEnter Task ID to mark as completed: ");
                    int completedTaskId = scanner.nextInt();
                    scanner.nextLine();
                    taskManager.markTaskAsCompleted(completedTaskId, taskManager.getCurrentUserId());
                    break;
                
                case 5:
                    ConsoleUtils.clearConsole();
                    taskManager.displayAllTasks(taskManager.getCurrentUserId());
                    System.out.print("\nEnter the Task ID to edit: ");
                    int editTaskId = scanner.nextInt();
                    scanner.nextLine();
                    
                    System.out.print("Enter the new Task Title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter the new Due Date (YYYY-MM-DD): ");
                    LocalDate newDueDate = LocalDate.parse(scanner.nextLine());
                    
                    taskManager.editTask(editTaskId, taskManager.getCurrentUserId(), newTitle, newDueDate);
                    break;

                case 6:
                    ConsoleUtils.clearConsole();
                    taskManager.displayAllTasks(taskManager.getCurrentUserId());
                    System.out.print("\nEnter the Task ID to remove: ");
                    int removeTaskId = scanner.nextInt();
                    scanner.nextLine();
                    taskManager.removeTask(removeTaskId, taskManager.getCurrentUserId());
                    break;

                case 0:
                    ConsoleUtils.clearConsole();
                    System.out.println("Logging out...");
                    authenticated = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }

            if (authenticated) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
    }
}