import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { NGXLogger } from 'ngx-logger';

@Injectable({
  providedIn: 'root'
})
export class HomeService {
  private apiUrl: string = environment.apiUrl; // Replace with your actual API URL

  constructor(private http: HttpClient, private logger: NGXLogger) { }

  // Favorite Articles CRUD operations

  getFavoriteArticles(): Observable<any> {
    this.logger.info('Getting favorite articles');
    return this.http.get(`${this.apiUrl}/favorite-articles`);
  }

  addFavoriteArticle(article: any): Observable<any> {
    this.logger.info('Adding favorite article', article);
    return this.http.post(`${this.apiUrl}/favorite-articles`, article);
  }

  updateFavoriteArticle(article: any): Observable<any> {
    this.logger.info('Updating favorite article', article);
    return this.http.put(`${this.apiUrl}/favorite-articles/${article.id}`, article);
  }

  deleteFavoriteArticle(articleId: number): Observable<any> {
    this.logger.info('Deleting favorite article', articleId);
    return this.http.delete(`${this.apiUrl}/favorite-articles/${articleId}`);
  }

  // Latest Articles
  getLatestArticles(): Observable<any> {
    this.logger.info('Getting latest articles');
    return this.http.get(`${this.apiUrl}/articles/latest`);
  }

  searchLatestArticles(keyword: string): Observable<any> {
    this.logger.info('Searching latest articles', keyword);
    return this.http.post(`${this.apiUrl}/articles/search`, keyword);
  }
}