import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AppRoutingModule} from './app-routing.module';
import {ArtistModule} from '../artist/artist.module';
import {SimpleNotificationsModule} from 'angular2-notifications';
import {PeopleModule} from '../people/people.module';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ArtistModule,
    PeopleModule,
    BrowserAnimationsModule,
    SimpleNotificationsModule.forRoot()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
