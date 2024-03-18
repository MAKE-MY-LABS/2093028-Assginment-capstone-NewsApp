import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController, TestRequest } from '@angular/common/http/testing';
import { HTTP_INTERCEPTORS, HttpClient } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { Router } from '@angular/router';
import { GlobalInterceptor } from './global.interceptor';
import { Component } from '@angular/core';

@Component({ template: '' })
class DummyComponent { }


describe('GlobalInterceptor', () => {
  let httpMock: HttpTestingController;
  let router: Router;
  let httpClient: HttpClient;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule, RouterTestingModule.withRoutes([
        { path: 'login', component: DummyComponent }
      ])],
      providers: [
        { provide: HTTP_INTERCEPTORS, useClass: GlobalInterceptor, multi: true }
      ],
      declarations: [DummyComponent]
    });

    httpMock = TestBed.inject(HttpTestingController);
    router = TestBed.inject(Router);
    httpClient = TestBed.inject(HttpClient);
  });

  afterEach(() => {
    httpMock.verify(); // Make sure that there are no outstanding requests
  });

  it('should add Authorization header when token is present', () => {
    localStorage.setItem('token', 'test-token');

    httpClient.get('/articles').subscribe();

    const req = httpMock.expectOne('/articles');
    req.flush({});

    expect(req.request.headers.has('Authorization')).toBeTruthy();
    expect(req.request.headers.get('Authorization')).toBe('Bearer test-token');
  });

  it('should navigate to login when token is not present', () => {
    localStorage.removeItem('token');

    /**
     * Spy on the `navigate` method of the router.
     */
    const navigateSpy = spyOn(router, 'navigate');

    httpClient.get('/articles').subscribe();

    const req = httpMock.expectOne('/articles');
    req.flush({});

    expect(navigateSpy).toHaveBeenCalledWith(['/login']);
  });
});