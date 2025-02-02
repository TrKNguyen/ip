package nguyen;
/**
 * Class to execute given command line
 *
 */
public class Command {
    private boolean isExit;
    private String item;
    /**
     * Constructor for the Command class.
     * Initializes the command with a string item.
     * The isExit flag is set to false by default.
     *
     * @param item The command input by the user.
     */
    public Command(String item) {
        this.item = item;
        isExit = false;
    }
    /**
     * Executes the command based on the user's input.
     * Different actions are performed based on the command string.
     *
     * @param taskList The list of tasks that will be modified.
     * @param ui The user interface for interacting with the user.
     * @param storage The storage for saving tasks.
     * @throws NguyenException If the command is invalid or there is an error in task handling.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws NguyenException {
        // Exit if the user inputs "bye"
        if (item.equals("bye")) {
            isExit = true;
            return;
        }
        // Displays all tasks in the list
        if (item.equals("list")) {
            taskList.printList();
        } else if (item.startsWith("delete")) {
            int number = Integer.parseInt(item.substring(7));
            taskList.delete(number);
        } else if (item.startsWith("mark")) {
            int number = Integer.parseInt(item.substring(5));
            taskList.mark(number);
        } else if (item.startsWith("unmark")) {
            int number = Integer.parseInt(item.substring(7));
            taskList.unMark(number);
        } else if (item.startsWith("find")) {
            taskList.find(item.substring(5));
        } else {
            TaskType type;
            if (item.startsWith("todo")) {
                type = TaskType.TODO;
            } else if (item.startsWith("deadline")) {
                type = TaskType.DEADLINE;
            } else if (item.startsWith("event")) {
                type = TaskType.EVENT;
            } else {
                throw new NguyenException("Invalid operation");
            }

            // Handles different types of tasks
            if (type == TaskType.TODO) {
                if (item.length() == 4) {
                    throw new NguyenException("Empty task is valid task");
                }
                String task = item.substring(4);
                taskList.add(new Todo(task));
            } else if (type == TaskType.DEADLINE) {
                int indexBy = item.indexOf("/by");
                if (item.length() == 8) {
                    throw new NguyenException("Empty task is valid task");
                }
                if (indexBy == -1) {
                    throw new NguyenException("Dont have deadline");
                }
                String task = item.substring(8, indexBy);
                String by = item.substring(indexBy + 4);
                taskList.add(new Deadline(task, by));
            } else if (type == TaskType.EVENT) {
                int indexFrom = item.indexOf("/from");
                int indexTo = item.indexOf("/to");
                if (item.length() == 5) {
                    throw new NguyenException("Empty task is valid task");
                }
                if (indexFrom == -1) {
                    throw new NguyenException("Dont have starting time");
                }
                if (indexTo == -1) {
                    throw new NguyenException("Dont have ending time");
                }
                if (indexFrom > indexTo) {
                    throw new NguyenException("Wrong syntax order: /from should stand before /to");
                }
                String task = item.substring(5, indexFrom);
                String from = item.substring(indexFrom + 6, indexTo);
                String to = item.substring(indexTo + 4);
                taskList.add(new Event(task, from, to));
            } else {
                throw new NguyenException("Invalid operation");
            }

            System.out.println("Got it. I've added this task:");
            System.out.println(taskList.get(taskList.size() - 1));
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        }
        storage.saveTask(taskList);
    }
    /**
     * Returns whether the exit command was given.
     *
     * @return true if the "bye" command was given, otherwise false.
     */
    public boolean isExit() {
        return isExit;
    }
}
