import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Host, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable()
export class FlightbookingService {

  HOST= environment.HOST+':'+environment.port;
  // HOST:string = "http://ltin319690:7087"

  constructor(private http:HttpClient) { 
    console.log(environment)
  }

  getAllLocationList(): Observable<any>{
    return this.http.get(this.HOST+'/user/api/v1/location');
  }

  searchFlight(request : any): Observable<any>{
    return this.http.post(this.HOST+'/user/api/v1/flight/search',request);
  }

}
