import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/user/modal/User';
import { LoginService } from 'src/app/admin/service/login.service';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent implements OnInit {
  loginForm: FormGroup;

  errorMessage = 'Invalid Credentials';
  successMessage: any;
  invalidLogin = false;
  loginSuccess = false;
  error: any;

  constructor(private router: Router, private loginService: LoginService) {
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
    // let user = new User();
    // send request
    this.loginService.authenticateUser(this.loginForm?.get('username')?.value,
    this.loginForm.get('password')?.value).subscribe(res => {
        console.log(res);
        this.invalidLogin = false;
        this.loginSuccess = true;
        this.successMessage = 'Login Successful.';
        localStorage.setItem("canLogin","true");
        this.router.navigate(['/', 'admin', 'admin-dashboard']);
      }, error => {
        this.invalidLogin = true;
        this.loginSuccess = false;
        this.error = error;
       let errorMessage=this.error.error.error_message;
       window.alert(errorMessage);
      });      
  }

}
