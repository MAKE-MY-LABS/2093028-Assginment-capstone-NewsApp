import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from './../../environments/environment';
import { NGXLogger } from 'ngx-logger';


@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private loginUrl = `${environment.apiUrl}user/login`;

  constructor(private http: HttpClient, private logger: NGXLogger) { }

  /**
   * Logs in the user with the provided email and password.
   * @param emailId - The email address of the user.
   * @param password - The password of the user.
   * @returns An Observable that emits the response from the login request.
   */
  login(emailId: string, password: string): Observable<any> {
    const body = { emailId, password };
    this.logger.debug('Attempting to login with email:', emailId);
    const obj: any = {
      responseType: 'text'
    }
    return this.http.post<any>(this.loginUrl, body, obj);
  }

  // add register method using user/save API
  /**
   * Registers a user.
   * 
   * @param user - The user object containing the registration details.
   * @returns An Observable that emits the response from the server.
   */
  register(user: any): Observable<any> {
    this.logger.debug('Attempting to register with email:', user.emailId);
    const obj: any = {
      responseType: 'text'
    }
    return this.http.post(`${environment.apiUrl}user/save`, user, obj);
  }
}