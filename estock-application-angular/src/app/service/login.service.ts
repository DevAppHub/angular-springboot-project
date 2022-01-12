import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'

  public username: String;
  public password: String;
  
  HOST= environment.HOST+':'+environment.port;

  constructor(private http: HttpClient) {
    this.username="";
    this.password="";
   }

  login(username: any,password:any): Observable<any>{
    return this.http.post<any>(this.HOST+'/emarket/authenticate',{username,password}).pipe(
       map(
         userData => {
          sessionStorage.setItem('username',username);
          let tokenStr= 'Bearer '+userData.token;
          sessionStorage.setItem('token', tokenStr);
          return userData;
         }
       )

      );
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('username')
    return !(user === null)
  }

  logOut() {
    sessionStorage.removeItem('username')
  }

  logout() {
    sessionStorage.clear();
    sessionStorage.removeItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    console.log(sessionStorage);
    this.username = "";
    this.password = "";
  }
  
  getLoggedInUserName() {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    if (user === null) return ''
    return user
  }
}
