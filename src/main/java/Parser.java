public class Parser {
    public static Command parse(String fullCommand) throws NguyenException {
        return new Command(fullCommand);
    }
    public static Task parseTask(String line) {
        Task task = null;
        if (line.startsWith("[T]")) {
            String description = line.substring(4);
            task = new Todo(description);
        } else if (line.startsWith("[D]")) {
            String description = line.substring(4, line.indexOf(" (by:"));
            String by = line.substring(line.indexOf("by:") + 4, line.length() - 1);
            task = new Deadline(description, by);
        } else if (line.startsWith("[E]")) {
            String description = line.substring(4, line.indexOf(" (from:"));
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
