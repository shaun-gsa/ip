package Task;

/**
 * Represents a base task in Shaun.
 * A Task has a description and a check status.
 *
 * This is the parent class for specific task types
 * {@link Todo}, {@link Deadline}, {@link Event}.
 */
public abstract class Task {

    public String description;
    protected boolean isDone;

    /**
     * Creates a Task with its description from the user.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Marks specific task as done. */
    public void markDone() {
        isDone = true;
    }

    /** Marks specific task as undone. */
    public void unmarkDone() {
        isDone = false;
    }

    /**
     * Returns the status icon of the specific task.
     *
     * @return "X" if task is completed, " " otherwise.
     */
    public String getStatus() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns a string representation of the task.
     * Format consists of the task's status followed by its description.
     *
     * @return String representation of the object with the task status and description.
     */
    @Override
    public String toString() {
        return getStatus() + " " + description;
    }

    /**
     * Converts the specific task into a file-friendly format for storage.
     * Format: "T | <status> | <description" , where status
     * is represented as "1" if task is done, or "0" otherwise.
     *
     * @return a string representation of the task suitable for saving to a storage file.
     */
    public String toFileFormat() {
        String status = isDone ? "1" : "0";
        return "T | " + status + " | " + description;
    }

    /**
     * Retrieves the description of the specific task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }
}
