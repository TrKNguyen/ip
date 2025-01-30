import java.util.Scanner;
public class TristanNguyen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] listTask = new Task[101];
        int size = 0;
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm TristanNguyen");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        while (true) {
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
                listTask[number].mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(listTask[number]);
            } else if (item.startsWith("unmark")) {
                int number = Integer.parseInt(item.substring(7));
                listTask[number].unMark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(listTask[number]);
            }
            else {
                size++;
                if (item.startsWith("todo")) {
                    String task = item.substring(4);
                    listTask[size] = new Todo(task);
                } else if (item.startsWith("deadline")){
                    int indexBy = item.indexOf("/by");
                    String task = item.substring(8, indexBy);
                    String by = item.substring(indexBy + 3);
                    listTask[size] = new Deadline(task, by);
                } else if (item.startsWith("event")) {
                    int indexFrom = item.indexOf("/from");
                    int indexTo = item.indexOf("/to");
                    String task = item.substring(5, indexFrom);
                    String from = item.substring(indexFrom + 5, indexTo);
                    String to = item.substring(indexTo + 3);
                    listTask[size] = new Event(task, from, to);
                }
                System.out.println("Got it. I've added this task:");
                System.out.println(listTask[size]);
                System.out.println("Now you have " + size + " tasks in the list.");
            }
            System.out.println("____________________________________________________________");
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
