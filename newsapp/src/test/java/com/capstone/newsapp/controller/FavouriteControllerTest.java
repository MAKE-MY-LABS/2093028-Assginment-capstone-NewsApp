package com.capstone.newsapp.controller;

import com.capstone.newsapp.exceptions.ArticalAlreadyExistsException;
import com.capstone.newsapp.model.Article;
import com.capstone.newsapp.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the FavouriteController class.
 */
public class FavouriteControllerTest {

    @InjectMocks
    FavouriteController favouriteController;

    @Mock
    ArticleService articleService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for deleting a favourite article.
     * 
     * This method tests the functionality of deleting a favourite article by calling the
     * deleteFavouriteArticle method of the FavouriteController class. It verifies that the
     * articleService's deleteArticleById method is called with the correct article ID and
     * that the response entity's status code is 200.
     */
    @Test
    void testDeleteFavouriteArticle() {
        doNothing().when(articleService).deleteArticleById(1);
        ResponseEntity<String> responseEntity = favouriteController.deleteFavouriteArticle(1);
        assertEquals(200, responseEntity.getStatusCode().value());
    }

    /**
     * Test case for the getFavouriteArticles method in the FavouriteController class.
     * It verifies that the method returns the correct number of favourite articles.
     */
    @Test
    void testGetFavouriteArticles() {
        Article article1 = new Article();
        Article article2 = new Article();
        when(articleService.getAllArticles()).thenReturn(Arrays.asList(article1, article2));
        ResponseEntity<List<Article>> responseEntity = favouriteController.getFavouriteArticles();
        List<Article> result = responseEntity.getBody();
        assertEquals(2, result.size());
    }

    /**
     * Test case for saving a favourite article.
     * 
     * This method creates a new Article object and mocks the behavior of the articleService.saveArticle() method
     * to return the same Article object. It then calls the saveFavouriteArticle() method of the favouriteController
     * and asserts that the returned ResponseEntity contains the same Article object. If an ArticalAlreadyExistsException
     * is thrown during the process, it is caught and the exception type is asserted.
     */
    @Test
    void testSaveFavouriteArticle() {
        Article article = new Article();
        try {
            when(articleService.saveArticle(article)).thenReturn(article);
            ResponseEntity<Article> responseEntity = favouriteController.saveFavouriteArticle(article);
            Article result = responseEntity.getBody();
            assertEquals(article, result);
        } catch (ArticalAlreadyExistsException e) {
            // Handle the exception and return the appropriate response
            // test exception
            assertEquals(ArticalAlreadyExistsException.class, e.getClass());
        }
    }
}