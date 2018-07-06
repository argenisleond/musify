import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {PeopleCreateComponent} from './people-create.component';
import {PeopleService} from './people.service';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  declarations: [
    PeopleCreateComponent
  ],
  providers: [
    PeopleService
  ]
})

export class PeopleModule { }
