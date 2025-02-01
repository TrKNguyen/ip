import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    public TaskList() {
        taskList = new ArrayList<Task>();
    }
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(i + "." + taskList.get(i - 1));
        }
    }
    public void delete(int number) throws NguyenException{
        if (number > taskList.size() || number <= 0) {
            throw new NguyenException("Out of range in list of task");
        }
        System.out.println("Got it. I've removed this task:");
        System.out.println(taskList.get(number - 1));
        taskList.remove(number - 1);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
    public void mark(int number) throws NguyenException{
        if (number > taskList.size() || number <= 0) {
            throw new NguyenException("Out of range in list of task");
        }
        taskList.get(number - 1).mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.get(number - 1));
    }
    public void unMark(int number) throws NguyenException{
        if (number > taskList.size() || number <= 0) {
            throw new NguyenException("Out of range in list of task");
        }
        taskList.get(number - 1).unMark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskList.get(number - 1));
    }
    public void add(Task task) {
        taskList.add(task);
    }
    public Task get(int number) {
        return taskList.get(number);
    }
    public int size() {
        return taskList.size();
    }

}
