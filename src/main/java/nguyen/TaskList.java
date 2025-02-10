package nguyen;

import java.util.ArrayList;

/**
 * Represents a list of tasks and provides methods to manipulate the tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList; // ArrayList to store the tasks

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param taskList The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Displays all tasks in the list with their corresponding indices.
     */
    public void printList() {
        assert taskList != null : "TaskList should not be null";
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(i + "." + taskList.get(i - 1));
        }
    }

    /**
     * Deletes a task from the list based on its index.
     *
     * @param number The index of the task to delete.
     * @throws NguyenException If the index is out of range.
     */
    public void delete(int number) throws NguyenException {
        assert taskList != null : "TaskList should not be null";
        if (number > taskList.size() || number <= 0) {
            throw new NguyenException("Out of range in list of task");
        }
        System.out.println("Got it. I've removed this task:");
        System.out.println(taskList.get(number - 1));
        taskList.remove(number - 1);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Marks a task as completed based on its index.
     *
     * @param number The index of the task to mark as done.
     * @throws NguyenException If the index is out of range.
     */
    public void mark(int number) throws NguyenException {
        assert taskList != null : "TaskList should not be null";
        if (number > taskList.size() || number <= 0) {
            throw new NguyenException("Out of range in list of task");
        }
        taskList.get(number - 1).mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.get(number - 1));
    }

    /**
     * Unmarks a task (marks it as not done) based on its index.
     *
     * @param number The index of the task to unmark.
     * @throws NguyenException If the index is out of range.
     */
    public void unMark(int number) throws NguyenException {
        assert taskList != null : "TaskList should not be null";
        if (number > taskList.size() || number <= 0) {
            throw new NguyenException("Out of range in list of task");
        }
        taskList.get(number - 1).unMark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskList.get(number - 1));
    }

    /**
     * Adds a new task to the list.
     *
     * @param tasks The task to add.
     */
    @SafeVarargs
    public final void add(Task... tasks) {
        assert taskList != null : "TaskList should not be null";
        for (Task task : tasks) {
            taskList.add(task);
        }
    }

    /**
     * Retrieves a task from the list based on its index.
     *
     * @param number The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int number) {
        assert taskList != null : "TaskList should not be null";
        return taskList.get(number);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        assert taskList != null : "TaskList should not be null";
        return taskList.size();
    }
    /**
     * Returns the list of matched task
     *
     * @param keyword The keyword need to search
     */
    public void find(String keyword) {
        assert taskList != null : "TaskList should not be null";
        System.out.println("Here are the matching tasks in your list:");
        int countId = 0;
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                countId++;
                System.out.println(countId + ". " + task);
            }
        }
    }
    /**
     * Handle every tasks
     *
     * @param item The task information
     */
    public void handleTask(String item) throws NguyenException {
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
                throw new NguyenException("Empty task is invalid task");
            }
            String task = item.substring(4);
            taskList.add(new Todo(task));
        } else if (type == TaskType.DEADLINE) {
            int indexBy = item.indexOf("/by");
            if (item.length() == 8) {
                throw new NguyenException("Empty task is invalid task");
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
                throw new NguyenException("Empty task is invalid task");
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
        }
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.get(taskList.size() - 1));
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}
