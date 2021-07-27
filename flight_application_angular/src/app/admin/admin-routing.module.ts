import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { AdminSchedulesComponent } from './admin-schedules/admin-schedules.component';
import { AdminComponent } from './admin.component';
import { DiscountComponent } from './discount/discount.component';
import { ManageAirlinesComponent } from './manage-airlines/manage-airlines.component';
import { ReportsComponent } from './reports/reports.component';

const routes: Routes = [
  { path: 'admin-dashboard', component: AdminComponent},
  { path: 'manage-schedules', component:AdminSchedulesComponent},
  { path: 'manage-discounts', component: DiscountComponent},
  { path: 'manage-airelines',component:ManageAirlinesComponent },
  {path: 'reports',component: ReportsComponent},
  {path: 'logout', component:AdminLoginComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
