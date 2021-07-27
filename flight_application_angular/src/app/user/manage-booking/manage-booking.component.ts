import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FlightResponse } from '../modal/FlightResponse';
import { ManagebookingService } from '../service/managebooking.service';
import { ToastService } from '../toast/toast.service';
import {jsPDF} from 'jspdf';
import { isTemplateExpression } from 'typescript';

@Component({
  selector: 'manage-booking',
  templateUrl: './manage-booking.component.html',
  styleUrls: ['./manage-booking.component.css']
})
export class ManageBookingComponent implements OnInit {

  modalcontent: any | undefined;
  viewBookingForm: FormGroup;
  pnrSearchForm: FormGroup;
  closeResult = '';
  error: any;
  pnrSearchResult=new FlightResponse(0,'','','',new Date(),0);

  manageFlight: FlightResponse[] = [];
  constructor(private toastService: ToastService, private manageBookingService: ManagebookingService, private modalService: NgbModal, private router: Router) {
   this.pnrSearchForm=new FormGroup({
    "pnr_no": new FormControl("", Validators.required)
   })

    this.viewBookingForm = new FormGroup({
      flight_no: new FormControl("", Validators.required),
      flight_name: new FormControl("", Validators.required),
      pnr_no: new FormControl("", Validators.required),
      departure_date_time: new FormControl("", Validators.required),
      no_of_travelers: new FormControl("", Validators.required)
    });
  }

  ngOnInit(): void {
    let emailId = sessionStorage.getItem('username');
    this.manageBookingService.getFlightDetailsForManage(emailId)
      .subscribe((res: any) => {
        console.log('res lenth' + res.length);
        for (let i = 0; i < res.length; i++) {
          this.manageFlight.push(new FlightResponse(res[i].id, res[i].flight_no, res[i].flight_name, res[i].pnr_no, res[i].departure_date_time, res[i].no_of_travelers));
        }
        console.log(JSON.stringify(this.manageFlight));
      }, error => {
        this.error = error;
        let errorMessage = this.error.error.error_message;
        window.alert(errorMessage);
      })
  }

  openModal(content: any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
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

  remove(id: any) {
    console.log('remove discount offer for ' + id)
    this.manageBookingService.cancelFlightBooking(id).subscribe(res => {
      console.log('request to cancel the airline for id' + id);
      if (res != null) {
        this.toastService.show("Booking Canceled Successfully", { classname: 'bg-success text-light', delay: 1000 });
        this.router.routeReuseStrategy.shouldReuseRoute = () => false;
        this.router.onSameUrlNavigation = 'reload';
        let currentUrl = this.router.url;
        this.router.navigate([currentUrl]);
      }
    }, error => {
      this.error = error;
      let errorMessage = this.error.error.error_message;
      window.alert(errorMessage);
    });
  }

  searchFlightDetails(){
    let pnrNo=this.pnrSearchForm.get('pnr_no')?.value;
    this.manageBookingService.searchFlightDetails(pnrNo).subscribe((res: any) => {
      if(res!=null){
        this.pnrSearchResult= new FlightResponse(res.id, res.flight_no, res.flight_name, res.pnr_no, res.departure_date_time, res.no_of_travelers);
      }
    }, error => {
      this.error = error;
      let errorMessage = this.error.error.error_message;
      window.alert(errorMessage);
    })
  }
  downloadPdf(data : any){
    const doc=new jsPDF();
    var text=doc.splitTextToSize(JSON.stringify(data), 180);
    doc.text(text, 10, 15,);
    doc.setFont("times","bold");
    doc.save('booking'+data.id+'.pdf');

  }

}
