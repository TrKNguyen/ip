package nguyen;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

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
        AtomicInteger countId = new AtomicInteger(0);
        taskList.stream()
                .forEach(task -> {
                    int currentId = countId.getAndIncrement();
                    System.out.println((currentId) + "." + task);
                });
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
}
