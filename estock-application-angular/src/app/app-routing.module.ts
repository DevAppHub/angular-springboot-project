import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CompanyComponent } from './company/company.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AuthenticationGuard } from './guard/authentication.guard';
import { HomeComponent } from './home/home.component';
import { StockComponent } from './stock/stock.component';

const routes: Routes = [
  {path: '', component:HomeComponent},
  {path: 'home', component:HomeComponent},
  { path: 'dashboard', component:DashboardComponent, canActivate: [AuthenticationGuard]},
  {path: 'add_company', component: CompanyComponent, canActivate: [AuthenticationGuard]},
  {path:'add_stock', component: StockComponent, canActivate: [AuthenticationGuard]},
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
