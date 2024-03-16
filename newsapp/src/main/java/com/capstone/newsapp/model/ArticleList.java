package com.capstone.newsapp.model;

/*
 * create class for the ArticleList with property as atricles 
 * use @Data annotation to generate the getters and setters
 * use @NoArgsConstructor annotation to generate the no argument constructor
 * use @AllArgsConstructor annotation to generate the all argument constructor
 */

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a list of articles.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleList {
    private List<Article> articles;
}
