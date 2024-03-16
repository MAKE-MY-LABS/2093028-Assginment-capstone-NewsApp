import { TestBed, ComponentFixture } from '@angular/core/testing';
import { HeaderComponent } from './header.component';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';

describe('HeaderComponent', () => {
  let component: HeaderComponent;
  let fixture: ComponentFixture<HeaderComponent>;
  let router: Router;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule],
      declarations: [HeaderComponent]
    });

    fixture = TestBed.createComponent(HeaderComponent);
    component = fixture.componentInstance;
    router = TestBed.inject(Router);
  });

  it('should call localStorage.removeItem and router.navigate when logout is called', () => {
    const removeItemSpy = spyOn(localStorage, 'removeItem');
    const navigateSpy = spyOn(router, 'navigate');

    component.logout();

    expect(removeItemSpy).toHaveBeenCalledWith('token');
    expect(navigateSpy).toHaveBeenCalledWith(['/login']);
  });
});