package com.capstone.newsapp.exceptions;

/**
 * This exception is thrown when an article already exists in the system.
 */
public class ArticalAlreadyExistsException extends Exception {
    public ArticalAlreadyExistsException(String message) {
        super(message);
    }
}
