import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminLoginComponent } from './admin/admin-login/admin-login.component';
import { AdminComponent } from './admin/admin.component';
import { HomeComponent } from './common/home/home.component';
import { AuthGuard } from './guard/AuthGuard';
import { GuardComponent } from './guard/guard.component';
import { UserLoginComponent } from './user/user-login/user-login.component';
import { UserSignupComponent } from './user/user-signup/user-signup.component';
import { UserComponent } from './user/user.component';

const routes: Routes = [
  {path: '', component:HomeComponent},
  { path: 'user-login', component:UserLoginComponent},
  { path: 'user-sign-up', component: UserSignupComponent},
  {path: 'admin-login', component:AdminLoginComponent},
  // {path: '**', redirectTo : ''},
  { path: 'user', loadChildren: ()=>import('./user/user.module').then(module => module.UserModule), canActivate: [AuthGuard]},
  { path: 'admin', loadChildren: ()=>import('./admin/admin.module').then(module => module.AdminModule), canActivate: [AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
