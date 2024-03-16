package com.capstone.newsapp.exceptions;

/*
 * create a class for global exception handling with following methods: handleEmailIdAlreadyExistsException
 * use @ControllerAdvice annotation to specify the class as a global exception handler
 * use @ExceptionHandler annotation to handle specific exceptions
 * use ResponseEntity to return the response with status code and message
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This class is a controller advice that handles global exceptions for the NewsApp application.
 * It provides exception handling methods for specific exceptions and a generic exception handling method.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    // handle ArticalAlreadyExistsException
    /**
     * Handles the exception when an article already exists.
     *
     * @param exception The ArticalAlreadyExistsException that occurred.
     * @return A ResponseEntity with a bad request status and the exception message in the response body.
     */
    @ExceptionHandler(ArticalAlreadyExistsException.class)
    public ResponseEntity<String> handleArticalAlreadyExistsException(ArticalAlreadyExistsException exception) {
        logger.error("ArticalAlreadyExistsException occurred: {}", exception.getMessage());
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    /**
     * Handles the EmailIdAlreadyExistsException and returns a ResponseEntity with a bad request status code and the exception message in the response body.
     *
     * @param exception The EmailIdAlreadyExistsException that occurred.
     * @return A ResponseEntity with a bad request status code and the exception message in the response body.
     */
    @ExceptionHandler(EmailIdAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailIdAlreadyExistsException(EmailIdAlreadyExistsException exception) {
        logger.error("EmailIdAlreadyExistsException occurred: {}", exception.getMessage());
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    /**
     * Handles exceptions of type Exception.
     * Logs the exception message and returns a ResponseEntity with a bad request status
     * and the exception message as the response body.
     *
     * @param exception The exception to be handled.
     * @return A ResponseEntity with a bad request status and the exception message as the response body.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        logger.error("Exception occurred: {}", exception.getMessage());
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}