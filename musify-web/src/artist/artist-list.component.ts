import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ArtistService} from './artist.service';
import {StyleService} from '../style/style.service';
import {Artist} from './artist';
import {Style} from '../style/style';
import {NotificationsService} from 'angular2-notifications';

@Component({
  templateUrl: './artist-list.component.html',
  styleUrls: ['./artist-list.component.css']
})

export class ArtistListComponent{
  public title: String = 'Artistas Musify';
  public artists: Artist[] = [];
  public styles: Style[] = [];

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private artistService: ArtistService,
    private notificationService: NotificationsService,
    private styleService: StyleService
  ) {}

  ngOnInit() {

    this.styleService.getAll().subscribe(styles => {
      this.styles = styles;
    }, (err) => {
      this.notificationService.error('ERROR', 'Oops! Ha ocurrido un error');
    });
    this.list();
  }

  list(style?: number) {

    if  (style != undefined) {
      this.styleService.getById(style).subscribe(st => {
        this.artists = st.artists;
      }, (err) => {
        this.notificationService.error('ERROR', 'Oops! Ha ocurrido un error');
      });
    } else {
      this.artistService.getAll().subscribe(artists => {
        this.artists = artists;
      }, (err) => {
        this.notificationService.error('ERROR', 'Oops! Ha ocurrido un error');
      });
    }
  }

  createArtist() {
    this.router.navigate(['./new'], {relativeTo: this.activatedRoute});
  }

  createPeople() {
    this.router.navigateByUrl('/people/new');
  }

  detailArtist(id: number) {
    this.router.navigate(['./' + id], {relativeTo: this.activatedRoute});
  }

  styleChanged(value){
    if (value === "") {
      this.list();
    } else {
      this.list(value);
    }
  }
}
