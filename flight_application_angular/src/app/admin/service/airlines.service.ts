import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable()
export class AirlinesService {
  HOST= environment.HOST+':'+environment.port;
  // HOST: string = "http://ltin319690:7087"

  constructor(private http: HttpClient) {
    console.log(environment)
  }

  getAllLocationList(): Observable<any>{
    return this.http.get(this.HOST+'/admin/api/v1/location');
  }
  
  getAirlineDetails() : Observable<any> {
    return this.http.get(this.HOST+'/admin/api/v1/flight');
  }

  updateAirlinesDetails(id: number, data: any) : Observable<any>{
    return this.http.put(this.HOST+'/admin/api/v1/flight/'+id, data);
  }

  deleteAirlines(id: any) : Observable<any>{
    return this.http.get(this.HOST);
  }

  addNewAirline(data: any): Observable<any> {
    return this.http.post(this.HOST+'/admin/api/v1/flight', data);
  }
}
