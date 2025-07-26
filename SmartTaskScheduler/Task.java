package SmartTaskScheduler;

import java.time.LocalDate;

public class Task implements Comparable<Task> {
    private String title;
    private String priority;
    private LocalDate deadline;

    public Task(String title, String priority, LocalDate deadline) {
        this.title = title;
        this.priority = priority;
        this.deadline = deadline;
    }

    public String getTitle() {
        return title;
    }

    public String getPriority() {
        return priority;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    @Override
    public int compareTo(Task other) {
        int thisPriority = getPriorityValue(this.priority);
        int otherPriority = getPriorityValue(other.priority);

        if (thisPriority != otherPriority) {
            return Integer.compare(thisPriority, otherPriority);
        } else {
            return this.deadline.compareTo(other.deadline);
        }
    }

    private int getPriorityValue(String priority) {
        return switch (priority.toUpperCase()) {
            case "HIGH" -> 1;
            case "MEDIUM" -> 2;
            case "LOW" -> 3;
            default -> 4;
        };
    }

    public String toString() {
        return title + " | " + priority + " | " + deadline;
    }
}
