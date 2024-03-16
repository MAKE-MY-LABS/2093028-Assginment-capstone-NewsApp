package com.capstone.newsapp.model;

/*
 * create a model class for the user with following fields: emailId, password, userName, mobileNumber
 * using lambok @Data annotation to generate getters and setters
 * use @Document annotation to specify the collection name in the database
 * use @NoArgsConstructor and @AllArgsConstructor to generate default and parameterized constructors
 */
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a user in the system.
 */
@Data
@Document(collection = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * The email ID of the user.
     */
    @Id
    private String emailId;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * The username of the user.
     */
    private String userName;

    /**
     * The mobile number of the user.
     */
    private String mobileNumber;
}