package nguyen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles loading and saving tasks to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.trim().isEmpty() : "File path must not be null or empty";
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file and returns them as an ArrayList.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws NguyenException If an error occurs while reading the file.
     */
    public ArrayList<Task> load() throws NguyenException {
        ArrayList<Task> taskList = new ArrayList<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            assert reader != null : "BufferedReader should not be null";
            while ((line = reader.readLine()) != null) {
                Task task = Parser.parseTask(line);
                assert task != null : "Parsed task should not be null";
                taskList.add(task);
            }
        } catch (IOException e) {
            throw new NguyenException(e.getMessage());
        }
        return taskList;
    }

    /**
     * Saves the tasks from the TaskList to the file.
     *
     * @param taskList The TaskList containing tasks to be saved.
     */
    public void saveTask(TaskList taskList) throws NguyenException {
        assert taskList != null : "TaskList must not be null";
        try {
            // Clear the existing file content
            new FileWriter(filePath, false).close();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                assert writer != null : "BufferedWriter should not be null";
                for (int i = 0; i < taskList.size(); i++) {
                    Task task = taskList.get(i);
                    assert task != null : "Task should not be null";
                    writer.write(task.toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new NguyenException("Error saving tasks: " + e.getMessage());
        }
    }
}
