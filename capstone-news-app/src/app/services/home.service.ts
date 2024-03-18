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

  /**
   * Retrieves the favorite articles.
   * @returns An Observable that emits the favorite articles.
   */
  getFavoriteArticles(): Observable<any> {
    this.logger.info('Getting favorite articles');
    return this.http.get(`${this.apiUrl}favorite-articles`);
  }

  /**
   * Adds a favorite article.
   * @param article - The article to be added as a favorite.
   * @returns An Observable that emits the response from the API.
   */
  addFavoriteArticle(article: any): Observable<any> {
    this.logger.info('Adding favorite article', article);
    return this.http.post(`${this.apiUrl}favorite-articles`, article);
  }

  /**
   * Deletes a favorite article.
   * @param articleId - The ID of the article to be deleted.
   * @returns An Observable that emits the response from the API.
   */
  deleteFavoriteArticle(articleId: number): Observable<any> {
    this.logger.info('Deleting favorite article', articleId);
    return this.http.delete(`${this.apiUrl}favorite-articles/${articleId}`, { responseType: 'text' });
  }

  /**
   * Retrieves the latest articles.
   * @returns An Observable that emits the latest articles.
   */
  getLatestArticles(): Observable<any> {
    this.logger.info('Getting latest articles');
    return this.http.get(`${this.apiUrl}articles/latest`);
  }

  /**
   * Searches for the latest articles based on a keyword.
   * @param keyword - The keyword to search for.
   * @returns An Observable that emits the search results.
   */
  searchLatestArticles(keyword: string): Observable<any> {
    this.logger.info('Searching latest articles', keyword);
    return this.http.post(`${this.apiUrl}articles/search`, keyword);
  }
}