
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { LoginService } from './../../services/login.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup = new FormGroup({});

  registerForm: FormGroup = new FormGroup({});

  constructor(
    private formBuilder: FormBuilder,
    private loginService: LoginService,
    // add router dependency
    private router: Router
  ) { }

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


  login() {

    if (this.loginForm.valid) {
      const email = this.loginForm.value.emailId;
      const password = this.loginForm.value.password;
      this.loginService.login(email, password).subscribe({
        next: (response) => {
          // Handle successful login
          // alert user login successful
          // store token in local storage
          localStorage.setItem('token', response);
          alert('Login successful');
          // add navigation to home page
          this.router.navigate(['/home']);

        },
        error: (error) => {
          // Handle login error
          // alert user login failed
          alert('Login failed');
          //clear local storage
          localStorage.removeItem('token');
        }
      });
    }
  }

  saveUserRegistration() {
    if (this.registerForm.valid) {
      const user = this.registerForm.value;

      this.loginService.register(user).subscribe({
        next: (response: any) => {
          // Handle successful registration
          // alert user registration successful
          // store token in local storage
          alert('Registration successful');
          //clear form registration form
          this.registerForm.reset();
        },
        error: (error: any) => {
          // Handle registration error
          // alert user registration failed
          alert('Registration failed');
          //clear local storage
          localStorage.removeItem('token');
        }
      });
    }
  }

}
