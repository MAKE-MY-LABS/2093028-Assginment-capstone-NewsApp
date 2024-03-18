import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
/**
 * Represents the HeaderComponent of the application.
 */
export class HeaderComponent implements OnInit {

  constructor(private router: Router) { }

  /**
   * Lifecycle hook that is called after data-bound properties of a directive are initialized.
   */
  ngOnInit(): void {
  }

  /**
   * Clears the token from local storage and navigates to the login page.
   */
  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }
}
