import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';

@Injectable()
export class HttpInterceptorService implements HttpInterceptor {

    constructor(private authenticationService: LoginService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if((req.url=="http://laptop-v4st53ol:9087/emarket/authenticate")){
        }
        else{
        const token = sessionStorage.getItem("token");
        // console.log('token'+token);
        req = req.clone({
            headers: req.headers.set('Authorization', ""+token),
        });
 
        req = req.clone({
            headers: req.headers.set('Content-Type', 'application/json')
        });
    }
    console.log(req.headers.get('Authorization'))
    console.log(req.headers.get('Content-Type'))
    return next.handle(req);
    }
}
