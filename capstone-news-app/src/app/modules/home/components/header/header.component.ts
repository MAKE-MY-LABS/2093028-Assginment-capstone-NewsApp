import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(private router: Router) { }


  ngOnInit(): void {
  }
  //logout function to clear token from local storage
  logout() {
    // redirect to login page using router
    // navigate to login page
    // Example:
    // import { Router } from '@angular/router';
    // constructor(private router: Router) { }
    // logout() {
    //   localStorage.removeItem('token');
    //   this.router.navigate(['/login']);
    // }
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }

}
