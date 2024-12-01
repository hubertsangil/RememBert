import java.time.LocalDate;

public abstract class Task {
    private String title;
    private LocalDate dueDate;
    private int userId;  
    private boolean completed;

    public Task(String title, LocalDate dueDate, int userId) {
        this.title = title;
        this.dueDate = dueDate;
        this.userId = userId;  
        this.completed = false;  
    }

    // Getter methods
    public String getTitle() {
        return title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public int getUserId() {
        return userId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public long calculateDaysLeft() {
        return java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), dueDate);
    }
    
    public abstract void displayInfo();
}
