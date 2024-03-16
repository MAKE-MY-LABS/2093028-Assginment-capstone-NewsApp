package com.capstone.newsapp.service;

/*
 * create interface for the user with following methods: validateUser, saveUser * 
 * for saveuser method, use UserRepository to save the user and throw EmailIdAlreadyExistsException if the emailId already exists
 * 
 */
import java.util.Optional;

import com.capstone.newsapp.exceptions.EmailIdAlreadyExistsException;
import com.capstone.newsapp.model.User;

/**
 * The UserService interface provides methods for user validation and user saving.
 */
public interface UserService {
    
    /**
     * Validates a user based on the provided email and password.
     *
     * @param emailId the email ID of the user
     * @param password the password of the user
     * @return an Optional containing the validated User object if the user is valid, otherwise an empty Optional
     */
    public Optional<User> validateUser(String emailId, String password);
    
    /**
     * Saves a user to the system.
     *
     * @param user the User object to be saved
     * @return the saved User object
     * @throws EmailIdAlreadyExistsException if the email ID already exists in the system
     */
    public User saveUser(User user) throws EmailIdAlreadyExistsException;
}




