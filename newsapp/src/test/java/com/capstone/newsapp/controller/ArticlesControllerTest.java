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

    @Test
    void testGetLatestArticles() {
        articlesController.getLatestArticles();
        verify(articleService).getLatestArticles();
    }

    @Test
    void testSearchLatestArticles() {
        String query = "tesla";
        articlesController.searchLatestArticles(query);
        verify(articleService).searchLatestArticles(query);
    }
}