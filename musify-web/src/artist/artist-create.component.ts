import {Component} from '@angular/core';
import {Artist} from './artist';
import {FormBuilder, Validators} from '@angular/forms';
import {StyleService} from '../style/style.service';
import {ArtistService} from './artist.service';
import {PeopleService} from '../people/people.service';
import {Style} from '../style/style';
import {People} from '../people/people';
import { Location } from '@angular/common';
import {NotificationsService} from 'angular2-notifications';

@Component({
  templateUrl: './artist-create.component.html',
  styleUrls: ['artist-create.component.css']
})

export class ArtistCreateComponent{

  public title: string = 'Incluir artista';
  public operation: string = 'Incluir';
  public flag = false;
  public artist: Artist;
  public allArtists: Artist[];
  public allStyles: Style[];
  public allPeople: People[];
  stylesList = [];
  selectedStyles = [];
  styleDropdownSettings = {
    singleSelection: false,
    text:"Seleccionar estilos",
    enableSearchFilter: true,
    enableCheckAll: true};
  artistList = [];
  selectedArtists = [];
  artistDropdownSettings = {
    singleSelection: false,
    text:"Seleccionar artistas relacionados",
    enableSearchFilter: true,
    enableCheckAll: true};
  peopleList = [];
  selectedPeople = [];
  peopleDropdownSettings = {
    singleSelection: false,
    text:"Seleccionar miembros",
    enableSearchFilter: true,
    enableCheckAll: true};

  artistForm = this.fb.group({
    name: ['', [Validators.required, Validators.maxLength(125)]],
    year: ['', [Validators.min(12)]]
  });

  constructor(
    private fb: FormBuilder,
    private location: Location,
    private peopleService: PeopleService,
    private styleService: StyleService,
    private artistService: ArtistService,
    private notificationService: NotificationsService
  ){}

  ngOnInit(){

    this.styleService.getAll().subscribe(res => {
        this.allStyles = <Style[]> res;

        for(let style of this.allStyles) {
          let item = {};
          item['id'] = style.id;
          item['itemName'] = style.name;
          this.stylesList.push(item);
        }
    }, (err) => {
      this.notificationService.error('ERROR', 'Oops! Ha ocurrido un error');
    });

    this.peopleService.getAll().subscribe(res => {
      this.allPeople = res;

      for(let people of this.allPeople) {
        let item = {};
        item['id'] = people.id;
        item['itemName'] = people.name;
        this.peopleList.push(item);
      }
    }, (err) => {
      this.notificationService.error('ERROR', 'Oops! Ha ocurrido un error');
    });

    this.artistService.getAll().subscribe(res => {
      this.allArtists = res;

      for(let art of this.allArtists) {
        let item = {};
        item['id'] = art.id;
        item['itemName'] = art.name;
        this.artistList.push(item);
      }
    }, (err) => {
      this.notificationService.error('ERROR', 'Oops! Ha ocurrido un error');
    });

    this.flag = true;

  }

  buildObject(list: any[]): any[] {
    let res = [];
    for(let item of list){
      let obj = {};
      obj['id'] = item.id;
      res.push(obj);
    }
    return res;
  }

  action($event){
    if(this.artistForm.valid) {

      this.artist = new Artist();
      this.artist.name = this.artistForm.controls['name'].value;
      this.artist.year = this.artistForm.controls['year'].value;
      this.artist.members = this.buildObject(this.selectedPeople);
      this.artist.styles = this.buildObject(this.selectedStyles);
      this.artist.related = this.buildObject(this.selectedArtists);
      this.artistService.create(this.artist).subscribe(response => {
        this.notificationService.success("Artista incluido exitosamente");
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
