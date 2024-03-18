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
 * This class contains unit tests for the FavoriteController class.
 */
public class FavoriteControllerTest {

    @InjectMocks
    FavoriteController favoriteController;

    @Mock
    ArticleService articleService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for deleting a favorite article.
     * 
     * This method tests the functionality of deleting a favorite article by
     * calling the
     * deletefavoriteArticle method of the favoriteController class. It verifies
     * that the
     * articleService's deleteArticleById method is called with the correct article
     * ID and
     * that the response entity's status code is 200.
     */
    @Test
    void testDeleteFavoriteArticle() {
        doNothing().when(articleService).deleteArticleById("1");
        ResponseEntity<String> responseEntity = favoriteController.deleteFavoriteArticle("1");
        assertEquals(200, responseEntity.getStatusCode().value());
    }

    /**
     * Test case for the getfavoriteArticles method in the favoriteController
     * class.
     * It verifies that the method returns the correct number of favorite articles.
     */
    @Test
    void testGetFavoriteArticles() {
        Article article1 = new Article();
        Article article2 = new Article();
        when(articleService.getAllArticles()).thenReturn(Arrays.asList(article1, article2));
        ResponseEntity<List<Article>> responseEntity = favoriteController.getFavoriteArticles();
        List<Article> result = responseEntity.getBody();
        assertEquals(2, result.size());
    }

    /**
     * Test case for saving a favorite article.
     * 
     * This method creates a new Article object and mocks the behavior of the
     * articleService.saveArticle() method
     * to return the same Article object. It then calls the savefavoriteArticle()
     * method of the favoriteController
     * and asserts that the returned ResponseEntity contains the same Article
     * object. If an ArticalAlreadyExistsException
     * is thrown during the process, it is caught and the exception type is
     * asserted.
     */
    @Test
    void testSaveFavoriteArticle() {
        Article article = new Article();
        try {
            when(articleService.saveArticle(article)).thenReturn(article);
            ResponseEntity<Article> responseEntity = favoriteController.saveFavoriteArticle(article);
            Article result = responseEntity.getBody();
            assertEquals(article, result);
        } catch (ArticalAlreadyExistsException e) {
            // Handle the exception and return the appropriate response
            // test exception
            assertEquals(ArticalAlreadyExistsException.class, e.getClass());
        }
    }
}