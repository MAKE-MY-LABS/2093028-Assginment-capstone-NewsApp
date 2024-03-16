package com.capstone.newsapp.exceptions;

/**
 * This exception is thrown when an email ID already exists in the system.
 */
public class EmailIdAlreadyExistsException extends Exception {
    public EmailIdAlreadyExistsException(String message) {
        super(message);
    }
}
