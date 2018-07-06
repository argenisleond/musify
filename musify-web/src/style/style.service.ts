import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {catchError} from 'rxjs/operators';
import {Style} from './style';
import {BaseService} from '../utils/BaseService';

@Injectable()
export class StyleService extends BaseService {

  BASE_URL: string = this.HOST + '/styles';

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

  create(style: Style): Observable<any> {
    return this.http.post(this.BASE_URL, style);
  }

  update(style: Style): Observable<any> {
    return this.http.put(this.BASE_URL + '/' + style.id, style);
  }
}
