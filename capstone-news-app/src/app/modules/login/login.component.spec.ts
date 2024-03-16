import { ComponentFixture, TestBed } from "@angular/core/testing";
import { ReactiveFormsModule } from "@angular/forms";
import { Router } from "@angular/router";
import { async, of, throwError } from "rxjs";
import { LoginService } from "src/app/services/login.service";
import { LoginComponent } from "./login.component";

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let loginService: LoginService;
  let router: Router;
  let loginSpy: jasmine.Spy;
  let registerSpy: jasmine.Spy;

  beforeEach(async () => {
    TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      providers: [
        { provide: LoginService, useValue: jasmine.createSpyObj('LoginService', ['login', 'register']) },
        { provide: Router, useValue: jasmine.createSpyObj('Router', ['navigate']) }
      ],
      imports: [ ReactiveFormsModule ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    loginService = TestBed.inject(LoginService);
    router = TestBed.inject(Router);
    loginSpy = spyOn(loginService, 'login');
    registerSpy = spyOn(loginService, 'register');
    fixture.detectChanges();
  });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

  it('should call the login service and navigate to home on successful login', () => {
    const response = 'token';
    loginSpy.and.returnValue(of(response));
    const routerSpy = spyOn(router, 'navigate');
    component.loginForm.setValue({ emailId: 'test@test.com', password: 'password' });
    component.login();
    expect(loginSpy).toHaveBeenCalledWith('test@test.com', 'password');
    expect(localStorage.getItem('token')).toEqual(response);
    expect(routerSpy).toHaveBeenCalledWith(['/home']);
  });

  it('should alert and clear token on login error', () => {
    const response = 'error';
    loginSpy.and.returnValue(throwError(() => response));
    const alertSpy = spyOn(window, 'alert');
    component.loginForm.setValue({ emailId: 'test@test.com', password: 'password' });
    component.login();
    expect(alertSpy).toHaveBeenCalledWith('Login failed');
    expect(localStorage.getItem('token')).toBeNull();
  });

  it('should call the register service and reset form on successful registration', () => {
    const response = 'success';
    registerSpy.and.returnValue(of(response));
    const user = { emailId: 'test@test.com', password: 'password', username: 'username', mobileNumber: '1234567890' };
    component.registerForm.setValue(user);
    component.saveUserRegistration();
    expect(registerSpy).toHaveBeenCalledWith(user);
    expect(component.registerForm.value).toEqual({ emailId: null, password: null, username: null, mobileNumber: null });
  });

  it('should alert and clear token on registration error', () => {
    const response = 'error';
    registerSpy.and.returnValue(throwError(response));
    const alertSpy = spyOn(window, 'alert');
    const user = { emailId: 'test@test.com', password: 'password', username: 'username', mobileNumber: '1234567890' };
    component.registerForm.setValue(user);
    component.saveUserRegistration();
    expect(alertSpy).toHaveBeenCalledWith('Registration failed');
    expect(localStorage.getItem('token')).toBeNull();
  });
});