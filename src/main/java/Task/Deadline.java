package Task;
import Shaun.exception.ShaunException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline that must be completed by a specific date/time.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Creates a deadline task with its description from the user.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     * @throws ShaunException Indicates that the date format is wrong.
     */
    public Deadline(String description, String by) throws ShaunException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new ShaunException(
                    "Deadline Format: deadline <task> /by yyyy-MM-dd (e.g. deadline return book /by 2026-03-01)"
            );
        }
    }

    /**
     * Returns a string representation of the deadline task.
     * Format consists of the deadline task's status followed by its description.
     *
     * @return String representation of the object with the deadline task status and description.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * Converts the specific deadline task into a file-friendly format for storage.
     * Format: "D | <status> | <description" , where status
     * is represented as "1" if task is done, or "0" otherwise.
     *
     * @return a string representation of the deadline task suitable for saving to a storage file.
     */
    @Override
    public String toFileFormat() {
        String status = isDone ? "1" : "0";
        return "D | " + status + " | " + description + " | " + by;
    }
}
