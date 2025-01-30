import java.util.Scanner;

public class TristanNguyen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] listItem = new String[101];
        int size = 0;
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm TristanNguyen");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        while (true) {
            String item = scanner.nextLine();
            if (item.equals("bye")) {
                break;
            } else if (item.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 1; i <= size; i++) {
                    System.out.println(i + ". " + listItem[i]);
                }
                System.out.println("____________________________________________________________");
            } else {
                size++;
                listItem[size] = item;
                System.out.println("____________________________________________________________");
                System.out.println("added: " + item);
                System.out.println("____________________________________________________________");
            }
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
