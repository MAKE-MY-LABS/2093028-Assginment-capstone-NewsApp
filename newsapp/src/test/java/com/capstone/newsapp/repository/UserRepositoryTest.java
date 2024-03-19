package com.capstone.newsapp.repository;

import com.capstone.newsapp.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserRepositoryTest {


    @Mock
    private UserRepository userRepositoryMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for the findByEmailIdAndPassword method in the UserRepository class.
     * It verifies that the method returns the expected User object when provided with a valid email and password.
     */
    @Test
    void testFindByEmailIdAndPassword() {
        User user = new User();
        String email = "test@test.com";
        String password = "password";
        when(userRepositoryMock.findByEmailIdAndPassword(email, password)).thenReturn(Optional.of(user));
        Optional<User> result = userRepositoryMock.findByEmailIdAndPassword(email, password);
        assertEquals(user, result.get());
    }
}
