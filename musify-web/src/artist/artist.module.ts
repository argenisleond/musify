import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {ArtistService} from './artist.service';
import {ArtistListComponent} from './artist-list.component';
import {StyleService} from '../style/style.service';
import {AngularMultiSelectModule} from '../utils/angular2-multiselect-checkbox-dropdown/src/app/angular2-multiselect-dropdown/multiselect.component';
import {ArtistCreateComponent} from './artist-create.component';
import {PeopleService} from '../people/people.service';
import {ArtistDetailComponent} from './artist-detail.component';
import {ArtistUpdateComponent} from './artist-update.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AngularMultiSelectModule
  ],
  declarations: [
    ArtistListComponent,
    ArtistDetailComponent,
    ArtistCreateComponent,
    ArtistUpdateComponent
  ],
  providers: [
    ArtistService,
    PeopleService,
    StyleService
  ]
})

export class ArtistModule { }
