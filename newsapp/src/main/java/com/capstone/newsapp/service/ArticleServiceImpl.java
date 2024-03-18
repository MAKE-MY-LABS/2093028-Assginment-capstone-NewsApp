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
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import com.capstone.newsapp.exceptions.ArticalAlreadyExistsException;
import com.capstone.newsapp.model.Article;
import com.capstone.newsapp.model.ArticleList;
import com.capstone.newsapp.repository.ArticleRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class ArticleServiceImpl implements ArticleService {

    private static final Logger logger = LogManager.getLogger(ArticleServiceImpl.class);

    @Autowired
    private ArticleRepository articleRepository;

    @Value("${API_URL}")
    private String apiUrl;

    @Value("${API_KEY}")
    private String apiKey;

    /**
     * Retrieves all articles from the database.
     *
     * @return The list of all articles.
     */
    @Override
    public List<Article> getAllArticles() {
        logger.info("Fetching all articles");
        return articleRepository.findAll();
    }

    /**
     * Retrieves an article by its ID.
     *
     * @param id The ID of the article.
     * @return An Optional containing the article, or an empty Optional if not
     *         found.
     */
    @Override
    public Optional<Article> getArticleById(String id) {
        logger.info("Fetching article with id: {}", id);
        return articleRepository.findById(id);
    }

    /**
     * Saves an article to the database.
     *
     * @param article The article to be saved.
     * @return The saved article.
     * @throws ArticalAlreadyExistsException If an article with the same ID already
     *                                       exists.
     */
    @Override
    public Article saveArticle(Article article) throws ArticalAlreadyExistsException {
        if (articleRepository.existsById(String.valueOf(article.getId()))) {
            logger.error("Article with id: {} already exists", article.getId());
            throw new ArticalAlreadyExistsException("Article with id: " + article.getId() + " already exists");
        }
        logger.info("Saving article with id: {}", article.getId());
        return articleRepository.save(article);
    }

    /**
     * Deletes an article from the database by its ID.
     *
     * @param id The ID of the article to be deleted.
     */
    @Override
    public void deleteArticleById(String id) {
        logger.info("Deleting article with id: {}", id);
        articleRepository.deleteById(id);
    }

    /**
     * Retrieves the latest articles from the API.
     *
     * @return The list of latest articles.
     */
    @Override
    public ArticleList getLatestArticles() {
        logger.info("Fetching latest articles");
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "top-headlines?country=us&category=business&apiKey=" + apiKey;
        logger.info("Fetching latest articles from: {}", url);
        ArticleList articleList = restTemplate.getForObject(url, ArticleList.class);
        return articleList;
    }

    /**
     * Searches for the latest articles based on a keyword.
     *
     * @param keyword The keyword to search for.
     * @return An ArticleList object containing the latest articles.
     */
    @Override
    public ArticleList searchLatestArticles(String keyword) {
        logger.info("Searching latest articles with keyword: {}", keyword);
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "everything?q=" + keyword + "&from=2024-02-25&sortBy=publishedAt&apiKey=" + apiKey;
        logger.info("Searching latest articles from: {}", url);
        ArticleList articleList = restTemplate.getForObject(url, ArticleList.class);
        return articleList;
    }

}
