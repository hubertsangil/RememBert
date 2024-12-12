package menus;

import java.util.Scanner;
import managers.TaskManager;
import utils.ConsoleUtils;

public class LoginMenu {
    private final TaskManager taskManager;

    public LoginMenu(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public boolean display(Scanner scanner) {
        while (true) {
            ConsoleUtils.clearConsole();
            System.out.println("\r\n" + //
                                "██████  ███████ ███    ███ ███████ ███    ███ ██████  ███████ ██████  ████████ \r\n" + //
                                "██   ██ ██      ████  ████ ██      ████  ████ ██   ██ ██      ██   ██    ██    \r\n" + //
                                "██████  █████   ██ ████ ██ █████   ██ ████ ██ ██████  █████   ██████     ██    \r\n" + //
                                "██   ██ ██      ██  ██  ██ ██      ██  ██  ██ ██   ██ ██      ██   ██    ██    \r\n" + //
                                "██   ██ ███████ ██      ██ ███████ ██      ██ ██████  ███████ ██   ██    ██    \r\n" + //
                                "                                                                               \r\n" + //

                                "");
            System.out.println("Remembert is a simple program to help with task tracking,\nallowing for easy listing and monitoring of tasks.\nA tool handy for those who frequently forgets or goes off track.");
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.println("0. Exit");
            System.out.print("\nChoose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    ConsoleUtils.clearConsole();
                    System.out.print("Enter username: ");
                    String regUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String regPassword = scanner.nextLine();
                    taskManager.registerUser(regUsername, regPassword);
                    break;

                case 2:
                    ConsoleUtils.clearConsole();
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    if (taskManager.loginUser(loginUsername, loginPassword)) {
                        taskManager.setCurrentUserId(taskManager.getUserId(loginUsername));
                        return true;
                    } else {
                        System.out.println("Invalid username or password. Please try again.");
                    }
                    break;

                case 0:
                    ConsoleUtils.clearConsole();
                    System.out.println("Exiting...");
                    return false;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}