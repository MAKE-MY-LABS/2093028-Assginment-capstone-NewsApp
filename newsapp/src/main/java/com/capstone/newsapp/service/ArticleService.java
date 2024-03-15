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

public interface ArticleService {
    public List<Article> getAllArticles();
    public Optional<Article> getArticleById(String id);
    public Article saveArticle(Article article) throws ArticalAlreadyExistsException;
    public void deleteArticleById(String id);
    // get latest articles from api return list of articles
    // search latest articles from api by keyword list of articles
    public List<Article> getLatestArticles();
    public List<Article> searchLatestArticles(String keyword);

}
