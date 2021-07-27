import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class BookinghistoryService {

  HOST= environment.HOST+':'+environment.port;
  // HOST:string = "http://ltin319690:7087"

  constructor(private http:HttpClient) { 
    console.log(environment)
  }

  getAllBookedFlightDetailss(emailId: any): Observable<any>{
    return this.http.get(this.HOST+'/user/api/v1/history/'+emailId);
  }
}
