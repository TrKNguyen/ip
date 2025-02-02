package nguyen;

/**
 * Parses user input into commands and tasks.
 */
public class Parser {
    /**
     * Converts a full command string into a Command object.
     *
     * @param fullCommand the full user input string
     * @return a Command object representing the parsed command
     * @throws NguyenException if the command is invalid
     */
    public static Command parse(String fullCommand) throws NguyenException {
        return new Command(fullCommand);
    }
    /**
     * Converts a stored task string into a Task object.
     *
     * @param line the task string from storage
     * @return a Task object representing the parsed task
     */
    public static Task parseTask(String line) {
        Task task = null;
        if (line.startsWith("[T]")) {
            String description = line.substring(6);
            task = new Todo(description);
        } else if (line.startsWith("[D]")) {
            String description = line.substring(6, line.indexOf(" (by:"));
            String by = line.substring(line.indexOf("by:") + 4, line.length() - 1);
            task = new Deadline(description, by);
        } else if (line.startsWith("[E]")) {
            String description = line.substring(6, line.indexOf(" (from:"));
            String from = line.substring(line.indexOf("from:") + 6, line.indexOf(" to:"));
            String to = line.substring(line.indexOf("to:") + 4, line.length() - 1);
            task = new Event(description, from, to);
        }
        if (line.substring(4,5).equals("X")) {
            task.mark();
        }
        return task;
    }
}
