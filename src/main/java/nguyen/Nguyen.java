package nguyen;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
    public Nguyen() {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage("data/ChatBot.txt");
    }
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
    /**
     * Runs the chatbot, continually accepting user input and executing corresponding commands.
     * Ends the program when the bye command is given.
     */
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
    public String getResponse(String userInput) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream capturingOut = new PrintStream(baos);
        System.setOut(capturingOut);
        try {
            Command c = Parser.parse(userInput);
            c.execute(tasks, ui, storage);
            if (c.isExit()) {
                ui.showBye();
            }
        } catch (NguyenException e) {
            ui.showError(e.getMessage());
        } finally {
            System.out.flush();
            System.setOut(originalOut);
        }
        return baos.toString();
    }
    /**
     * The main method that runs the chatbot.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Nguyen("data/Nguyen.txt").run();
    }
}
