import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Injectable()
export class GlobalInterceptor implements HttpInterceptor {

  constructor(
    private router: Router
  ) { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const token = localStorage.getItem('token');
    if (token) {
      // Token is present, add it to the request headers
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
          // include other headers if needed for cors preflight
          'Access-Control-Allow-Origin': '*',
          'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
          'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token'
        }
      });
    } else {
      // Token is not present, navigate to login
      // Assuming you have imported the Router module
      // and injected it into the constructor of this class
      this.router.navigate(['/login']);
    }

    return next.handle(request);
  }
}
