import { Component, OnInit } from '@angular/core';
import { IArticle } from 'src/app/models/article.interface';
import { HomeService } from 'src/app/services/home.service';

@Component({
  selector: 'app-favorite-articles',
  templateUrl: './favorite-articles.component.html',
  styleUrls: ['./favorite-articles.component.scss']
})
export class FavoriteArticlesComponent implements OnInit {
  selectedArticle: IArticle | null = null;
  favoriteArticles: any[] = [];

  constructor(
    // use the HomeService to fetch favorite articles
    private homeService: HomeService
  ) { }

  ngOnInit(): void {
    this.getFavoriteArticles();
  }


  // add method to handle selected article showDetails
  showDetails(article: IArticle) {
    this.selectedArticle = article;
  }

  // add method to handle selected article closeDetails
  closeDetails() {
    this.selectedArticle = null;
  }

  getFavoriteArticles(): void {
    // Code to fetch favorite articles from the server
    // Assign the fetched articles to the favoriteArticles array
    this.homeService.getFavoriteArticles().subscribe((response: any) => {
      this.favoriteArticles = response || [];
    });
  }

  deleteArticle(article: any): void {
    // Code to delete the article from the favoriteArticles array
    // Display an alert message to inform the user that the article has been deleted
    this.homeService.deleteFavoriteArticle(article.id).subscribe((response: any) => {
      this.getFavoriteArticles();
      alert('Article removed from favorites');
    });
  }

}
