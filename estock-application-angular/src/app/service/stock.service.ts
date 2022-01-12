import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class StockService {

  HOST= environment.HOST+':'+environment.port;

  constructor(private http: HttpClient) {
    console.log(environment)
  }

  getCompanyList() : Observable<any> {
    return this.http.get(this.HOST+'/emarket/api/v1.0/company');
  }
  getStockList() : Observable<any> {
    return this.http.get(this.HOST+'/emarket/api/v1.0/stock');
  }

  addStock(data: any, companyCode: any) : Observable<any>{
    return this.http.post(this.HOST+'/emarket/api/v1.0/market/stock/add/'+companyCode, data);
  }
  getAvailableStockList(){
    return this.http.get(this.HOST+'/emarket/api/v1.0/market/stock');
  }
  deleteStockDetails(id: any): Observable<any>{
    return this.http.delete(this.HOST+"/emarket/api/v1.0/market/stock/delete/"+id);
  }

  searchStockBasedOnCompanyCode(companyCode: any): Observable<any>{
    return this.http.get(this.HOST+"/emarket/api/v1.0/market/stock/info/"+companyCode)
  }

}
