package com.capstone.newsapp.controller;

/*
 * create a controller for the articles with following methods:  getLatestArticles, searchLatestArticles using loggers from ArticleServices
 * use restcontroller annotation and autowire the ArticleService and use the methods to get the latest articles and search the latest articles
 * use @GetMapping and @postMapping annotation to specify the url for the methods
 * use route /api/v1/articles for the methods
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.newsapp.model.ArticleList;
import com.capstone.newsapp.service.ArticleService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Controller class for handling API requests related to articles.
 */
@RestController
@RequestMapping("/api/v1/articles")
public class ArticlesController {

    private static final Logger logger = LogManager.getLogger(ArticlesController.class);

    @Autowired
    private ArticleService articleService;

    /**
     * Retrieves the latest articles.
     *
     * @return ResponseEntity containing the latest articles
     */
    @GetMapping("/latest")
    public ResponseEntity<ArticleList> getLatestArticles() {
        logger.info("Fetching the latest articles");
        ArticleList latestArticles = articleService.getLatestArticles();
        return ResponseEntity.ok(latestArticles);
    }

    /**
     * Searches for the latest articles based on a keyword.
     *
     * @param keyword the keyword to search for
     * @return ResponseEntity containing the search results
     */
    @PostMapping("/search")
    public ResponseEntity<ArticleList> searchLatestArticles(@RequestBody String keyword) {
        logger.info("Searching the latest articles by keyword: {}", keyword);
        ArticleList latestArticles = articleService.searchLatestArticles(keyword);
        return ResponseEntity.ok(latestArticles);
    }
}
