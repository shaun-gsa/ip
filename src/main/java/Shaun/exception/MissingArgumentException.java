package Shaun.exception;

public class MissingArgumentException extends ShaunException {
    public MissingArgumentException(String message) {
        super("Missing input. Please try again ");
    }
}
