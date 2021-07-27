import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';
import { UserRegister } from '../modal/SignUp';
import { SignupService } from '../service/signup.service';
import { ToastService } from '../toast/toast.service';

@Component({
  selector: 'user-signup',
  templateUrl: './user-signup.component.html',
  styleUrls: ['./user-signup.component.css']
})
export class UserSignupComponent implements OnInit {

  signUpForm: FormGroup;
  error!: any;

  constructor(private httpClient: HttpClient, private router: Router, private signUpService: SignupService
    , private toastService: ToastService) {
    this.signUpForm = new FormGroup({
      "username": new FormControl("",
        [
          Validators.required,
          Validators.pattern("[a-zA-Z0-9!#$%&'*+\/=?^_`{|}~-]+(?:\.[a-zA-Z0-9!#$%&'*+\/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?")
        ]
      ),
      "password": new FormControl("", Validators.required),
      "firstName": new FormControl("", Validators.required),
      "lastName": new FormControl("", Validators.required),
      "mobileNo": new FormControl("", Validators.required)

    })
  }

  ngOnInit(): void {
  }

  signUp() {
    let username=this.signUpForm?.get('firstName')?.value+" "+this.signUpForm.get('lastName')?.value;
    let user = new UserRegister(this.signUpForm?.get('firstName')?.value,
      this.signUpForm.get('lastName')?.value,
       this.signUpForm?.get('mobileNo')?.value,
        this.signUpForm?.get('username')?.value,
         this.signUpForm?.get('password')?.value);
    // send request
    this.signUpService.registerUser(user)
      .subscribe(res => {
        if (res.ok) {
          this.toastService.show(username+'Register Successfully',{classname: 'bg-success text-light', delay: 1000 })
          this.router.navigate(['/', 'user-login']);
        }
      }, error => {
        this.error = error;
       let errorBody= JSON.stringify(this.error);
       let errorMessage=this.error.error.error_message;
       console.log(errorBody);
       window.alert(errorMessage);
      })

  }
}
