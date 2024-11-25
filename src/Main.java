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
        boolean running = true;

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
                            taskManager.addTask(new QuizTask(quizTitle, quizDueDate));
                            break;
                        
                        case 2:
                            clearConsole();
                            System.out.print("Enter Activity Title: ");
                            String activityTitle = scanner.nextLine();
                            System.out.print("Enter Due Date (YYYY-MM-DD): ");
                            LocalDate activityDueDate = LocalDate.parse(scanner.nextLine());
                            taskManager.addTask(new ActivityTask(activityTitle, activityDueDate));
                            break;
                        default:
                            System.out.println("Invalid input, please try again.");
                    }
                    break;

                case 2: 
                    clearConsole();
                    taskManager.displayAllTasks();
                    break;

                case 3:
                    clearConsole();
                    taskManager.showTaskStats();
                    break;

                case 4:
                    clearConsole();
                    if (taskManager.getTasks().isEmpty()) {
                        System.out.println("No tasks available to mark as completed.");
                    } else {
                        System.out.print("Enter Task Title to mark as completed: ");
                        String title = scanner.nextLine();
                        taskManager.markTaskAsCompleted(title);
                    }
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
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine(); 
        
                clearConsole();
            }
        }
        
    }

    public static void markTaskAsCompleted(TaskManager taskManager, String title) {
        if (taskManager.getTasks().isEmpty()) {
            System.out.println("No tasks available to mark as completed.");
            return; 
        }
    
        for (Task task : taskManager.getTasks())  {
            if (task.getTitle().equalsIgnoreCase(title)) {
                task.setCompleted(true);
                System.out.println("Task marked as completed.");
                return;
            }
        }
    
        System.out.println("Task not found.");
    }
}



