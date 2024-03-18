package com.capstone.newsapp.service;

/*
 * create interface for the ArticleService with following methods: getAllArticles, getArticleById which return Optionl artical, saveArticle, deleteArticleById
 * for saveArticle method, use ArticleRepository to save the Artical and throw ArticalAlreadyExistsException if the id already exists
 * 
 */
import java.util.List;
import java.util.Optional;

import com.capstone.newsapp.exceptions.ArticalAlreadyExistsException;
import com.capstone.newsapp.model.Article;
import com.capstone.newsapp.model.ArticleList;

/**
 * The ArticleService interface provides methods to interact with articles.
 */
public interface ArticleService {

    /**
     * Retrieves all articles.
     *
     * @return a list of all articles
     */
    public List<Article> getAllArticles();

    /**
     * Retrieves an article by its ID.
     *
     * @param id the ID of the article
     * @return an optional containing the article, or an empty optional if not found
     */
    public Optional<Article> getArticleById(String id);

    /**
     * Saves an article.
     *
     * @param article the article to be saved
     * @return the saved article
     * @throws ArticalAlreadyExistsException if the article already exists
     */
    public Article saveArticle(Article article) throws ArticalAlreadyExistsException;

    /**
     * Deletes an article by its ID.
     *
     * @param id the ID of the article to be deleted
     */
    public void deleteArticleById(String id);

    /**
     * Retrieves the latest articles from the API.
     *
     * @return a list of the latest articles
     */
    public ArticleList getLatestArticles();

    /**
     * Searches the latest articles from the API by keyword.
     *
     * @param keyword the keyword to search for
     * @return a list of articles matching the keyword
     */
    public ArticleList searchLatestArticles(String keyword);
}
