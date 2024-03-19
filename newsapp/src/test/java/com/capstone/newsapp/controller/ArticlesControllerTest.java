package com.capstone.newsapp.controller;

import com.capstone.newsapp.service.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class ArticlesControllerTest {

    @Mock
    private ArticleService articleService;

    @InjectMocks
    private ArticlesController articlesController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
        * Test case for the getLatestArticles method.
        * This test verifies that the getLatestArticles method of the ArticlesController class
        * calls the getLatestArticles method of the ArticleService class.
        */
    @Test
    void testGetLatestArticles() {
        articlesController.getLatestArticles();
        verify(articleService).getLatestArticles();
    }

    /**
        * Test case for searching latest articles.
        * 
        * This test verifies that the searchLatestArticles method in the ArticlesController
        * class correctly calls the searchLatestArticles method in the ArticleService class
        * with the provided query.
        */
    @Test
    void testSearchLatestArticles() {
        String query = "tesla";
        articlesController.searchLatestArticles(query);
        verify(articleService).searchLatestArticles(query);
    }
}