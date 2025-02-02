package nguyen;

/**
 * Represents a task with a description and a completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     * By default, the task is not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon for the task.
     * "X" represents a completed task, and " " represents an incomplete task.
     *
     * @return The status icon ("X" for done, " " for not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Unmarks the task (marks as not completed).
     */
    public void unMark() {
        isDone = false;
    }

    public boolean isMarked() {
        return isDone;
    }
    /**
     * Returns a string representation of the task, including its status.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + description;
    }
}
