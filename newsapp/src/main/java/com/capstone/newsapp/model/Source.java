package com.capstone.newsapp.model;

/*
 * create a model class for the Source with following fields: id, name
 * using lambok @Data annotation to generate getters and setters
 * use @Document annotation to specify the collection name in the database
 * use @NoArgsConstructor and @AllArgsConstructor to generate default and parameterized constructors
 * use @Id annotation to specify the primary key of the collection
 * use @GeneratedValue annotation to specify the strategy for generating the primary key
 */
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Represents a news source.
 */
@Data
@Document(collection = "source")
@NoArgsConstructor
@AllArgsConstructor
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
}
