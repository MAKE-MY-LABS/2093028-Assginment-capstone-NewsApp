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

@Data
@Document(collection = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String emailId;
    private String password;
    private String userName;
    private String mobileNumber;
}