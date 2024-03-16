import { TestBed } from '@angular/core/testing';
import { CheckTokenGuard } from './check-token.guard';
import { ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

describe('CheckTokenGuard', () => {
  let guard: CheckTokenGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(CheckTokenGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });

  it('should return false when there is no token', () => {
    const route = {} as ActivatedRouteSnapshot;
    const state = {} as RouterStateSnapshot;
    spyOn(localStorage, 'getItem').and.returnValue(null);
    expect(guard.canActivate(route, state)).toBe(false);
  });

  it('should return true when there is a token', () => {
    const route = {} as ActivatedRouteSnapshot;
    const state = {} as RouterStateSnapshot;
    spyOn(localStorage, 'getItem').and.returnValue('token');
    expect(guard.canActivate(route, state)).toBe(true);
  });
});