package com.capstone.newsapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.capstone.newsapp.exceptions.ArticalAlreadyExistsException;
import com.capstone.newsapp.model.Article;
import com.capstone.newsapp.model.ArticleList;
import com.capstone.newsapp.repository.ArticleRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the ArticleServiceImpl class.
 */
/**
 * This class contains unit tests for the ArticleServiceImpl class.
 */
public class ArticleServiceImplTest {

    
    @InjectMocks
    private ArticleServiceImpl articleService;

    @Mock
    private ArticleRepository articleRepository;

    private Article article;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        // craete artical  object with mock data using {
    //   "id": 0,
    //   "source": {
    //     "id": null,
    //     "name": "CNBC"
    //   },
    //   "author": "Alex Harring",
    //   "title": "Stock futures are little changed after strong inflation data spooks investors: Live updates - CNBC",
    //   "description": "The three major indexes finished Thursday's session lower.",
    //   "url": "https://www.cnbc.com/2024/03/14/stock-market-today-live-updates.html",
    //   "urlToImage": "https://image.cnbcfm.com/api/v1/image/107386389-1710266151994-NYSE_Traders-Photo-20240312-CC-1.jpg?v=1710331621&w=1920&h=1080",
    //   "publishedAt": "2024-03-15T00:38:00Z",
    //   "content": "Stock futures are near flat Thursday night as investors analyzed the fresh batch of corporate earnings and attempted to look beyond the latest inflation reading.\r\nFutures tied to the Dow Jones Indust… [+1866 chars]"
    // }

     article = new Article();
    article.setId(0);
    article.setAuthor("Alex Harring");
    article.setTitle("Stock futures are little changed after strong inflation data spooks investors: Live updates - CNBC");
    article.setDescription("The three major indexes finished Thursday's session lower.");
    article.setUrl("https://www.cnbc.com/2024/03/14/stock-market-today-live-updates.html");
    article.setUrlToImage("https://image.cnbcfm.com/api/v1/image/107386389-1710266151994-NYSE_Traders-Photo-20240312-CC-1.jpg?v=1710331621&w=1920&h=1080");
    article.setPublishedAt("2024-03-15T00:38:00Z");
    article.setContent("Stock futures are near flat Thursday night as investors analyzed the fresh batch of corporate earnings and attempted to look beyond the latest inflation reading.\r\nFutures tied to the Dow Jones Indust… [+1866 chars]");

    }

    //tearDown method to release any resources that are allocated in setup method
    // and to release memory allocation
    // and to set the object to null
    
    @AfterEach
    public void tearDown() {
        article = null;
    }


    /**
     * Test case for the deleteArticleById method in the ArticleServiceImpl class.
     * 
     * This method verifies that the deleteArticleById method correctly deletes an article by its ID.
     * It mocks the behavior of the articleRepository's deleteById method to do nothing when called with the ID "1".
     * Then, it calls the deleteArticleById method of the articleService with the ID 1.
     * Finally, it verifies that the deleteById method of the articleRepository was called exactly once with the ID "1".
     */
    @Test
    void testDeleteArticleById() {
        doNothing().when(articleRepository).deleteById("1");
        articleService.deleteArticleById(1);
        verify(articleRepository, times(1)).deleteById("1");
    }

    /**
     * Test case for the {@link ArticleServiceImpl#getAllArticles()} method.
     * It verifies that the method returns the correct number of articles.
     */
    @Test
    void testGetAllArticles() {
        when(articleRepository.findAll()).thenReturn(Arrays.asList(article));
        List<Article> result = articleService.getAllArticles();
        assertEquals(2, result.size());
    }
    

    /**
     * Test case for the getLatestArticles method of the ArticleServiceImpl class.
     * It verifies that the returned ArticleList object is equal to the expected ArticleList object.
     */
    @Test
    void testGetLatestArticles() {
        ArticleList articleList = new ArticleList();
        articleList.setArticles(Arrays.asList(article));
        when(articleService.getLatestArticles()).thenReturn(articleList);
        ArticleList result = articleService.getLatestArticles();
        assertEquals(articleList, result);
    }

    /**
     * Test case for the saveArticle method in the ArticleServiceImpl class.
     * It verifies that the saveArticle method correctly saves an article and returns the saved article.
     * 
     * @throws ArticalAlreadyExistsException if the article already exists in the repository
     */
    @Test
    void testSaveArticle() throws ArticalAlreadyExistsException {
        when(articleRepository.save(article)).thenReturn(article);
        Article result = articleService.saveArticle(article);
        assertEquals(article, result);
    }

    /**
     * Test case for searching latest articles.
     */
    @Test
    void testSearchLatestArticles() {
        String keyword = "test";
        ArticleList articleList = new ArticleList();
        Article article12 = new Article();
        article.setTitle("Title");
        article.setAuthor("MAcY");
        Article article1= new Article();
        article.setTitle("Title1");
        article.setId(0);
        article.setAuthor("MAcY1");
        articleList.setArticles(Arrays.asList(article,article12, article1));

        when(articleService.searchLatestArticles(keyword)).thenReturn(articleList);
        ArticleList result = articleService.searchLatestArticles(keyword);
        assertEquals(articleList, result);
    }
}
