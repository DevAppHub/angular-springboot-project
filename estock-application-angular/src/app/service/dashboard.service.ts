import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  HOST= environment.HOST+':'+environment.port;

  constructor(private http: HttpClient) {
    console.log(environment)
  }

  getDailyStatus(){
    return this.http.get(this.HOST+'/emarket/api/v1.0/market/stock/status');
  }
}
