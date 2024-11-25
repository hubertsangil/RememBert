import java.time.LocalDate;

public class QuizTask extends Task {
    public QuizTask(String title, LocalDate dueDate) {
        super(title, dueDate);
    }

    @Override
    public void displayInfo() {
        System.out.println("Quiz Task: " + getTitle() + ", Due in: " + calculateDaysLeft() + " days");
        System.out.println("Status: " + (isCompleted() ? "Completed" : "Pending"));
    }
}
