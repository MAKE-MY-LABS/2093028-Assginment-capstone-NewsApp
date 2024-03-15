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

@Data
@Document(collection = "article")
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private Source source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;
}
