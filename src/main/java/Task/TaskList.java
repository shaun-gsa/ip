package Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks managed by Shaun.
 * Provides methods to add, remove, find and search tasks.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates an empty TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Append a task to the task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task of the specified index in the task list.
     *
     * @param index Index of task.
     * @return The task at the given index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Deletes the task of the specified index in the task list.
     *
     * @param index Index of task to be deleted.
     * @return The deleted task.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Total number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns a TaskList containing task(s) with descriptions
     * that contain the specified keyword.
     *
     * @param taskWord Keyword to search for.
     * @return A TaskList of matching task(s).
     */
    public TaskList find(String taskWord) {
        ArrayList<Task> matches = new ArrayList<>();

        for (Task task: tasks) {
            if (task.getDescription().contains(taskWord)) {
                matches.add(task);
            }
        }

        return new TaskList(matches);
    }

}
