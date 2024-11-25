import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Task {
    private String title;
    private LocalDate dueDate;
    private boolean isCompleted;

    public Task(String title, LocalDate dueDate) {
        this.title = title;
        this.dueDate = dueDate;
        this.isCompleted = false;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public long calculateDaysLeft() {
        return LocalDate.now().until(dueDate, ChronoUnit.DAYS);
    }

    public abstract void displayInfo();
}
