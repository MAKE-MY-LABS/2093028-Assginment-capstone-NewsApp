package com.capstone.newsapp.service;

/*
 * create interface for the user with following methods: validateUser, saveUser * 
 * for saveuser method, use UserRepository to save the user and throw EmailIdAlreadyExistsException if the emailId already exists
 * 
 */
import java.util.Optional;

import com.capstone.newsapp.exceptions.EmailIdAlreadyExistsException;
import com.capstone.newsapp.model.User;

public interface UserService {
    public Optional<User> validateUser(String emailId, String password);
    public User saveUser(User user) throws EmailIdAlreadyExistsException;
}




