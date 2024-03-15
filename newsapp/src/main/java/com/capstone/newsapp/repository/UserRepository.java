package com.capstone.newsapp.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.capstone.newsapp.model.User;


public interface UserRepository extends MongoRepository<User, String> {
    // add method to to check email and password and return optional of user
    public Optional<User> findByEmailIdAndPassword(String emailId, String password);
    
}
