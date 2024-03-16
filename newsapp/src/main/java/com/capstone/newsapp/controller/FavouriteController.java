package com.capstone.newsapp.controller;

/*
 * create a controller for the favourite with following methods:  getFavouriteArticles, saveFavouriteArticle, deleteFavouriteArticle using loggers from ArticleServices
 * use restcontroller annotation and autowire the ArticleService and use the methods to get the favourite articles, save the favourite articles and delete the favourite articles
 * use @GetMapping and @postMapping and @DeleteMapping annotation to specify the url for the methods
 * use route /api/v1/favourite for the methods
 */
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.newsapp.exceptions.ArticalAlreadyExistsException;
import com.capstone.newsapp.model.Article;
import com.capstone.newsapp.service.ArticleService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The FavouriteController class handles the API endpoints related to favourite articles.
 * It provides methods to fetch, save, and delete favourite articles.
 */
@RestController
@RequestMapping("/api/v1/favouriteArticle")
public class FavouriteController {

    private static final Logger logger = LogManager.getLogger(FavouriteController.class);

    @Autowired
    private ArticleService articleService;

    @GetMapping("/all")
    public ResponseEntity<List<Article>> getFavouriteArticles() {
        logger.info("Fetching all favourite articles");
        List<Article> favouriteArticles = articleService.getAllArticles();
        return ResponseEntity.ok(favouriteArticles);
    }

    @PostMapping("/save")
    public ResponseEntity<Article> saveFavouriteArticle(@RequestBody Article article) {
        try {
            Article savedArticle = articleService.saveArticle(article);
            logger.info("Article with id: {} saved as favourite", article.getId());
            return ResponseEntity.ok(savedArticle);
        } catch (ArticalAlreadyExistsException exception) {
            logger.error("Article with id: {} already saved as favourite", article.getId());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFavouriteArticle(@PathVariable int id) {
        articleService.deleteArticleById(id);
        logger.info("Article with id: {} deleted from favourites", id);
        return ResponseEntity.ok("Article with id: " + id + " deleted from favourites");
    }
}
