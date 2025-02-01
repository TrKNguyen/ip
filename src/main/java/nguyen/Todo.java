package nguyen;

/**
 * Represents a simple to-do task without any specific date or time.
 */
public class Todo extends Task {

    /**
     * Constructs a new To-do task with the given description.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the to-do task.
     *
     * @return A formatted string representing the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
