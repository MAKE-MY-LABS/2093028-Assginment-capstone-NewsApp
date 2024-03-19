package com.capstone.newsapp.service;

import com.capstone.newsapp.exceptions.EmailIdAlreadyExistsException;
import com.capstone.newsapp.model.User;
import com.capstone.newsapp.repository.UserRepository;
import com.capstone.newsapp.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /**
        * Test case for validating a user.
        */
    @Test
    public void testValidateUser() {
        User user = new User();
        user.setEmailId("test@test.com");
        user.setPassword("password");

        when(userRepository.findByEmailIdAndPassword(user.getEmailId(), user.getPassword())).thenReturn(Optional.of(user));

        Optional<User> result = userService.validateUser(user.getEmailId(), user.getPassword());

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        verify(userRepository, times(1)).findByEmailIdAndPassword(user.getEmailId(), user.getPassword());
    }

    /**
     * Test case for the saveUser method in the UserServiceImpl class.
     * It tests the scenario where a new user is saved successfully.
     *
     * @throws EmailIdAlreadyExistsException if the email ID already exists in the repository.
     */
    @Test
    public void testSaveUser() throws EmailIdAlreadyExistsException {
        User user = new User();
        user.setEmailId("test@test.com");
        user.setPassword("password");

        when(userRepository.findById(user.getEmailId())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.saveUser(user);

        assertEquals(user, result);
        verify(userRepository, times(1)).findById(user.getEmailId());
        verify(userRepository, times(1)).save(user);
    }

    /**
     * Test case to verify that the saveUser method throws an EmailIdAlreadyExistsException
     * when the email ID already exists in the user repository.
     */
    @Test
    public void testSaveUser_EmailIdAlreadyExists() {
        User user = new User();
        user.setEmailId("test@test.com");
        user.setPassword("password");

        when(userRepository.findById(user.getEmailId())).thenReturn(Optional.of(user));

        assertThrows(EmailIdAlreadyExistsException.class, () -> userService.saveUser(user));
        verify(userRepository, times(1)).findById(user.getEmailId());
    }
}