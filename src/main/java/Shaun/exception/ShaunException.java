package Shaun.exception;

/**
 * General Exception thrown by the Shaun chatbot program.
 * Serves as the parent class for all custom exceptions specific to Shaun.
 */
public class ShaunException extends Exception {

    public ShaunException(String message) {
        super(message);
    }
}
