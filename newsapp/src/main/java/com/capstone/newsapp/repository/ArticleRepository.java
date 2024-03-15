package com.capstone.newsapp.repository;

/*
 * create a repositiry interface for artical which implemnts mongodb repository
 * use @Repository annotation to specify the repository
 */
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.capstone.newsapp.model.Article;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {

}
