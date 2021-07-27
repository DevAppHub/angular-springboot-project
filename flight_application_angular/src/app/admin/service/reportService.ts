import { HttpClient } from "@angular/common/http"
import { Injectable } from "@angular/core"
import { environment } from "src/environments/environment"

@Injectable()
export class ReportService {

  HOST= environment.HOST+':'+environment.port;
  // HOST: string = "http://ltin319690:7087"

  constructor(private http: HttpClient) {
    console.log(environment)
  }

  getReport(flight_no: string){
    return this.http.get(this.HOST+'/admin/api/v1/flight/'+flight_no);
  }
}