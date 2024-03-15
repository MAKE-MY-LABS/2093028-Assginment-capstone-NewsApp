package com.capstone.newsapp.service;

/*
 * create class for the ArticleServiceImpl which implements the ArticleService
 * use @Service annotation to specify the service
 * use @Autowired annotation to inject the ArticleRepository
 * implement all the methods of the ArticleService
 *
 */

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.newsapp.exceptions.ArticalAlreadyExistsException;
import com.capstone.newsapp.model.Article;
import com.capstone.newsapp.repository.ArticleRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capstone.newsapp.exceptions.ArticalAlreadyExistsException;
import com.capstone.newsapp.model.Article;
import com.capstone.newsapp.repository.ArticleRepository;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Optional<Article> getArticleById(String id) {
        return articleRepository.findById(id);
    }

    @Override
    public Article saveArticle(Article article) throws ArticalAlreadyExistsException {
        if (articleRepository.existsById(article.getId())) {
            throw new ArticalAlreadyExistsException("Artical with id: " + article.getId() + " already exists");
        }
        return articleRepository.save(article);
    }

    @Override
    public void deleteArticleById(String id) {
        articleRepository.deleteById(id);
    }

    @Override
    public List<Article> getLatestArticles() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Article> searchLatestArticles(String keyword) {
        // TODO Auto-generated method stub
        return null;
    }

}


