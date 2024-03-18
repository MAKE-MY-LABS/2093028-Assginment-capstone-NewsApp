import { TestBed, ComponentFixture } from '@angular/core/testing';
import { of } from 'rxjs';
import { FavoriteArticlesComponent } from './favorite-articles.component';
import { HomeService } from 'src/app/services/home.service';

describe('FavoriteArticlesComponent', () => {
  let component: FavoriteArticlesComponent;
  let fixture: ComponentFixture<FavoriteArticlesComponent>;
  let homeService: HomeService;
  let getFavoriteArticlesSpy: jasmine.Spy;
  let deleteFavoriteArticleSpy: jasmine.Spy;

  beforeEach(() => {
    const homeServiceSpy = jasmine.createSpyObj('HomeService', ['getFavoriteArticles', 'deleteFavoriteArticle']);
    getFavoriteArticlesSpy = homeServiceSpy.getFavoriteArticles.and.returnValue(of([]));
    deleteFavoriteArticleSpy = homeServiceSpy.deleteFavoriteArticle.and.returnValue(of(''));

    TestBed.configureTestingModule({
      declarations: [FavoriteArticlesComponent],
      providers: [
        { provide: HomeService, useValue: homeServiceSpy }
      ]
    });

    fixture = TestBed.createComponent(FavoriteArticlesComponent);
    component = fixture.componentInstance;
    homeService = TestBed.inject(HomeService);
  });

  it('should get favorite articles on init', () => {
    const favoriteArticles = [{ id: 1, title: 'Test Article' }];
    getFavoriteArticlesSpy.and.returnValue(of(favoriteArticles));

    component.ngOnInit();

    expect(homeService.getFavoriteArticles).toHaveBeenCalled();
    expect(component.favoriteArticles).toEqual(favoriteArticles);
  });

  it('should delete favorite article', () => {
    const favoriteArticles = [{ id: 1, title: 'Test Article' }];
    deleteFavoriteArticleSpy.and.returnValue(of(favoriteArticles));
    component.ngOnInit();
    component.deleteArticle(favoriteArticles[0]);
    expect(homeService.deleteFavoriteArticle).toHaveBeenCalled();
  });

  it('should show details', () => {
    const article: any = { id: 1, title: 'Test Article' };
    component.showDetails(article);
    expect(component.selectedArticle).toEqual(article);
  });

  it('should close details', () => {
    component.closeDetails();
    expect(component.selectedArticle).toBeNull();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});