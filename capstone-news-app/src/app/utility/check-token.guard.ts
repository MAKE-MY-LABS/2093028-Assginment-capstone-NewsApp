import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CheckTokenGuard implements CanActivate {
  /**
   * Determines if the user can activate a particular route.
   * @param route - The activated route snapshot.
   * @param state - The router state snapshot.
   * @returns An observable, promise, or boolean indicating if the user can activate the route.
   */
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const token = localStorage.getItem('token');
    if (!token) {
      return false;
    }
    return true;
  }

}
