import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {
  loginForm: FormGroup;
  errorMessage = 'Invalid Credentials';
  successMessage: any;
  invalidLogin = false;
  loginSuccess = false;

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
        this.router.navigate(['/', 'user', 'user-dashboard']);
      }, () => {
        this.invalidLogin = true;
        this.loginSuccess = false;
      });
  }

  signUp() {
    this.router.navigate(['/', 'user-sign-up']);
  }
}
