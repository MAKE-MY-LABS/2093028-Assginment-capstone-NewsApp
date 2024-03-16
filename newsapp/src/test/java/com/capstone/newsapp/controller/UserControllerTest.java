package com.capstone.newsapp.controller;

import com.capstone.newsapp.exceptions.EmailIdAlreadyExistsException;
import com.capstone.newsapp.model.User;
import com.capstone.newsapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

/**
 * This class contains unit tests for the UserController class.
 */
public class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for the saveUser method in the UserController class.
     * It verifies the behavior of the saveUser method when saving a user.
     * 
     * Steps:
     * 1. Create a mock HTTP servlet request.
     * 2. Set the request attributes using RequestContextHolder.
     * 3. Mock the behavior of the saveUser method in the userService to return a new User object.
     * 4. Catch the EmailIdAlreadyExistsException and handle it.
     * 5. Test that the caught exception is an instance of EmailIdAlreadyExistsException.
     * 6. Create a new User object.
     * 7. Call the saveUser method in the userController and get the response entity.
     * 8. Test that the status code of the response entity is 201 (CREATED).
     */
    @Test
    void testSaveUser() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        try {
            when(userService.saveUser(any(User.class))).thenReturn(new User());
        } catch (EmailIdAlreadyExistsException e) {
            // Handle the exception here
            // test the exception
            assertEquals(EmailIdAlreadyExistsException.class, e.getClass());   
        }

        User user = new User();
        ResponseEntity<String> responseEntity = userController.saveUser(user);

        assertEquals(201, responseEntity.getStatusCode().value());
    }

    /**
     * Test case to validate the user.
     * 
     * This method tests the validation of a user by mocking the HTTP request and setting the request attributes.
     * It also mocks the UserService's saveUser method to return a new User object.
     * If an EmailIdAlreadyExistsException is thrown during the saveUser method call, it is caught and handled.
     * The method then asserts that the caught exception is of type EmailIdAlreadyExistsException.
     * Finally, it creates a new User object and calls the validateUser method of the UserController.
     * The method asserts that the HTTP response status code is 200 (OK).
     */
    @Test
    void testValidateUser() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        try {
            when(userService.saveUser(any(User.class))).thenReturn(new User());
        } catch (EmailIdAlreadyExistsException e) {
            // Handle the exception here
            assertEquals(EmailIdAlreadyExistsException.class, e.getClass());   
        }

        User user = new User();
        ResponseEntity<String> responseEntity = userController.validateUser(user);

        assertEquals(200, responseEntity.getStatusCode().value());
    }
}