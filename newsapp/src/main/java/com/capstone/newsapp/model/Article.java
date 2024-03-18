package com.capstone.newsapp.model;

/*
 * create a model class for the Artical with following fields: {
      "source": {
        "id": null,
        "name": "Investor's Business Daily"
      },
      "author": "Investor's Business Daily",
      "title": "Stock Market Today: Dow Jones Rises Ahead Of Key Inflation Data; Nvidia, Tesla Extend Losses - Investor's Business Daily",
      "description": null,
      "url": "https://www.investors.com/market-trend/stock-market-today/dow-jones-sp500-nasdaq-ppi-nvidia-nvda-stock-tesla-tsla-stock/",
      "urlToImage": null,
      "publishedAt": "2024-03-14T12:25:33Z",
      "content": null
    }
    * using lambok @Data annotation to generate getters and setters
    * use @Document annotation to specify the collection name in the database
    * use @NoArgsConstructor and @AllArgsConstructor to generate default and parameterized constructors
 */
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Represents an article in the news application.
 */
@Data
@Document(collection = "article")
@NoArgsConstructor
@AllArgsConstructor
public class Article {
  /**
   * The unique identifier of the article.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  /**
   * The source of the article.
   */
  private Source source;

  /**
   * The author of the article.
   */
  private String author;

  /**
   * The title of the article.
   */
  private String title;

  /**
   * The description of the article.
   */
  private String description;

  /**
   * The URL of the article.
   */
  private String url;

  /**
   * The URL to the image associated with the article.
   */
  private String urlToImage;

  /**
   * The published date and time of the article.
   */
  private String publishedAt;

  /**
   * The content of the article.
   */
  private String content;
}
