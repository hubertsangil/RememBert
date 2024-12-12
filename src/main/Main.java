package main;

import database.RememBert_DB;
import java.util.Scanner;
import managers.TaskManager;
import menus.LoginMenu;
import menus.MainMenu;
import utils.ConsoleUtils;

public class Main {
    public static void main(String[] args) {
        RememBert_DB.initializeDatabase();
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        LoginMenu loginMenu = new LoginMenu(taskManager);
        MainMenu mainMenu = new MainMenu(taskManager);

        while (true) {
            boolean isAuthenticated = loginMenu.display(scanner);
            if (!isAuthenticated) break;

            mainMenu.display(scanner);
        }

        ConsoleUtils.clearConsole();
        System.out.println("Exiting program . . .");
    }
}
