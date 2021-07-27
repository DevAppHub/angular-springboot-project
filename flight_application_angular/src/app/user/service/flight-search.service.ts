import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable()
export class FlightSearchService {

  HOST= environment.HOST+':'+environment.port;
  // HOST: string = "http://ltin319690:7087"

  constructor(private http: HttpClient) {
    console.log(environment)
  }

  

  getAllOnGoingFlightDetails(): Observable<any> {
    return this.http.get(this.HOST + '/user/api/v1/flight/search');
  }

  getAllreturnFlightDetails(): Observable<any> {
    return this.http.get(this.HOST + '/user/api/v1/flight/search');
  }

  bookFlight(request: any): Observable<any> {
    return this.http.post(this.HOST + '/user/api/v1/flight/book', request);
  }

  addTravellerDetails(request: any, emailId: string): Observable<any> {
    
    return this.http.put(this.HOST + '/user/api/v1/add/passenger/' + emailId, request);
  }
  getDiscountList(): Observable<any> {
   
    return this.http.get(this.HOST + '/user/api/v1/discount');
  }

  doPayment(data: any, email_id: string):Observable<any>{
    return this.http.put(this.HOST+'/user/api/v1/discount/'+email_id, data);
  }

  proceedForPayment(data: any, email_id: string): Observable<any>{
    return this.http.put(this.HOST+'/user/api/v1/payment/'+email_id, data);
  }

}


