package UI;

import Shaun.exception.ShaunException;
import Task.Task;
import Task.Event;
import Task.Deadline;
import Task.Todo;
import Task.TaskList;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/** Helps to load and save task data to a file. */
public class Storage {

    private final String filePath;

    /** Constructor for filePath. */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from storage file.
     *
     * @return a TaskList containing stored tasks and their data.
     */
    public TaskList loadTasks() {

        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();

            if (!file.exists()) {
                file.createNewFile();
            }

            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" \\| ");

                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task;

                switch (type) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    task = new Deadline(description, parts[3]);
                    break;
                case "E":
                    String[] timeParts = parts[3].split(" to ");
                    task = new Event(description, timeParts[0], timeParts[1]);
                    break;
                default:
                    continue;
                }

                if (isDone) {
                    task.markDone();
                }

                tasks.add(task);
            }

            fileScanner.close();
        } catch (IOException e) {
            System.out.println("Error loading file...");
        } catch (ShaunException e) {
            throw new RuntimeException(e);
        }

        return new TaskList(tasks);
    }

    /**
     * Saves the user input task to the storage file.
     *
     * @param taskList the task list to be saved.
     * */
    public void saveTasks(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(filePath);

            for (int i = 0; i < taskList.size(); i++) {
                writer.write(taskList.getTask(i).toFileFormat() + System.lineSeparator());
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving file...");
        }
    }

}
