import { Component, OnInit, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { Flight } from '../modal/Flight';
import { ModalDismissReasons, NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { BookinghistoryService } from '../service/bookinghistory.service';
import { FlightResponse } from '../modal/FlightResponse';
import { FormControl, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-booking-history',
  templateUrl: './booking-history.component.html',
  styleUrls: ['./booking-history.component.css']
})
export class BookingHistoryComponent implements OnInit {

  flights: FlightResponse[]=[];
  historyForm: FormGroup;
  closeResult = '';
  error: any;
  modalcontent: any | undefined;

  constructor(private modalService: NgbModal, private flightService: BookinghistoryService) {
    this.historyForm=new FormGroup({
      flight_no:new FormControl("", Validators.required),
      flight_name:new FormControl("", Validators.required),
      pnr_no:new FormControl("", Validators.required),
      departure_date_time:new FormControl("", Validators.required),
      no_of_travelers:new FormControl("", Validators.required)
    })
  }


  open(content: any, flight: any) {
    this.modalcontent = flight;
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }


  ngOnInit(): void {
    let emailId= sessionStorage.getItem('username');
    this.flightService.getAllBookedFlightDetailss(emailId)
      .subscribe((res: any) => {
        for(let i = 0; i < res.length; i++){
          this.flights.push(new FlightResponse(res[i].id,res[i].flight_no,res[i].flight_name, res[i].pnr_no,res[i].departure_date_time, res[i].no_of_travelers));
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
