import { NgModule } from '@angular/core';
import { BookingHistoryComponent } from './booking-history/booking-history.component';
import { FlightBookingComponent } from './flight-booking/flight-booking.component';
import { ManageBookingComponent } from './manage-booking/manage-booking.component';
import { UserRoutingModule } from './user-routing.module';
import { UserComponent } from './user.component';
import { UserHeaderComponent } from './user-header/user-header.component';
import { UserFooterComponent } from './user-footer/user-footer.component';
import { UserSignupComponent } from './user-signup/user-signup.component';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { UserLoginComponent } from './user-login/user-login.component';
import { BookinghistoryService } from './service/bookinghistory.service';
import { FlightbookingService } from './service/flightbooking.service';
import { LoginService } from './service/login.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ManagebookingService } from './service/managebooking.service';
import { FlightSearchComponent } from './flight-search/flight-search.component';
import { FlightSearchService } from './service/flight-search.service';
import { SearchDialogueComponent } from './search-dialogue/search-dialogue.component';
import { ToastComponent } from './toast/toast.component';
import { ToastService } from './toast/toast.service';
import { UserService } from './userService';


@NgModule({
  declarations: [
    UserComponent,
    BookingHistoryComponent,
    FlightBookingComponent,
    ManageBookingComponent,
    UserHeaderComponent,
    UserFooterComponent,
    UserSignupComponent,
    UserLoginComponent,
    FlightSearchComponent,
    SearchDialogueComponent,
    ToastComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    ReactiveFormsModule,
    NgbModule
  ],
  providers: [UserService,
    ToastService,ManagebookingService,BookinghistoryService,FlightbookingService, LoginService,FlightSearchService],
  bootstrap: [UserComponent]
})
export class UserModule { }
