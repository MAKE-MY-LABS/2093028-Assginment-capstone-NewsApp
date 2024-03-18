import { TestBed, ComponentFixture } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { ArticlesComponent } from './articles.component';
import { HomeService } from 'src/app/services/home.service';
import { NGXLogger } from 'ngx-logger';

describe('ArticlesComponent', () => {
  let component: ArticlesComponent;
  let fixture: ComponentFixture<ArticlesComponent>;
  let homeService: HomeService;
  let logger: NGXLogger;
  let getLatestArticlesSpy: { and: { returnValue: (arg0: Observable<{ articles: { id: number; title: string; }[]; }>) => void; }; };


  beforeEach(() => {
    const homeServiceSpy = jasmine.createSpyObj('HomeService', ['getLatestArticles']);
    const loggerSpy = jasmine.createSpyObj('NGXLogger', ['debug']);
    getLatestArticlesSpy = homeServiceSpy.getLatestArticles.and.returnValue(of({ articles: [] }));


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
});