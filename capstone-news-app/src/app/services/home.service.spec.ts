import { TestBed } from '@angular/core/testing';
import { HomeService } from './home.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { environment } from 'src/environments/environment';
import { LoggerModule, NgxLoggerLevel } from 'ngx-logger';
import { SharedModule } from '../utility/shared.module';

describe('HomeService', () => {
  let service: HomeService;
  let httpMock: HttpTestingController;
  let article = {
    "id": 1,
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
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, SharedModule],
      providers: [HomeService]
    });

    service = TestBed.inject(HomeService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify(); // Make sure that there are no outstanding requests
  });

  it('should call http.get with correct URL when getFavoriteArticles is called', () => {
    service.getFavoriteArticles().subscribe();

    const req = httpMock.expectOne(`${environment.apiUrl}/favorite-articles`);
    expect(req.request.method).toBe('GET');
  });

  it('should call http.post with correct URL and data when addFavoriteArticle is called', () => {
    service.addFavoriteArticle(article).subscribe();

    const req = httpMock.expectOne(`${environment.apiUrl}/favorite-articles`);
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(article);
  });

  it('should call http.put with correct URL and data when updateFavoriteArticle is called', () => {
    service.updateFavoriteArticle(article).subscribe();

    const req = httpMock.expectOne(`${environment.apiUrl}/favorite-articles/${article.id}`);
    expect(req.request.method).toBe('PUT');
    expect(req.request.body).toEqual(article);
  });

  it('should call http.delete with correct URL when deleteFavoriteArticle is called', () => {
    const articleId = 1;
    service.deleteFavoriteArticle(articleId).subscribe();

    const req = httpMock.expectOne(`${environment.apiUrl}/favorite-articles/${articleId}`);
    expect(req.request.method).toBe('DELETE');
  });

  it('should call http.get with correct URL when getLatestArticles is called', () => {
    service.getLatestArticles().subscribe();

    const req = httpMock.expectOne(`${environment.apiUrl}/articles/latest`);
    expect(req.request.method).toBe('GET');
  });

  it('should call http.post with correct URL and data when searchLatestArticles is called', () => {
    const keyword = 'USA';

    service.searchLatestArticles(keyword).subscribe();

    const req = httpMock.expectOne(`${environment.apiUrl}/articles/search`);
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(keyword);
  });
});