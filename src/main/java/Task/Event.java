package Task;

/**
 * Represents a task for an event that occurs within a specific time period.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Creates an event task with its description from the user.
     *
     * @param description Description of the task.
     * @param from Starting date/time.
     * @param to Ending date/time.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task
     * Format consists of the event task's status followed by its description
     *
     * @return String representation of the object with the event task status and description
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Converts the specific event task into a file-friendly format for storage.
     * Format: "E | <status> | <description" , where status
     * is represented as "1" if task is done, or "0" otherwise.
     *
     * @return a string representation of the event task suitable for saving to a storage file.
     */
    @Override
    public String toFileFormat() {
        String status = isDone ? "1" : "0";
        return "E | " + status + " | " + description + " | " + from + " to " + to;
    }
}
