import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  HOST= environment.HOST+':'+environment.port;

  constructor(private http: HttpClient) {
    console.log(environment)
  }

  getCompanyList() : Observable<any> {
    return this.http.get(this.HOST+'/emarket/api/v1.0/market/company/getall');
  }

  addNewCompany(data: any) : Observable<any>{
    return this.http.post(this.HOST+'/emarket/api/v1.0/market/company/register', data);
  }

  searchCompanyBasedOnCompanyCode(companyCode: any): Observable<any>{
    return this.http.get(this.HOST+"/emarket/api/v1.0/market/company/info/"+companyCode)
  }

  deleteCompanyDetails(id: any): Observable<any>{
    return this.http.delete(this.HOST+"/emarket/api/v1.0/market/company/delete/"+id);
  }

}
