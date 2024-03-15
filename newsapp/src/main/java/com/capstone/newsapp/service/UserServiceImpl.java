package com.capstone.newsapp.service;

/*
 * create class UserServiceImpl which implements UserService and override the methods
 * use UserRepository to save the user and throw EmailIdAlreadyExistsException if the emailId already exists
 * use UserRepository to validate the user and return optional of user
 * autowire UserRepository
 * use loggers to log the messages using log4f
 *
 */

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.newsapp.exceptions.EmailIdAlreadyExistsException;
import com.capstone.newsapp.model.User;
import com.capstone.newsapp.repository.UserRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> validateUser(String emailId, String password) {
        logger.info("Validating user with emailId: {}", emailId);
        // based on if validate user with return startments
        Optional<User> user = userRepository.findByEmailIdAndPassword(emailId, password);
        if (user.isPresent()) {
            logger.info("User with emailId: {} validated successfully", emailId);
            return user;
        } else {
            logger.error("User with emailId: {} not found", emailId);
            return Optional.empty();
        }
    }

    @Override
    public User saveUser(User user) throws EmailIdAlreadyExistsException {
        logger.info("Saving user with emailId: {}", user.getEmailId());
        if (userRepository.findById(user.getEmailId()).isPresent()) {
            logger.error("EmailIdAlreadyExistsException occurred: EmailId {} already exists", user.getEmailId());
            throw new EmailIdAlreadyExistsException("EmailId " + user.getEmailId() + " already exists");
        }
        return userRepository.save(user);
    }

}