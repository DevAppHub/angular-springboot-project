import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FlightSearch } from '../modal/FlightSearch';
import { FlightbookingService } from '../service/flightbooking.service';
import { UserService } from '../userService';

@Component({
  selector: 'app-flight-booking',
  templateUrl: './flight-booking.component.html',
  styleUrls: ['./flight-booking.component.css']
})
export class FlightBookingComponent implements OnInit {
  flightSearchForm: FormGroup;

  ongoingFlight: FlightSearch[] = [];
  returnFlight: FlightSearch[] = [];
  error: any;

  locationObj = {
    "stateCode": "",
    "stateName": ""
  }
  locations = [this.locationObj];

  constructor(private httpClient: HttpClient, private router: Router, private bookingService: FlightbookingService, private userServic: UserService) {
    this.flightSearchForm = new FormGroup({
      "tripType": new FormControl("", Validators.required),
      "from_city": new FormControl("", Validators.required),
      "to_city": new FormControl("", Validators.required),
      "depart_date": new FormControl("", Validators.required),
      "return_date": new FormControl("", Validators.minLength(0)),
      "no_of_passenger": new FormControl("", Validators.required)
    })
  }

  ngOnInit(): void {
    this.bookingService.getAllLocationList().subscribe((res: any) => {
      console.log('----' + res);
      var obj = [];
      for (let i = 0; i < res.length; i++) {
        var loc = {
          stateName: res[i].stateName,
          stateCode: res[i].stateCode
        }
        obj.push(loc);
      }
      this.locations = obj;
      // console.log(JSON.stringify(this.locations));
    }, error => {
      this.error = error;
     let errorBody= JSON.stringify(this.error);
     let errorMessage=this.error.error.error_message;
    //  console.log(errorBody);
     window.alert(errorMessage);
    })
  }

  searchFlight() {
    if (this.flightSearchForm?.get('tripType')?.value == '1') {
      let request = {
        from_place: this.flightSearchForm?.get('from_city')?.value,
        to_place: this.flightSearchForm?.get('to_city')?.value,
        date_of_journey: this.flightSearchForm?.get('depart_date')?.value
      }
      this.bookingService.searchFlight(request)
        .subscribe(res => {
          // console.log(res);
          this.createFlightSearchObj(res);
          console.log('' + JSON.stringify(this.ongoingFlight));
          this.userServic.setOngoingFlight(this.ongoingFlight);
        }, error => {
          this.error = error;
         let errorBody= JSON.stringify(this.error);
         let errorMessage=this.error.error.error_message;
         console.log(errorBody);
         window.alert(errorMessage);
        })
      this.router.navigate(['/user/flight-details']);
    } else {
      let oneWaySearch = {
        from_place: this.flightSearchForm?.get('from_city')?.value,
        to_place: this.flightSearchForm?.get('to_city')?.value,
        date_of_journey: this.flightSearchForm?.get('depart_date')?.value
      }
      let returnFlightSearch = {
        from_place: this.flightSearchForm?.get('to_city')?.value,
        to_place: this.flightSearchForm?.get('from_city')?.value,
        date_of_journey: this.flightSearchForm?.get('return_date')?.value
      }
      this.bookingService.searchFlight(oneWaySearch)
        .subscribe(res => {
          // console.log(res);
          this.createFlightSearchObj(res);
          this.userServic.setOngoingFlight(this.ongoingFlight);
          // co/snsole.log('on going flight'+this.userServic.getOngoingFlight());
        }, error => {
          this.error = error;
         let errorMessage=this.error.error.error_message;
         window.alert(errorMessage);
        })
      this.bookingService.searchFlight(returnFlightSearch)
        .subscribe(res => {
          // console.log(res);
          this.createReturnFlightSearchObj(res);
          this.userServic.setReturnFlight(this.returnFlight);
        }, error => {
          this.error = error;
         let errorBody= JSON.stringify(this.error);
         let errorMessage=this.error.error.error_message;
         console.log(errorBody);
         window.alert(errorMessage);
        })
      this.router.navigate(['/user/flight-details']);
    }

  }
  createFlightSearchObj(res: any) {
    {
      for (let i = 0; i < res.length; i++) {
        this.ongoingFlight.push(new FlightSearch(res[i].id, res[i].flight_no, res[i].flight_name, res[i].price, res[i].departure_date_time, res[i].from_place, res[i].to_place));
      }
    }
  }

  createReturnFlightSearchObj(res: any) {
    {
      for (let i = 0; i < res.length; i++) {
        this.returnFlight.push(new FlightSearch(res[i].id, res[i].flight_no, res[i].flight_name, res[i].price, res[i].departure_date_time, res[i].from_place, res[i].to_place));
      }
    }
  }

}
