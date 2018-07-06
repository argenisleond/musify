import {AppComponent} from './app.component';
import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {artistRoutes} from '../artist/artist.routes';
import {peopleRoutes} from '../people/people.routes';

const appRoutes: Routes = [
  {
    path: '',
    component: AppComponent,
    children: [
      ...artistRoutes,
      ...peopleRoutes
    ]
  }
];

@NgModule({
  imports: [ RouterModule.forRoot(appRoutes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
