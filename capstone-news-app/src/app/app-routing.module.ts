import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CheckTokenGuard } from './utility/check-token.guard';

/**
 * Defines the routes for the application.
 */
const routes: Routes = [

  // add login route usinf loginmodule lazy loading
  {
    path: 'login',
    loadChildren: () => import('./modules/login/login.module').then(m => m.LoginModule)
  },
  // add home route using home module lazy loading-using canactivate guard checktokengaurd to protect the route 
  {
    path: 'capstone-news-app',
    loadChildren: () => import('./modules/home/home.module').then(m => m.HomeModule),
    canActivate: [CheckTokenGuard]
  },
  //add default route to login
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
