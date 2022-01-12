import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  loginForm : FormGroup;
  errorMessage = 'Invalid Credentials';
  successMessage: any;
  invalidLogin = false;
  loginSuccess = false;

  constructor(private httpClient: HttpClient, private router: Router, private loginService: LoginService
    ) {
      this.loginForm = new FormGroup({
        "username": new FormControl("",
          [
            Validators.required,
            Validators.pattern("[a-zA-Z0-9!#$%&'*+\/=?^_`{|}~-]+(?:\.[a-zA-Z0-9!#$%&'*+\/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?")
          ]
        ),
        "password": new FormControl("", Validators.required)
      })
     }

  ngOnInit(): void {
  }

  login() {
    this.loginService.login(this.loginForm?.get('username')?.value,
    this.loginForm.get('password')?.value).subscribe(res => {
      console.log(res);
      this.invalidLogin = false;
      this.loginSuccess = true;
      this.successMessage = 'Login Successful.';
      localStorage.setItem("canLogin","true");
      this.router.navigate(['/','dashboard']);
    }, () => {
      this.invalidLogin = true;
      this.loginSuccess = false;
    });
   
  }

}
