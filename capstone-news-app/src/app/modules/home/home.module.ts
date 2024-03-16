import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router'; // Import RouterModule from @angular/router
import { HomeComponent } from './home.component';
import { SharedModule } from './../../utility/shared.module'; // Import SharedModule from the correct file

@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    CommonModule,
    SharedModule, // Add SharedModule to the imports array
    RouterModule.forChild([
      {
        path: '',
        component: HomeComponent
      }
    ])
  ]
})
export class HomeModule { }
