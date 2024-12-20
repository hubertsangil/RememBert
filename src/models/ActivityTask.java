package models;
import java.time.LocalDate;

public class ActivityTask extends Task {
    public ActivityTask(String title, LocalDate dueDate, int userId) {
        super(title, dueDate, userId);
    }

    @Override
    public void displayInfo() {
        System.out.println("Activity Task: " + getTitle() + ", Due in: " + calculateDaysLeft() + " days");
        System.out.println("Status: " + (isCompleted() ? "Completed" : "Pending"));
    }
}
