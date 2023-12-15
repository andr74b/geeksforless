package com.geeksforless.mathhelper.exception;

/**
 * This class represents a custom exception that should be thrown when validation of user input fails.
 */
public class CustomValidationInputException extends RuntimeException {

    /**
     * Constructor for the exception which allows a custom message to be passed.
     *
     * @param message a custom message to be included in the exception to provide more details about the error.
     */
    public CustomValidationInputException(String message) {
        super(message);
    }
}
