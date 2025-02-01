package nguyen;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
    }
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Nguyen");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public String readCommand() {
        String command = scanner.nextLine();
        return command;
    }

    public void showError(String errorMessage) {
        System.err.println("Error: " + errorMessage);
    }
    public void showLoadingError() {
        System.err.println("Loading Error");
    }
    public void showLine() {
        System.out.println("____________________________________________________________");
    }
}
