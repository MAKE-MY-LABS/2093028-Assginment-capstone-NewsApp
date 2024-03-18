import { TestBed, ComponentFixture, tick } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { ArticlesComponent } from './articles.component';
import { HomeService } from 'src/app/services/home.service';
import { NGXLogger } from 'ngx-logger';
import { IArticle } from 'src/app/models/article.interface';

describe('ArticlesComponent', () => {
  let component: ArticlesComponent;
  let fixture: ComponentFixture<ArticlesComponent>;
  let homeService: HomeService;
  let logger: NGXLogger;
  let getLatestArticlesSpy: { and: { returnValue: (arg0: Observable<{ articles: { id: number; title: string; }[]; }>) => void; }; };
  let article: IArticle | null = { id: 1, description: "", title: 'Test Article', content: 'Test Content', author: 'Test Author', publishedAt: new Date().toISOString(), source: { id: '1', name: 'Test Source' }, url: 'http://test.com', urlToImage: 'http://test.com/image.jpg' };
  let searchLatestArticlesSpy: { and: { returnValue: (arg0: Observable<{ articles: any[]; }>) => void; }; };
  let getFavoriteArticlesSpy: { and: { returnValue: (arg0: Observable<any[]>) => void; }; };
  let addFavoriteArticleSpy: { and: { returnValue: (arg0: Observable<{ article: IArticle; }>) => void; }; };

  beforeEach(() => {
    article = { id: 1, description: "", title: 'Test Article', content: 'Test Content', author: 'Test Author', publishedAt: new Date().toISOString(), source: { id: '1', name: 'Test Source' }, url: 'http://test.com', urlToImage: 'http://test.com/image.jpg' };

    const homeServiceSpy = jasmine.createSpyObj('HomeService', ['getLatestArticles', 'searchLatestArticles', 'getFavoriteArticles', 'addFavoriteArticle']);
    const loggerSpy = jasmine.createSpyObj('NGXLogger', ['debug']);
    getLatestArticlesSpy = homeServiceSpy.getLatestArticles.and.returnValue(of({ articles: [] }));
    searchLatestArticlesSpy = homeServiceSpy.searchLatestArticles.and.returnValue(of({ articles: [] }));
    getFavoriteArticlesSpy = homeServiceSpy.getFavoriteArticles.and.returnValue(of([]));
    addFavoriteArticleSpy = homeServiceSpy.addFavoriteArticle.and.returnValue(of({ article: article }));

    TestBed.configureTestingModule({
      declarations: [ArticlesComponent],
      providers: [
        { provide: HomeService, useValue: homeServiceSpy },
        { provide: NGXLogger, useValue: loggerSpy }
      ]
    });

    fixture = TestBed.createComponent(ArticlesComponent);
    component = fixture.componentInstance;
    homeService = TestBed.inject(HomeService);
    logger = TestBed.inject(NGXLogger);
  });

  it('should get articles on init', () => {
    const articles = [{ id: 1, title: 'Test Article' }];
    getLatestArticlesSpy.and.returnValue(of({ articles }));

    component.ngOnInit();

    expect(homeService.getLatestArticles).toHaveBeenCalled();
    expect(logger.debug).toHaveBeenCalledWith('Articles received:', articles);
    expect(component.articles).toEqual(jasmine.arrayContaining(articles));
  });

  it('should show details', () => {
    if (article) {
      component.showDetails(article);
      expect(component.selectedArticle).toEqual(article);
    }
  });

  it('should close details', () => {
    component.selectedArticle = article;
    component.closeDetails();
    expect(component.selectedArticle).toBeNull();
  });

  it('should search articles', (done) => {
    const articles: any = [article];
    const keyword = 'tesla';
    searchLatestArticlesSpy.and.returnValue(of({ articles }));

    component.searchArticles(keyword);

    // Assuming that component.articles is updated inside searchArticles method
    setTimeout(() => {
      expect(homeService.searchLatestArticles).toHaveBeenCalledWith(keyword);
      expect(logger.debug).toHaveBeenCalledWith('Searching articles with keyword:', keyword);
      expect(logger.debug).toHaveBeenCalledWith('Articles received:', articles);
      expect(component.articles).toEqual(jasmine.arrayContaining(articles));
      done();
    }, 0);
  });

  it('should get all articles when keyword is empty', () => {
    const articles: any = [article];
    const keyword = '';
    getLatestArticlesSpy.and.returnValue(of({ articles }));

    component.searchArticles(keyword);

    expect(homeService.getLatestArticles).toHaveBeenCalled();
    expect(logger.debug).toHaveBeenCalledWith('Keyword is empty, getting all articles');
    expect(logger.debug).toHaveBeenCalledWith('Articles received:', articles);
    expect(component.articles).toEqual(jasmine.arrayContaining(articles));
  });

  it('should favorite article', (done) => {
    const favoriteArticle: any = article;
    const favoriteArticles: any = [favoriteArticle];
    getFavoriteArticlesSpy.and.returnValue(of(favoriteArticles));
    addFavoriteArticleSpy.and.returnValue(of({ article: favoriteArticle }));

    if (article) {
      component.favoriteArticle(article);
    }

    // Assuming that component.articles is updated inside favoriteArticle method
    setTimeout(() => {
      expect(homeService.getFavoriteArticles).toHaveBeenCalled();
      expect(homeService.addFavoriteArticle).toHaveBeenCalledWith(article);
      expect(logger.debug).toHaveBeenCalledWith('Adding article to favorites:', article);
      expect(logger.debug).toHaveBeenCalledWith('Article added to favorites:', favoriteArticle);
      done();
    }, 0);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});