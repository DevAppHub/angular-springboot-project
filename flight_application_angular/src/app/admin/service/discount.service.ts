import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable()
export class DiscountService {
  HOST= environment.HOST+':'+environment.port;
  // HOST: string = "http://ltin319690:7087"

  constructor(private http: HttpClient) {
    console.log(environment)
  }

  getDiscountList() : Observable<any> {
    return this.http.get(this.HOST+'/admin/api/v1/discount');
  }

  updateDiscountDetails(id: number, data: any) : Observable<any>{
    return this.http.put(this.HOST+'/admin/api/v1/discount/'+id, data);
  }

  removeDiscountOffer(id: any) : Observable<any>{
    return this.http.delete(this.HOST+'/admin/api/v1/discount/'+id);
  }

  addNewDiscountOffer(data: any) : Observable<any>{
    return this.http.post(this.HOST+'/admin/api/v1/discount/', data);
  }
}
