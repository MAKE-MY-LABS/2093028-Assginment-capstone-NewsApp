import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FavoriteArticlesComponent } from './favorite-articles.component';
import { RouterModule } from '@angular/router';
import { SharedModule } from 'src/app/utility/shared.module';



@NgModule({
  declarations: [
    FavoriteArticlesComponent
  ],
  imports: [
    CommonModule,
    // add shared module
    SharedModule,
    // add router module
    RouterModule.forChild([
      {
        path: '',
        component: FavoriteArticlesComponent
      }
    ])
  ]
})
export class FavoriteArticlesModule { }
