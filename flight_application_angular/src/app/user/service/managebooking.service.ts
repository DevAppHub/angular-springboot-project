import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable()
export class ManagebookingService {

  HOST= environment.HOST+':'+environment.port;
  // HOST:string = "http://ltin319690:7087"

  constructor(private http:HttpClient) { 
    console.log(environment)
  }

  getFlightDetailsForManage(username: any): Observable<any>{
    return this.http.get(this.HOST+'/user/api/v1/flight/'+username);
  }

  cancelFlightBooking(id : any): Observable<any>{
    return this.http.delete(this.HOST+'/user/api/v1/flight/'+id);
  }
  searchFlightDetails(pnr: any){
    return this.http.get(this.HOST+'/user/api/v1/flight/pnr/'+pnr);
  }
}
