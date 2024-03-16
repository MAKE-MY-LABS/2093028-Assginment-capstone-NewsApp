import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { NGXLogger } from 'ngx-logger';
import { LoginService } from './login.service';
import { environment } from '../../environments/environment';

describe('LoginService', () => {
  let service: LoginService;
  let httpMock: HttpTestingController;
  let loggerSpy: jasmine.SpyObj<NGXLogger>;

  beforeEach(() => {
    const spy = jasmine.createSpyObj('NGXLogger', ['debug']);

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        LoginService,
        { provide: NGXLogger, useValue: spy }
      ]
    });

    service = TestBed.inject(LoginService);
    httpMock = TestBed.inject(HttpTestingController);
    loggerSpy = TestBed.inject(NGXLogger) as jasmine.SpyObj<NGXLogger>;
  });

  afterEach(() => {
    httpMock.verify(); // Ensure that there are no outstanding requests
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should call the login API and return the result', () => {
    const mockResponse = '12345';
    const emailId = 'test@example.com';
    const password = 'password';

    service.login(emailId, password).subscribe(response => {
      expect(response).toEqual(mockResponse);
    });

    const req = httpMock.expectOne(`${environment.apiUrl}user/login`);
    expect(req.request.method).toBe('POST');
    req.flush(mockResponse);

    expect(loggerSpy.debug.calls.count()).toBe(1, 'spy method was called once');
    expect(loggerSpy.debug.calls.first().args[1]).toBe(emailId, 'spy method was called with right argument');
  });

  it('should call http.post with correct URL and data when register is called', () => {
    const user = {
      emailId: 'test@test.com',
      password: 'password'
    };

    service.register(user).subscribe();

    const req = httpMock.expectOne(`${environment.apiUrl}user/save`);
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(user);
    expect(req.request.responseType).toBe('text');
  });

});
