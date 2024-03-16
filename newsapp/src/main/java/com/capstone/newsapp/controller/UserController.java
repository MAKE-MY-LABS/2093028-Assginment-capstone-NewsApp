package com.capstone.newsapp.controller;

/*
 * create a controller for the user with following methods: validateUser, saveUser using loggers from UserServices 
 * user restcontroller autowire the UserService and use the methods to validate and save the user
 * for saveuser method, use UserRepository to save the user and throw EmailIdAlreadyExistsException if the emailId already exists
 * for validateUser method, use UserRepository to validate the user and return generated the token using jwt
 * create generate token method using jwt
 *  
 */

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.newsapp.exceptions.EmailIdAlreadyExistsException;
import com.capstone.newsapp.model.User;
import com.capstone.newsapp.service.UserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * The UserController class handles the HTTP requests related to user operations.
 * It provides endpoints for user login and user registration.
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * Validates the user with the given emailId and password.
     *
     * @param user The user object containing the emailId and password.
     * @return A ResponseEntity containing a string representation of the validation result.
     */
    @PostMapping("/login")
    public ResponseEntity<String> validateUser(@RequestBody User user) {
        logger.info("Validating user with emailId: {}", user.getEmailId());
        Optional<User> validatedUser = userService.validateUser(user.getEmailId(), user.getPassword());
        if (validatedUser.isPresent()) {
            logger.info("User with emailId: {} validated successfully", user.getEmailId());
            return ResponseEntity.ok(generateToken(user.getEmailId()));
        } else {
            logger.error("User with emailId: {} not found", user.getEmailId());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid emailId or password");
        }
    }

    /**
     * Saves a user in the system.
     *
     * @param user The user object to be saved.
     * @return ResponseEntity<String> The response entity indicating the status of the save operation.
     *         If the user is saved successfully, it returns a response entity with HTTP status 200 (OK)
     *         and a success message. If the user already exists, it returns a response entity with
     *         HTTP status 400 (Bad Request) and an error message.
     */
    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        try {
            userService.saveUser(user);
            logger.info("User with emailId: {} saved successfully", user.getEmailId());
            return ResponseEntity.ok("User with emailId: " + user.getEmailId() + " saved successfully");
        } catch (EmailIdAlreadyExistsException exception) {
            logger.error("EmailIdAlreadyExistsException occurred: {}", exception.getMessage());
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    /**
     * Generates a token using JWT for the given emailId.
     *
     * @param emailId The emailId for which the token needs to be generated.
     * @return The generated token as a string.
     */
    private String generateToken(String emailId) {
        return Jwts.builder().setSubject(emailId).signWith(SignatureAlgorithm.HS256, "CTS-NEWSAPP").compact();
    }
}