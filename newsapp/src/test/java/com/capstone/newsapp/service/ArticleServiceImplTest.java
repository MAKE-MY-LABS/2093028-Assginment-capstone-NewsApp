package com.capstone.newsapp.service;

import com.capstone.newsapp.exceptions.ArticalAlreadyExistsException;
import com.capstone.newsapp.model.Article;
import com.capstone.newsapp.model.ArticleList;
import com.capstone.newsapp.repository.ArticleRepository;
import com.capstone.newsapp.service.ArticleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ArticleServiceImplTest {

    @InjectMocks
    private ArticleServiceImpl articleService;

    @Mock
    private ArticleRepository articleRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllArticles() {
        Article article = new Article();
        article.setId("1");
        when(articleRepository.findAll()).thenReturn(Arrays.asList(article));

        List<Article> result = articleService.getAllArticles();

        assertEquals(1, result.size());
        assertEquals(article, result.get(0));
        verify(articleRepository, times(1)).findAll();
    }

    @Test
    public void testGetArticleById() {
        Article article = new Article();
        article.setId("1");
        when(articleRepository.findById(article.getId())).thenReturn(Optional.of(article));

        Optional<Article> result = articleService.getArticleById(article.getId());

        assertTrue(result.isPresent());
        assertEquals(article, result.get());
        verify(articleRepository, times(1)).findById(article.getId());
    }

    @Test
    public void testSaveArticle() throws ArticalAlreadyExistsException {
        Article article = new Article();
        article.setId("1");
        when(articleRepository.existsById(article.getId())).thenReturn(false);
        when(articleRepository.save(any(Article.class))).thenReturn(article);

        Article result = articleService.saveArticle(article);

        assertEquals(article, result);
        verify(articleRepository, times(1)).existsById(article.getId());
        verify(articleRepository, times(1)).save(article);
    }

    @Test
    public void testSaveArticle_ArticleAlreadyExists() {
        Article article = new Article();
        article.setId("1");
        when(articleRepository.existsById(article.getId())).thenReturn(true);

        assertThrows(ArticalAlreadyExistsException.class, () -> articleService.saveArticle(article));
        verify(articleRepository, times(1)).existsById(article.getId());
    }

    @Test
    public void testDeleteArticleById() {
        String id = "1";
        doNothing().when(articleRepository).deleteById(id);

        articleService.deleteArticleById(id);

        verify(articleRepository, times(1)).deleteById(id);
    }
}