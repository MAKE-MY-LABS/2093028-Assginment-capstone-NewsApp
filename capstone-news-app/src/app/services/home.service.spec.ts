import { TestBed } from '@angular/core/testing';
import { HomeService } from './home.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { environment } from 'src/environments/environment';
import { LoggerModule, NGXLogger, NgxLoggerLevel } from 'ngx-logger';
import { SharedModule } from '../utility/shared.module';
import { of } from 'rxjs';

describe('HomeService', () => {
  let service: HomeService;
  let homeServiceSpy: jasmine.SpyObj<HomeService>;
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
    homeServiceSpy = jasmine.createSpyObj('HomeService', ['getFavoriteArticles', 'addFavoriteArticle', 'deleteFavoriteArticle', 'getLatestArticles', 'searchLatestArticles']);
    const loggerSpy = jasmine.createSpyObj('NGXLogger', ['debug']);

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, SharedModule],
      providers: [
        { provide: HomeService, useValue: homeServiceSpy },
        { provide: NGXLogger, useValue: loggerSpy }
      ]
    });

    service = TestBed.inject(HomeService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify(); // Make sure that there are no outstanding requests
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should get favorite articles', () => {
    homeServiceSpy.getFavoriteArticles.and.returnValue(of([article]));

    service.getFavoriteArticles().subscribe((response: any) => {
      expect(response).toEqual([article]);
    });

    expect(homeServiceSpy.getFavoriteArticles).toHaveBeenCalled();

  });

  it('should delete favorite article', () => {
    homeServiceSpy.deleteFavoriteArticle.and.returnValue(of(""));

    service.deleteFavoriteArticle(1).subscribe((response: any) => {
      expect(response).toEqual("");
    });

    expect(homeServiceSpy.deleteFavoriteArticle).toHaveBeenCalled();
  });

  it('should add favorite article', () => {
    homeServiceSpy.addFavoriteArticle.and.returnValue(of({ article }));

    service.addFavoriteArticle(article).subscribe((response: any) => {
      expect(response).toEqual({ article });
    });

    expect(homeServiceSpy.addFavoriteArticle).toHaveBeenCalled();
  });

  it('should get latest articles', () => {
    homeServiceSpy.getLatestArticles.and.returnValue(of({ articles: [article] }));

    service.getLatestArticles().subscribe((response: any) => {
      expect(response).toEqual({ articles: [article] });
    });

    expect(homeServiceSpy.getLatestArticles).toHaveBeenCalled();
  });

  it('should search latest articles', () => {
    homeServiceSpy.searchLatestArticles.and.returnValue(of({ articles: [article] }));

    service.searchLatestArticles('tesla').subscribe((response: any) => {
      expect(response).toEqual({ articles: [article] });
    });

    expect(homeServiceSpy.searchLatestArticles).toHaveBeenCalled();
  });

});