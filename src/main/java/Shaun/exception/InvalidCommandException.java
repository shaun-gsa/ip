package Shaun.exception;

/**
 * Represents an exception thrown when the user inputs
 * an invalid or unknown command.
 */
public class InvalidCommandException extends ShaunException {
    public InvalidCommandException(String message) {
        super(message);
    }
}
