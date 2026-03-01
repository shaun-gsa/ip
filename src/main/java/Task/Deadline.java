package Task;
import Shaun.exception.ShaunException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate by;

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

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    @Override
    public String toFileFormat() {
        String status = isDone ? "1" : "0";
        return "D | " + status + " | " + description + " | " + by;
    }
}
