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
                // System.out.println(number);
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
                listTask[size] = new Task(item);
                System.out.println("added: " + item);
            }
            System.out.println("____________________________________________________________");
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
