import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {People} from './people';
import {BaseService} from '../utils/BaseService';

@Injectable()
export class PeopleService extends BaseService {

  BASE_URL: string = this.HOST + '/people';

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

  create(people: People): Observable<any> {
    return this.http.post(this.BASE_URL, people);
  }

  update(people: People): Observable<any> {
    return this.http.put(this.BASE_URL + '/' + people.id, people);
  }
}
