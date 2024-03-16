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

  login(emailId: string, password: string): Observable<any> {
    const body = { emailId, password };
    this.logger.debug('Attempting to login with email:', emailId);
    const obj: any =  {
      responseType: 'text'  
    }
    return this.http.post<any>(this.loginUrl, body, obj);
  }

  // add register method using user/save API
  register(user: any): Observable<any> {
    this.logger.debug('Attempting to register with email:', user.emailId);
    const obj: any =  {
      responseType: 'text'  
    }
    return this.http.post(`${environment.apiUrl}user/save`, user,obj);
  }
}