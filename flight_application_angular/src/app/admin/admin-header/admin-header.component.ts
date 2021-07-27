import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'admin-header',
  templateUrl: './admin-header.component.html',
  styleUrls: ['./admin-header.component.css']
})
export class AdminHeaderComponent implements OnInit {

  isLoggedIn = false;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  logout(){
    localStorage.clear();
    sessionStorage.clear();
    this.router.navigate(['']);
  }
  
}
