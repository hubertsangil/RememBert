package utils;

public class ConsoleUtils {
        public static void clearConsole() {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
