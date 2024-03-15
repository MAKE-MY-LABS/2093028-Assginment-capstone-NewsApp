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

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EmailIdAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailIdAlreadyExistsException(EmailIdAlreadyExistsException exception) {
        logger.error("EmailIdAlreadyExistsException occurred: {}", exception.getMessage());
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        logger.error("Exception occurred: {}", exception.getMessage());
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}