import { Component, OnInit } from '@angular/core';
import { NGXLogger } from 'ngx-logger';
import { IArticle } from 'src/app/models/article.interface';
import { HomeService } from 'src/app/services/home.service';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss']
})
export class ArticlesComponent implements OnInit {
  selectedArticle: IArticle | null = null;
  articles: IArticle[] = [];

  constructor(private homeService: HomeService, private logger: NGXLogger) { }

  ngOnInit(): void {
    this.getArticles();
  }


  getArticles() {
    this.homeService.getLatestArticles().subscribe((response: { articles: IArticle[] }) => {
      this.articles = response.articles || [];
      this.logger.debug('Articles received:', this.articles);
    });
  }

  // add method to handle selected article showDetails
  showDetails(article: IArticle) {
    this.selectedArticle = article;
  }

  // add method to handle selected article closeDetails
  closeDetails() {
    this.selectedArticle = null;
  }

  // add method to get searhlated articles bay keyword from input text
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

  // add method to handle favorite article
  favoriteArticle(article: IArticle) {
    // get favorite article from the server and assign it to the articles array at the last index with an incremented id
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
