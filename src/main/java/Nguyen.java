import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

enum TaskType {
    TODO, DEADLINE, EVENT;
}
/**
 * The main class for the chatbot "Nguyen".
 * This chatbot allows users to manage tasks, including adding, deleting, marking, and listing them.
 */
public class Nguyen {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Represents different types of tasks a user can create.
     */
    public Nguyen(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (NguyenException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
                if (isExit) {
                    ui.showBye();
                }
            } catch (NguyenException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    /**
     * The main method that runs the chatbot.
     * It continuously takes user input and processes different commands.
     *
     * @param args Command-line arguments (not used in this application).
     */
//    public static void main(String[] args) {
//        ui = new Ui();
//
//        ArrayList<Task> listTask = new ArrayList<Task>();
//
//        System.out.println("____________________________________________________________");
//        System.out.println("Hello! I'm Nguyen");
//        System.out.println("What can I do for you?");
//        System.out.println("____________________________________________________________");
//
//        while (true) {
//            try {
//                String item = scanner.nextLine();
//
//                // Exit if the user inputs "bye"
//                if (item.equals("bye")) {
//                    break;
//                }
//
//                System.out.println("____________________________________________________________");
//
//                // Displays all tasks in the list
//                if (item.equals("list")) {
//                    System.out.println("Here are the tasks in your list:");
//                    for (int i = 1; i <= listTask.size(); i++) {
//                        System.out.println(i + "." + listTask.get(i - 1));
//                    }
//                }
//                // Deletes a task based on its index
//                else if (item.startsWith("delete")) {
//                    int number = Integer.parseInt(item.substring(7));
//                    if (number > listTask.size() || number <= 0) {
//                        throw new NguyenException("Out of range in list of task");
//                    }
//                    System.out.println("Got it. I've removed this task:");
//                    System.out.println(listTask.get(number - 1));
//                    listTask.remove(number - 1);
//                    System.out.println("Now you have " + listTask.size() + " tasks in the list.");
//                }
//                // Marks a task as completed
//                else if (item.startsWith("mark")) {
//                    int number = Integer.parseInt(item.substring(5));
//                    if (number > listTask.size() || number <= 0) {
//                        throw new NguyenException("Out of range in list of task");
//                    }
//                    listTask.get(number - 1).mark();
//                    System.out.println("Nice! I've marked this task as done:");
//                    System.out.println(listTask.get(number - 1));
//                }
//                // Unmarks a task (marks as not done)
//                else if (item.startsWith("unmark")) {
//                    int number = Integer.parseInt(item.substring(7));
//                    if (number > listTask.size() || number <= 0) {
//                        throw new NguyenException("Out of range in list of task");
//                    }
//                    listTask.get(number - 1).unMark();
//                    System.out.println("OK, I've marked this task as not done yet:");
//                    System.out.println(listTask.get(number - 1));
//                }
//                // Adds a new task
//                else {
//                    TaskType type;
//
//                    // Determines the task type
//                    if (item.startsWith("todo")) {
//                        type = TaskType.TODO;
//                    } else if (item.startsWith("deadline")) {
//                        type = TaskType.DEADLINE;
//                    } else if (item.startsWith("event")) {
//                        type = TaskType.EVENT;
//                    } else {
//                        throw new NguyenException("Invalid operation");
//                    }
//
//                    // Handles different types of tasks
//                    if (type == TaskType.TODO) {
//                        if (item.length() == 4) {
//                            throw new NguyenException("Empty task is valid task");
//                        }
//                        String task = item.substring(4);
//                        listTask.add(new Todo(task));
//                    }
//                    else if (type == TaskType.DEADLINE) {
//                        int indexBy = item.indexOf("/by");
//                        if (item.length() == 8) {
//                            throw new NguyenException("Empty task is valid task");
//                        }
//                        if (indexBy == -1) {
//                            throw new NguyenException("Dont have deadline");
//                        }
//                        String task = item.substring(8, indexBy);
//                        String by = item.substring(indexBy + 4);
//                        listTask.add(new Deadline(task, by));
//                    }
//                    else if (type == TaskType.EVENT) {
//                        int indexFrom = item.indexOf("/from");
//                        int indexTo = item.indexOf("/to");
//                        if (item.length() == 5) {
//                            throw new NguyenException("Empty task is valid task");
//                        }
//                        if (indexFrom == -1) {
//                            throw new NguyenException("Dont have starting time");
//                        }
//                        if (indexTo == -1) {
//                            throw new NguyenException("Dont have ending time");
//                        }
//                        if (indexFrom > indexTo) {
//                            throw new NguyenException("Wrong syntax order: /from should stand before /to");
//                        }
//                        String task = item.substring(5, indexFrom);
//                        String from = item.substring(indexFrom + 6, indexTo);
//                        String to = item.substring(indexTo + 4);
//                        listTask.add(new Event(task, from, to));
//                    } else {
//                        throw new NguyenException("Invalid operation");
//                    }
//
//                    System.out.println("Got it. I've added this task:");
//                    System.out.println(listTask.get(listTask.size() - 1));
//                    System.out.println("Now you have " + listTask.size() + " tasks in the list.");
//                }
//                saveTask(listTask);
//            }
//            // Handles custom exceptions
//            catch (NguyenException e) {
//                System.out.println("Have NguyenException: " + e.getMessage());
//            }
//            // Handles all other exceptions
//            catch (Exception e) {
//                System.out.println("Have Exception: " + e.getMessage());
//            }
//            // Prints separator line after every command
//            finally {
//                System.out.println("____________________________________________________________");
//            }
//        }
//
//        System.out.println("____________________________________________________________");
//        System.out.println("Bye. Hope to see you again soon!");
//        System.out.println("________________________  ____________________________________");
//    }

//    private static void saveTask(ArrayList<Task> listTask) {
//        String filePath = "./data/Nguyen.txt";
//        try {
//            // clear the existed file
//            new FileWriter(filePath, false).close();
//            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
//                for (Task task : listTask) {
//                    writer.write(task.toString());
//                    writer.newLine();
//                }
//                // System.out.println("Tasks saved to " + filePath);
//            }
//        } catch (IOException e) {
//            System.out.println("Error saving tasks: " + e.getMessage());
//        }
//    }
}
