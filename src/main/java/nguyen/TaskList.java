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
     * @param task The task to add.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Retrieves a task from the list based on its index.
     *
     * @param number The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int number) {
        return taskList.get(number);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return taskList.size();
    }
    /**
     * Returns the list of matched task
     *
     * @param keyword The keyword need to search
     */
    public void find(String keyword) {
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
}
