package com.capstone.newsapp.controller;

import com.capstone.newsapp.controller.UserController;
import com.capstone.newsapp.exceptions.EmailIdAlreadyExistsException;
import com.capstone.newsapp.model.User;
import com.capstone.newsapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case to validate the user.
     * 
     * This method creates a user object with a test email and password. It then mocks the behavior of the userService's
     * validateUser method to return an Optional containing the user object. The userController's validateUser method is
     * called with the user object as a parameter. The method asserts that the HTTP status code of the response entity is
     * HttpStatus.OK and verifies that the userService's validateUser method is called exactly once with the email and
     * password of the user object.
     */
    @Test
    public void testValidateUser() {
        User user = new User();
        user.setEmailId("test@test.com");
        user.setPassword("password");

        when(userService.validateUser(user.getEmailId(), user.getPassword())).thenReturn(Optional.of(user));

        ResponseEntity<?> result = userController.validateUser(user);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(userService, times(1)).validateUser(user.getEmailId(), user.getPassword());
    }

    /**
     * Test case to verify the functionality of saving a user.
     * @throws EmailIdAlreadyExistsException if the email ID already exists.
     */
    @Test
    public void testSaveUser() throws EmailIdAlreadyExistsException {
        User user = new User();
        user.setEmailId("test@test.com");
        user.setPassword("password");

        when(userService.saveUser(any(User.class))).thenReturn(user);

        ResponseEntity<?> result = userController.saveUser(user);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("User with emailId: test@test.com saved successfully", result.getBody());
        verify(userService, times(1)).saveUser(user);
    }
}