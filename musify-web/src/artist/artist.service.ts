import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Artist} from './artist';
import {BaseService} from '../utils/BaseService';

@Injectable()
export class ArtistService extends BaseService {

  BASE_URL: string = this.HOST + '/artists';

  constructor(
    private http: HttpClient) {
    super();
  }

  getAll(params: any = '{ }'): Observable<any> {
    return this.http.get(this.BASE_URL, {
      params: params
    });
  }

  getById(id: number): Observable<any> {
    return this.http.get(this.BASE_URL + '/' + id);
  }

  create(artist: Artist): Observable<any> {
    return this.http.post(this.BASE_URL, artist);
  }

  update(artist: Artist): Observable<any> {
    return this.http.put(this.BASE_URL + '/' + artist.id, artist);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(this.BASE_URL + '/' + id);
  }

}
