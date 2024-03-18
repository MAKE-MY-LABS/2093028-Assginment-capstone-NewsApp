import { Component, OnInit } from '@angular/core';
import { NGXLogger } from 'ngx-logger';
import { IArticle } from 'src/app/models/article.interface';
import { HomeService } from 'src/app/services/home.service';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss']
})
/**
 * Represents the ArticlesComponent class.
 */
export class ArticlesComponent implements OnInit {
  selectedArticle: IArticle | null = null;
  articles: IArticle[] = [];

  constructor(private homeService: HomeService, private logger: NGXLogger) { }

  /**
   * Initializes the component and retrieves the articles.
   */
  ngOnInit(): void {
    this.getArticles();
  }

  /**
   * Retrieves the latest articles from the server.
   */
  getArticles() {
    this.homeService.getLatestArticles().subscribe((response: { articles: IArticle[] }) => {
      this.articles = response.articles || [];
      this.logger.debug('Articles received:', this.articles);
    });
  }

  /**
   * Sets the selected article to show its details.
   * @param article - The article to show details for.
   */
  showDetails(article: IArticle) {
    this.selectedArticle = article;
  }

  /**
   * Closes the details of the selected article.
   */
  closeDetails() {
    this.selectedArticle = null;
  }

  /**
   * Searches for articles based on the provided keyword.
   * If the keyword is empty, retrieves all articles.
   * @param keyword - The keyword to search for.
   */
  searchArticles(keyword: string) {
    if (keyword && keyword !== '') {
      this.logger.debug('Searching articles with keyword:', keyword);
      this.homeService.searchLatestArticles(keyword).subscribe((response: { articles: IArticle[] }) => {
        this.articles = response.articles || [];
        this.logger.debug('Articles received:', this.articles);
      });
    } else {
      this.logger.debug('Keyword is empty, getting all articles');
      this.getArticles();
    }
  }

  /**
   * Adds the specified article to favorites.
   * @param article - The article to add to favorites.
   */
  favoriteArticle(article: IArticle) {
    this.logger.debug('Adding article to favorites:', article);
    this.homeService.getFavoriteArticles().subscribe((response: IArticle[]) => {
      const favoriteArticle: IArticle[] = response || [];
      article.id = favoriteArticle.length + 1;
      this.homeService.addFavoriteArticle(article).subscribe((response: { article: IArticle }) => {
        // add alert to show success message
        alert('Article added to favorites');
        this.logger.debug('Article added to favorites:', response.article);
      });
    });
  }
}
