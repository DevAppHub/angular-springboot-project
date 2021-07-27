import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable()
export class SignupService {

  HOST= environment.HOST+':'+environment.port;
    // HOST: string = "http://ltin319690:7087"

    constructor(private http: HttpClient) {
      console.log(environment)
    }

    registerUser(data: any): Observable<any>{
      return this.http.post(this.HOST+'/user/register',data);
    }
}
