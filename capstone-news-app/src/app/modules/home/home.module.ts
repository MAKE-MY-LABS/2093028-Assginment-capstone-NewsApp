import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router'; // Import RouterModule from @angular/router
import { HomeComponent } from './home.component';
import { SharedModule } from './../../utility/shared.module';
import { HeaderComponent } from './components/header/header.component'; // Import SharedModule from the correct file

@NgModule({
  declarations: [
    HomeComponent,
    HeaderComponent
  ],
  imports: [
    CommonModule,
    SharedModule, // Add SharedModule to the imports array
    RouterModule.forChild([
      {
        path: 'home',
        component: HomeComponent,
        // lazload articles module, favorite articles module
        children: [
          {
            path: 'articles',
            loadChildren: () => import('./../home/modules/articles/articles.module').then(m => m.ArticlesModule)
          },
          {
            path: 'favorite-articles',
            loadChildren: () => import('./../home/modules/favorite-articles/favorite-articles.module').then(m => m.FavoriteArticlesModule)
          },
          // redirect to articles module
          {
            path: 'articles',
            redirectTo: 'articles',
            pathMatch: 'full'
          }
        ]

      },

    ])
  ]
})
export class HomeModule { }
