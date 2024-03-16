package com.capstone.newsapp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.capstone.newsapp.exceptions.EmailIdAlreadyExistsException;
import com.capstone.newsapp.model.User;
import com.capstone.newsapp.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Optional;

/**
 * This class contains unit tests for the UserServiceImpl class.
 * It uses Mockito framework for mocking dependencies and verifying interactions.
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    /**
     * Test case to verify the functionality of saving a user.
     */
    @Test
    void testSaveUser() {
        User user = new User(); // assuming User is your model class
        user.setEmailId("Cts@cts.com");
        user.setPassword("cts");
        when(userRepository.save(any(User.class))).thenReturn(user);

        try {
            userService.saveUser(user);
        } catch (EmailIdAlreadyExistsException e) {
            // Handle the exception  
            // test the exception
            assertEquals(EmailIdAlreadyExistsException.class, e.getClass());          
        }

        verify(userRepository, times(1)).save(user);
    }

    /**
        * Test case to validate the user.
        */
    @Test
    void testValidateUser() {
        User user = new User();
        user.setEmailId("Cts@cts.com");
        user.setPassword("cts");
        when(userRepository.findById(user.getEmailId())).thenReturn(Optional.of(user));

        userService.validateUser(user.getEmailId(), user.getPassword());

        verify(userRepository, times(1)).findById(user.getEmailId());
    }


    /**
     * Test case to verify the behavior of saving a user when the email ID already exists.
     * It creates a new User object with an existing email ID and a password.
     * It mocks the behavior of the userRepository.save() method to throw an EmailIdAlreadyExistsException.
     * It asserts that an EmailIdAlreadyExistsException is thrown when the userService.saveUser() method is called.
     * It verifies that the userRepository.save() method is called exactly once.
     */
    @Test
    void testSaveUser_EmailIdAlreadyExists() {
        User user = new User();
        user.setEmailId("existingEmail@domain.com");
        user.setPassword("password");

        when(userRepository.save(any(User.class))).thenThrow(EmailIdAlreadyExistsException.class);

        assertThrows(EmailIdAlreadyExistsException.class, () -> {
            userService.saveUser(user);
        });

        verify(userRepository, times(1)).save(user);
    }

    /**
     * Test case to validate user with invalid credentials.
     * 
     * This test case verifies that the `validateUser` method throws an exception when the provided email and password are invalid.
     * It mocks the behavior of the `userRepository.findById` method to return an empty optional when searching for the user by email.
     * The `assertThrows` method is used to assert that an exception is thrown when calling the `validateUser` method with the invalid email and password.
     * Finally, the `verify` method is used to verify that the `userRepository.findById` method is called exactly once with the provided email.
     */
    @Test
    void testValidateUser_InvalidCredentials() {
        String emailId = "invalidEmail@domain.com";
        String password = "invalidPassword";

        when(userRepository.findById(emailId)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> {
            userService.validateUser(emailId, password);
        });

        verify(userRepository, times(1)).findById(emailId);
    }

}