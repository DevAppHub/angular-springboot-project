import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    canLogin: boolean | undefined;

    constructor() { }
    isLoggedIn(): boolean {
        var token = localStorage.getItem("canLogin");
        console.log("Auth Service Token " + token);
        if (token === "true") {
            console.log("Auth Service Token Routed " + token);
            return true;
        }
        else {
            console.log("Auth Service Token Failed " + token);
            return false;
        }
    }
}

