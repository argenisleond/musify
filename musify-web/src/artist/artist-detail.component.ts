import {Component} from '@angular/core';
import {Artist} from './artist';
import {ActivatedRoute, Router} from '@angular/router';
import {ArtistService} from './artist.service';
import {NotificationsService} from 'angular2-notifications';
import {Location} from '@angular/common';

@Component({
  templateUrl: './artist-detail.component.html',
  styleUrls: ['artist-detail.component.css']
})

export class ArtistDetailComponent{
  public artist: Artist;

  constructor(
   private router: Router,
   private location: Location,
   private artistService: ArtistService,
   private activatedRouteService: ActivatedRoute,
   private notificationService: NotificationsService
  ) {}

  ngOnInit(){
    this.activatedRouteService.params.subscribe(param => {

      this.artistService.getById(param['artistID']).subscribe(res => {
        this.artist = res;
      }, (err) => {
        this.notificationService.error('ERROR', 'Oops! Ha ocurrido un error'); });
    }, (err) => {
      this.notificationService.error('ERROR', 'Oops! Ha ocurrido un error'); });
  }

  update($event){
    this.router.navigate(['./update'], {relativeTo: this.activatedRouteService});
  }

  back($event){
    this.location.back();
  }

  delete(id){
    this.artistService.delete(id).subscribe(res => {
      this.notificationService.success("Artista eliminado exitosamente");
      this.location.back();
    }, (err) => {
      this.notificationService.error('ERROR', 'Verifique si el artista no se encuentra relacionado con otros'); });
  }
}
