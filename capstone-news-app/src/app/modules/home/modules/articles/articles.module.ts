import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ArticlesComponent } from './articles.component';
import { RouterModule } from '@angular/router';
import { SharedModule } from 'src/app/utility/shared.module';



@NgModule({
  declarations: [
    ArticlesComponent
  ],
  imports: [
    CommonModule,
    // add shared module
    SharedModule,
    // add router module
    RouterModule.forChild([
      {
        path: '',
        component: ArticlesComponent
      }
    ])
  ]
})
export class ArticlesModule { }
