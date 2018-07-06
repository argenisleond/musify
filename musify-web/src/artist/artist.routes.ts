import {Routes} from '@angular/router';
import {ArtistListComponent} from './artist-list.component';
import {ArtistCreateComponent} from './artist-create.component';
import {ArtistDetailComponent} from './artist-detail.component';
import {ArtistUpdateComponent} from './artist-update.component';

export const artistRoutes: Routes = [
  {
   path: '',
   pathMatch: 'full',
   redirectTo: 'artists'
  },
  {
    path: 'artists',
    pathMatch: 'full',
    component: ArtistListComponent,
  },
  {
    path: 'artists/new',
    component: ArtistCreateComponent
  },
  {
    path: 'artists/:artistID',
    component: ArtistDetailComponent,
  },
  {
    path: 'artists/:artistID/update',
    component: ArtistUpdateComponent
  }
  ]

