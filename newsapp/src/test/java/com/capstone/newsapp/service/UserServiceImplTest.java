package com.capstone.newsapp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.capstone.newsapp.exceptions.EmailIdAlreadyExistsException;
import com.capstone.newsapp.model.User;
import com.capstone.newsapp.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

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
        }

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testValidateUser() {
        User user = new User();
        user.setEmailId("Cts@cts.com");
        user.setPassword("cts");
        when(userRepository.findById(user.getEmailId())).thenReturn(Optional.of(user));

        userService.validateUser(user.getEmailId(), user.getPassword());

        verify(userRepository, times(1)).findById(user.getEmailId());
    }


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