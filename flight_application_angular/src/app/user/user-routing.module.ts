import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookingHistoryComponent } from './booking-history/booking-history.component';
import { FlightBookingComponent } from './flight-booking/flight-booking.component';
import { FlightSearchComponent } from './flight-search/flight-search.component';
import { ManageBookingComponent } from './manage-booking/manage-booking.component';
import { UserSignupComponent } from './user-signup/user-signup.component';
import { UserComponent } from './user.component';

const routes: Routes = [
  // { path: '/sign-up', component: UserSignupComponent},
  { path:'user-dashboard', component:UserComponent},
  { path:'flight-details', component:FlightSearchComponent},
  {path:'book-flight', component:FlightBookingComponent},
  { path: 'booking-history', component: BookingHistoryComponent},
  { path: 'manage-bookings', component: ManageBookingComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
