package Task;

/**
 * Represents a task of type 'to-do' without any date or time
 */
public class Todo extends Task {

    /**
     * Creates a to-do task with the input description.
     *
     * @param description Description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the to-do task
     * Format consists of the to-do task's status followed by its description
     *
     * @return String representation of the object with the to-do task status and description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
