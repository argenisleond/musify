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
import {ActivatedRoute} from '@angular/router';

@Component({
  templateUrl: './artist-create.component.html',
  styleUrls: ['artist-create.component.css']
})

export class ArtistUpdateComponent {

  public title = 'Actualizar artista';
  public operation = 'Actualizar';
  public flag = false;
  public artist: Artist;
  public allArtists: Artist[];
  public allStyles: Style[];
  public allPeople: People[];
  stylesList = [];
  selectedStyles = [];
  styleDropdownSettings = {
    singleSelection: false,
    text: 'Seleccionar estilos',
    enableSearchFilter: true,
    enableCheckAll: true};
  artistList = [];
  selectedArtists = [];
  artistDropdownSettings = {
    singleSelection: false,
    text: 'Seleccionar artistas relacionados',
    enableSearchFilter: true,
    enableCheckAll: true};
  peopleList = [];
  selectedPeople = [];
  peopleDropdownSettings = {
    singleSelection: false,
    text: 'Seleccionar miembros',
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
    private activatedRouteService: ActivatedRoute,
    private notificationService: NotificationsService
  ) {}

  ngOnInit() {

    this.styleService.getAll().subscribe(res => {
      this.allStyles = <Style[]> res;

      for (const style of this.allStyles) {
        const item = {};
        item['id'] = style.id;
        item['itemName'] = style.name;
        this.stylesList.push(item);
      }
    }, (err) => {
      this.notificationService.error('ERROR', 'Oops! Ha ocurrido un error');
    });

    this.peopleService.getAll().subscribe(res => {
      this.allPeople = res;

      for (const people of this.allPeople) {
        const item = {};
        item['id'] = people.id;
        item['itemName'] = people.name;
        this.peopleList.push(item);
      }
    }, (err) => {
      this.notificationService.error('ERROR', 'Oops! Ha ocurrido un error');
    });

    this.artistService.getAll().subscribe(res => {
      this.allArtists = res;

      for (const art of this.allArtists) {
        const item = {};
        item['id'] = art.id;
        item['itemName'] = art.name;
        this.artistList.push(item);
      }
    }, (err) => {
      this.notificationService.error('ERROR', 'Oops! Ha ocurrido un error');
    });

    this.activatedRouteService.params.subscribe(param => {

      this.artistService.getById(param['artistID']).subscribe(artist => {
        this.artist = artist;

        this.artistForm.controls['name'].setValue(this.artist.name);
        this.artistForm.controls['year'].setValue(this.artist.year);

        for(let art of this.artist.related){
          let obj = {};
          obj['id'] = art.id;
          obj['itemName'] = art.name;
          this.selectedArtists.push(obj);
        }

        for(let people of this.artist.members){
          let obj = {};
          obj['id'] = people.id;
          obj['itemName'] = people.name;
          this.selectedPeople.push(obj);
        }

        for(let st of this.artist.styles){
          let obj = {};
          obj['id'] = st.id;
          obj['itemName'] = st.name;
          this.selectedStyles.push(obj);
        }
      }, (err) => {
        this.notificationService.error('ERROR', 'Oops! Ha ocurrido un error');
      });

    }, (err) => {
      this.notificationService.error('ERROR', 'Oops! Ha ocurrido un error');
    });

    this.flag = true;

  }

  buildObject(list: any[]): any[] {
    const res = [];
    for (const item of list) {
      const obj = {};
      obj['id'] = item.id;
      res.push(obj);
    }
    return res;
  }

  action($event) {
    if (this.artistForm.valid) {

      this.artist.name = this.artistForm.controls['name'].value;
      this.artist.year = this.artistForm.controls['year'].value;
      this.artist.members = this.buildObject(this.selectedPeople);
      this.artist.styles = this.buildObject(this.selectedStyles);
      this.artist.related = this.buildObject(this.selectedArtists);

      this.artistService.update(this.artist).subscribe(response => {
        this.notificationService.success("Artista actualizado exitosamente");
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
