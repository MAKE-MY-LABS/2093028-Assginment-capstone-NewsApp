
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { LoginService } from './../../services/login.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NGXLogger } from 'ngx-logger';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
/**
 * Represents the LoginComponent class.
 * This component handles the login and registration functionality.
 */
export class LoginComponent implements OnInit {
  loginForm: FormGroup = new FormGroup({});

  registerForm: FormGroup = new FormGroup({});

  constructor(
    private formBuilder: FormBuilder,
    private loginService: LoginService,
    // add router dependency
    private router: Router,
    private logger: NGXLogger  // inject NGXLogger
  ) { }

  /**
   * Initializes the component.
   * Sets up the login and registration forms.
   */
  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      emailId: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });

    this.registerForm = this.formBuilder.group({
      emailId: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      username: ['', Validators.required],
      mobileNumber: ['', Validators.required]
    });
  }

  /**
   * Handles the login functionality.
   * If the login form is valid, it sends a login request to the server.
   * If the login is successful, it stores the token in local storage and navigates to the home page.
   * If there is an error, it displays an alert and logs the error.
   */
  login() {
    if (this.loginForm.valid) {
      const email = this.loginForm.value.emailId;
      const password = this.loginForm.value.password;
      this.loginService.login(email, password).subscribe({
        next: (response: string) => {
          // Handle successful login
          // alert user login successful
          // store token in local storage
          localStorage.setItem('token', response);
          this.logger.info('Login successful');  // log info message
          // add navigation to home page
          this.router.navigate(['/capstone-news-app/home/articles']);
          alert('Login successful');
        },
        error: (error: any) => {
          // Handle login error
          // alert user login failed
          alert('Login failed');
          this.logger.error('Login failed', error);  // log error message
          //clear local storage
          localStorage.removeItem('token');
        }
      });
    }
  }

  /**
   * Handles the user registration functionality.
   * If the registration form is valid, it sends a registration request to the server.
   * If the registration is successful, it displays an alert and logs the success message.
   * If there is an error, it displays an alert, logs the error, and clears the local storage.
   */
  saveUserRegistration() {
    if (this.registerForm.valid) {
      const user = this.registerForm.value;

      this.loginService.register(user).subscribe({
        next: (response: any) => {
          // Handle successful registration
          // alert user registration successful
          // store token in local storage
          alert('Registration successful');
          this.logger.info('Registration successful');  // log info message
          //clear form registration form
          this.registerForm.reset();
        },
        error: (error: any) => {
          // Handle registration error
          // alert user registration failed
          alert('Registration failed');
          this.logger.error('Registration failed', error);  // log error message
          //clear local storage
          localStorage.removeItem('token');
        }
      });
    }
  }
}
