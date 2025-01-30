import java.util.Scanner;
public class Nguyen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] listTask = new Task[101];
        int size = 0;
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Nguyen");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        while (true) {
            try {
                String item = scanner.nextLine();
                if (item.equals("bye")) {
                    break;
                }
                System.out.println("____________________________________________________________");
                if (item.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i <= size; i++) {
                        System.out.println(i + "." + listTask[i]);
                    }
                } else if (item.startsWith("mark")) {
                    int number = Integer.parseInt(item.substring(5));
                    if (number > size || number <= 0) {
                        throw new NguyenException("Out of range in list of task");
                    }
                    listTask[number].mark();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(listTask[number]);
                } else if (item.startsWith("unmark")) {
                    int number = Integer.parseInt(item.substring(7));
                    if (number > size || number <= 0) {
                        throw new NguyenException("Out of range in list of task");
                    }
                    listTask[number].unMark();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(listTask[number]);
                } else {
                    if (item.startsWith("todo")) {
                        if (item.length() == 4) {
                            throw new NguyenException("Empty task is valid task");
                        }
                        String task = item.substring(4);
                        size++;
                        listTask[size] = new Todo(task);
                    } else if (item.startsWith("deadline")) {
                        int indexBy = item.indexOf("/by");
                        if (item.length() == 8) {
                            throw new NguyenException("Empty task is valid task");
                        }
                        if (indexBy == -1) {
                            throw new NguyenException("Dont have deadline");
                        }
                        String task = item.substring(8, indexBy);
                        String by = item.substring(indexBy + 3);
                        size++;
                        listTask[size] = new Deadline(task, by);
                    } else if (item.startsWith("event")) {
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
                        String from = item.substring(indexFrom + 5, indexTo);
                        String to = item.substring(indexTo + 3);
                        size++;
                        listTask[size] = new Event(task, from, to);
                    } else {
                        throw new NguyenException("Invalid operation");
                    }
                    System.out.println("Got it. I've added this task:");
                    System.out.println(listTask[size]);
                    System.out.println("Now you have " + size + " tasks in the list.");
                }
            } catch(NguyenException e) {
                System.out.println("Have NguyenException: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Have Exception: " + e.getMessage());
            } finally{
                System.out.println("____________________________________________________________");
            }
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
