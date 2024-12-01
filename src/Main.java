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

        while (true) { 
            boolean authenticated = false;
        
            while (!authenticated) { 
                clearConsole();
                System.out.println("Welcome to Task Tracker");
                System.out.println("\n1. Register");
                System.out.println("2. Login");
                System.out.println("0. Exit");
                System.out.println();
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
        
                switch (choice) {
                    case 1: 
                        clearConsole();
                        System.out.print("Enter username: ");
                        String regUsername = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String regPassword = scanner.nextLine();
                        taskManager.registerUser(regUsername, regPassword);
                        break;
        
                    case 2:
                        clearConsole();
                        System.out.print("Enter username: ");
                        String loginUsername = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String loginPassword = scanner.nextLine();
        
                        if (taskManager.loginUser(loginUsername, loginPassword)) {
                            authenticated = true;
                            taskManager.setCurrentUserId(taskManager.getUserId(loginUsername));
                        } else {
                            System.out.println("Invalid username or password. Please try again.");
                        }
                        break;
        
                    case 0:
                        clearConsole();
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
        
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        
            while (authenticated) {
                clearConsole();
                System.out.println("Task Tracker");
                System.out.println("\n1. Add a Task");
                System.out.println("2. Display All Tasks");
                System.out.println("3. Show Task Statistics");
                System.out.println("4. Mark Task as Completed");
                System.out.println("0. Logout");
                System.out.println();
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
                                taskManager.addTask(new QuizTask(quizTitle, quizDueDate, taskManager.getCurrentUserId()));
                                break;
        
                            case 2:
                                clearConsole();
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
                        clearConsole();
                        taskManager.displayAllTasks(taskManager.getCurrentUserId());
                        break;
        
                    case 3: 
                        clearConsole();
                        taskManager.showTaskStats();
                        break;
        
                    case 4: 
                        clearConsole();
                        System.out.print("Enter Task ID to mark as completed: ");
                        int taskId = scanner.nextInt();
                        scanner.nextLine();
                        taskManager.markTaskAsCompleted(taskId, taskManager.getCurrentUserId());
                        break;
        
                    case 0: 
                        clearConsole();
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
}
