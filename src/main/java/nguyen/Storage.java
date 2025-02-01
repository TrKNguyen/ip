package nguyen;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public ArrayList<Task> load() throws NguyenException {
        ArrayList<Task> taskList = new ArrayList<Task>();
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            while ((line = reader.readLine()) != null) {
                Task task = Parser.parseTask(line);
                if (task != null) {
                    taskList.add(task);
                }
            }
        }catch (IOException e) {
            throw new NguyenException(e.getMessage());
        }
        return taskList;
    }
    public void saveTask(TaskList taskList) {
        try {
            // clear the existed file
            new FileWriter(filePath, false).close();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (int i = 0; i < taskList.size(); i++) {
                    writer.write(taskList.get(i).toString());
                    writer.newLine();
                }
                // System.out.println("Tasks saved to " + filePath);
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
