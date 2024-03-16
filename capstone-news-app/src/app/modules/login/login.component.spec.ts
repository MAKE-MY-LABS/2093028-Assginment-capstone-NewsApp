import { TestBed, ComponentFixture } from '@angular/core/testing';
import { LoginComponent } from './login.component';
import { LoginService } from './../../services/login.service';
import { of, throwError } from 'rxjs';
import { Router } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from 'src/app/utility/shared.module';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let loginService: LoginService;
  let router: Router;

  beforeEach(() => {
    TestBed.configureTestingModule({
      // provide the component-under-test and dependent service
      declarations: [LoginComponent],
      imports: [
        ReactiveFormsModule,
        FormsModule,
        SharedModule
      ],
      providers: [
        { provide: LoginService, useValue: jasmine.createSpyObj('LoginService', ['login', 'register']) },
        { provide: Router, useValue: jasmine.createSpyObj('Router', ['navigate']) }
      ]
    });

    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    loginService = TestBed.inject(LoginService) as jasmine.SpyObj<LoginService>;
    router = TestBed.inject(Router);
  });

  it('should call login service when form is valid', () => {
    component.loginForm = {
      valid: true,
      value: {
        emailId: 'test@test.com',
        password: 'password'
      }
    } as any;

    (loginService.login as jasmine.Spy).and.returnValue(of('token'));

    component.login();

    expect(loginService.login).toHaveBeenCalledWith('test@test.com', 'password');
  });

  it('should navigate to home page on successful login', () => {
    component.loginForm = {
      valid: true,
      value: {
        emailId: 'test@test.com',
        password: 'password'
      }
    } as any;

    (loginService.login as jasmine.Spy).and.returnValue(of('token'));

    component.login();

    expect(router.navigate).toHaveBeenCalledWith(['/capstone-news-app/home/articles']);
  });

  it('should handle login error', () => {
    component.loginForm = {
      valid: true,
      value: {
        emailId: 'test@test.com',
        password: 'password'
      }
    } as any;

    (loginService.login as jasmine.Spy).and.returnValue(throwError('error'));

    component.login();

    expect(localStorage.getItem('token')).toBeNull();
  });

  it('should call register service when form is valid', () => {
    component.registerForm = {
      valid: true,
      reset: jasmine.createSpy('reset'),
      value: {
        emailId: 'test@test.com',
        password: 'password',
        userName: 'test',
        mobileNumber: '1234567890'
      }
    } as any;

    (loginService.register as jasmine.Spy).withArgs(component.registerForm.value).and.returnValue(of({}));

    component.saveUserRegistration();

    expect(loginService.register).toHaveBeenCalledWith({
      emailId: 'test@test.com',
      password: 'password',
      userName: 'test',
      mobileNumber: '1234567890'
    });
  });

  it('should handle registration error', () => {
    component.registerForm = {
      valid: true,
      reset: jasmine.createSpy('reset'),
      value: {
        emailId: 'test@test.com',
        password: 'password',
        userName: 'test',
        mobileNumber: '1234567890'
      }
    } as any;

    (loginService.register as jasmine.Spy).withArgs(component.registerForm.value).and.returnValue(throwError(() => 'error'));

    component.saveUserRegistration();

    expect(localStorage.getItem('token')).toBeNull();
  });
});