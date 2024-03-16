import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router'; // Import RouterModule from @angular/router
import { LoginComponent } from './login.component';
import { SharedModule } from './../../utility/shared.module'; // Import SharedModule from the correct path

@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    CommonModule,
    SharedModule, // Add SharedModule to the imports array
    RouterModule.forChild([
      {
        path: '',
        component: LoginComponent
      }
    ])
  ]
})
export class LoginModule { }
