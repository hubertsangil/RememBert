import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        boolean authenticated = false;

        while (!authenticated) {
            System.out.println("Welcome to Task Tracker");
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String regUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String regPassword = scanner.nextLine();
                    taskManager.registerUser(regUsername, regPassword);
                    break;

                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();


                    if (taskManager.loginUser(loginUsername, loginPassword)) {
                        authenticated = true;
                        taskManager.setCurrentUserId(taskManager.getUserId(loginUsername));
                    }
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        // Main menu after authentication
        System.out.println("\nAccess granted. Welcome!");

        while (true) {
            clearConsole();
            System.out.println("Task Tracker");
            System.out.println("\n1. Add a Task");
            System.out.println("2. Display All Tasks");
            System.out.println("3. Show Task Statistics");
            System.out.println("4. Mark Task as Completed");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    clearConsole();
                    System.out.println("\n1. Add a Quiz Task");
                    System.out.println("2. Add an Activity Task");
                    int nextOption = scanner.nextInt();
                    scanner.nextLine();

                    switch (nextOption) {
                        case 1:
                            clearConsole();
                            System.out.print("Enter Quiz Title: ");
                            String quizTitle = scanner.nextLine();
                            System.out.print("Enter Due Date (YYYY-MM-DD): ");
                            LocalDate quizDueDate = LocalDate.parse(scanner.nextLine());
                            taskManager.addTask(new QuizTask(quizTitle, quizDueDate, taskManager.getCurrentUserId()));  // Pass currentUserId
                            break;

                        case 2:
                            clearConsole();
                            System.out.print("Enter Activity Title: ");
                            String activityTitle = scanner.nextLine();
                            System.out.print("Enter Due Date (YYYY-MM-DD): ");
                            LocalDate activityDueDate = LocalDate.parse(scanner.nextLine());
                            taskManager.addTask(new ActivityTask(activityTitle, activityDueDate, taskManager.getCurrentUserId()));  // Pass currentUserId
                            break;

                        default:
                            System.out.println("Invalid input, please try again.");
                    }
                    break;

                    case 2: 
                    clearConsole();
                    taskManager.displayAllTasks(taskManager.getCurrentUserId());
                    break;

                case 3:
                    clearConsole();
                    taskManager.showTaskStats();
                    break;

                case 4:
                    clearConsole();
                    System.out.print("Enter Task Title to mark as completed: ");
                    String title = scanner.nextLine();
                    taskManager.markTaskAsCompleted(title); 
                    break;

                case 0:
                    clearConsole();
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    clearConsole();
                    System.out.println("Invalid option. Please try again.");
                    break;
            }

            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
            clearConsole();
        }
    }
}
