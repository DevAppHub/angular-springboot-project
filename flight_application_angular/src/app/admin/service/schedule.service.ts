import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable()
export class ScheduleService {

  HOST= environment.HOST+':'+environment.port;
  // HOST: string = "http://ltin319690:7087"

  constructor(private http: HttpClient) {
    console.log(environment)
  }

  getAllLocationList(): Observable<any>{
    return this.http.get(this.HOST+'/admin/api/v1/location');
  }
  getFlightData(): Observable<any> {
    return this.http.get(this.HOST + '/admin/api/v1/flight/schedule');
  }

  updateList(id: number, data: any): Observable<any> {
    return this.http.put(this.HOST + '/admin/api/v1/flight/schedule/' + id, data);
  }

  remove(id: any): Observable<any> {
    return this.http.delete(this.HOST + '/admin/api/v1/flight/schedule/' + id);
  }

  add(data: any): Observable<any> {
    return this.http.post(this.HOST + '/admin/api/v1/flight/schedule', data);
  }

  search(data: any): Observable<any> {
    return this.http.post(this.HOST + '/admin/api/v1/flight/schedule/search', data);
  }
}
