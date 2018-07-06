import {Routes} from '@angular/router';
import {PeopleCreateComponent} from './people-create.component';

export const peopleRoutes: Routes = [
  {
    path: 'people/new',
    component: PeopleCreateComponent
  }
  ]
