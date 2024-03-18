package com.capstone.newsapp.controller;

/*
 * create a controller for the favorite with following methods:  getfavoriteArticles, savefavoriteArticle, deletefavoriteArticle using loggers from ArticleServices
 * use restcontroller annotation and autowire the ArticleService and use the methods to get the favorite articles, save the favorite articles and delete the favorite articles
 * use @GetMapping and @postMapping and @DeleteMapping annotation to specify the url for the methods
 * use route /api/v1/favorite for the methods
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.newsapp.exceptions.ArticalAlreadyExistsException;
import com.capstone.newsapp.model.Article;
import com.capstone.newsapp.service.ArticleService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The FavoriteController class handles the API endpoints related to favorite
 * articles.
 * It provides methods to fetch, save, and delete favorite articles.
 */
@RestController
@RequestMapping("/api/v1/favorite-articles")
public class FavoriteController {

    private static final Logger logger = LogManager.getLogger(FavoriteController.class);

    @Autowired
    private ArticleService articleService;

    @GetMapping
    public ResponseEntity<List<Article>> getFavoriteArticles() {
        logger.info("Fetching all favorite articles");
        List<Article> favoriteArticles = articleService.getAllArticles();
        return ResponseEntity.ok(favoriteArticles);
    }

    @PostMapping
    public ResponseEntity<Article> saveFavoriteArticle(@RequestBody Article article) {
        try {
            Article savedArticle = articleService.saveArticle(article);
            logger.info("Article with id: {} saved as favorite", article.getId());
            return ResponseEntity.ok(savedArticle);
        } catch (ArticalAlreadyExistsException exception) {
            logger.error("Article with id: {} already saved as favorite", article.getId());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteFavoriteArticle(@PathVariable String id) {
        articleService.deleteArticleById(id);
        logger.info("Article with id: {} deleted from favorite", id);
        return ResponseEntity.ok("Article with id: " + id + " deleted from favorite");
    }
}
