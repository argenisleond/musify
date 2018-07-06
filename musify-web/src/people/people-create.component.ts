import {Component} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import { Location } from '@angular/common';
import {NotificationsService} from 'angular2-notifications';
import {People} from './people';
import {PeopleService} from './people.service';

@Component({
  templateUrl: './people-create.component.html',
  styleUrls: ['people-create.component.css']
})

export class PeopleCreateComponent{

  public title: string = 'Incluir gente';
  public operation: string = 'Incluir';
  public people: People;

  peopleForm = this.fb.group({
    name: ['', [Validators.required, Validators.maxLength(125)]],
    years: ['', [Validators.min(12)]]
  });

  constructor(
    private fb: FormBuilder,
    private location: Location,
    private peopleService: PeopleService,
    private notificationService: NotificationsService
  ){}

  action($event){
    if(this.peopleForm.valid) {

      this.people = new People();
      this.people.name = this.peopleForm.controls['name'].value;
      this.people.years = this.peopleForm.controls['years'].value;
      this.peopleService.create(this.people).subscribe(response => {
        this.notificationService.success("Gente creada exitosamente");
        this.location.back();
      }, (err) => {
        this.notificationService.error('ERROR', 'Oops! Ha ocurrido un error');
      });
    }else{
    }
  }

  back($event){
    this.location.back();
  }
}
