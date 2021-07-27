import { Component, Input, OnInit } from '@angular/core';
import { SearchDialogueComponent } from '../search-dialogue/search-dialogue.component';
import { FlightSearchService } from '../service/flight-search.service';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../userService';
import { FlightSearch } from '../modal/FlightSearch';
import { Passenger } from '../modal/Passenger';
import { ToastService } from '../toast/toast.service';
import { Discount } from '../modal/Discounts';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-flight-search',
  templateUrl: './flight-search.component.html',
  styleUrls: ['./flight-search.component.css']
})
export class FlightSearchComponent implements OnInit {
  closeResult = '';
  modalcontent: any | undefined;
  bookFlightForm: FormGroup;
  discountForm: FormGroup;
  paymentForm: FormGroup;
  travellerDetailsForm: FormGroup;
  passengerDet: Passenger[] =[];
  canActivate= true;
  addActive =false;
  travellerCount! :number;
  ongoingFlight: FlightSearch[] =[];
  returnFlight : FlightSearch[] =[];
  discounts: Discount[]=[];
  amountpayable: any;
  error: any;
  isToFlightBooked= false;
  isReturnFlightBooked=true;
  count=1;
  

  constructor(private router: Router,private toastService: ToastService,private flightSearchService: FlightSearchService, private modalService: NgbModal, private userServic: UserService) {
    this.paymentForm=new FormGroup({
      "payment_method": new FormControl("", Validators.required),
      "amount": new FormControl("",Validators.required),
     });
   
    this.discountForm=new FormGroup({
    "discount_id": new FormControl("", Validators.required),
    "amount": new FormControl("",Validators.required),
   });
    this.bookFlightForm = new FormGroup({
      "email_id": new FormControl("", Validators.required),
      "mobile_no": new FormControl("",Validators.required),
      "flight_name": new FormControl("", Validators.required),
      "flight_no": new FormControl("", Validators.required),
      "no_of_travelers": new FormControl("",Validators.minLength(0)),
      "departure_date_time" : new FormControl("", Validators.required),
      "from_place" : new FormControl("", Validators.required),
      "to_place" : new FormControl("", Validators.required)
    })
    this.travellerDetailsForm = new FormGroup({
      "name": new FormControl("", Validators.required),
      "id_proof": new FormControl("", Validators.required),
      "date_of_birth": new FormControl("", Validators.required),
      "meal_preference": new FormControl("", Validators.required),
      "meal_included": new FormControl("", Validators.required),
    })

  }

  
  ngOnInit(): void {
    this.ongoingFlight=this.userServic.getOngoingFlight();
    this.returnFlight=this.userServic.getReturnFlight();
      this.flightSearchService.getDiscountList().subscribe((res: any) => {
        for (let i = 0; i < res.length; i++) {
          this.discounts.push(new Discount(res[i].discount_number, res[i].discount_name, res[i].price));
        }
      })
  }
  bookFlight() {
    let flight_req={
      email_id:this.bookFlightForm?.get('email_id')?.value,
      mobile_no:this.bookFlightForm?.get('mobile_no')?.value,
      flight_no:this.modalcontent.flight_no,
      flight_name:this.modalcontent.flight_name,
      no_of_travelers:this.bookFlightForm?.get('no_of_travelers')?.value,
      departure_date_time:this.modalcontent.departure_date_time,
      from_place:this.modalcontent.from_place,
      to_place:this.modalcontent.to_place
    }
    this.amountpayable=this.bookFlightForm?.get('no_of_travelers')?.value*this.modalcontent.price;
    console.log('amount payable '+this.amountpayable);
    this.flightSearchService.bookFlight(flight_req).subscribe(res => {
      this.travellerCount=this.bookFlightForm?.get('no_of_travelers')?.value;
    }, error => {
      this.error = error;
     let errorMessage=this.error.error.error_message;
     window.alert(errorMessage);
    })
  }

  validateNoOfTravellers(){
    if(this.passengerDet.length<this.bookFlightForm?.get('no_of_travelers')?.value){
      return true;
    }
    return false;
  }

  addTravellerDetails(){
    let email_id=this.bookFlightForm?.get('email_id')?.value;
    console.log('book flight method get called');
    console.log('request '+JSON.stringify(this.passengerDet) +' and email id'+email_id);
    this.flightSearchService.addTravellerDetails(this.passengerDet, email_id).subscribe(res => {
      this.toastService.show("Passenger Details added successfully", {classname: 'bg-success text-light', delay: 1000 });
    }, error => {
      this.error = error;
     let errorMessage=this.error.error.error_message;
     window.alert(errorMessage);
    })

  }
  openDiscountModal(content: any){
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  
  applyDiscount(){
    let email_id=this.bookFlightForm?.get('email_id')?.value;
    let data={
      price:this.modalcontent.price*this.travellerCount,
      discount_code:this.discountForm?.get('discount_id')?.value
    }
    this.flightSearchService.doPayment(data, email_id).subscribe((res: any)=>{
      console.log('response'+ JSON.stringify(res));
      this.amountpayable=res.price;
    }, error => {
      this.error = error;
     let errorMessage=this.error.error.error_message;
     window.alert(errorMessage);
    })
  }
  proceedForpayment(){
    let email_id=this.bookFlightForm?.get('email_id')?.value;
    let data={
      price:this.paymentForm?.get('amount')?.value,
      payment_method:this.paymentForm?.get('payment_method')?.value
    }
    this.flightSearchService.proceedForPayment(data, email_id).subscribe((res: any)=>{
      this.toastService.show('Flight Booked Successfully. Your PNR Number is '+res.pnr_no,{classname: 'bg-success text-light', delay: 1000  });
      this.router.routeReuseStrategy.shouldReuseRoute = () => false;
      this.isToFlightBooked=true;
      this.isReturnFlightBooked=false;
      this.count++;
      if(this.count>2){
        this.isToFlightBooked=true;
      this.isReturnFlightBooked=true;
      }
      let currentUrl = this.router.url;
      this.router.navigate([currentUrl]);
    }, error => {
      this.error = error;
     let errorMessage=this.error.error.error_message;
     window.alert(errorMessage);
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

  openTravelerDetailsModal(content: any) {
    let formData={
      "name": this.travellerDetailsForm?.get('name')?.value,
      "id_proof":  this.travellerDetailsForm?.get('id_proof')?.value,
      "date_of_birth":  this.travellerDetailsForm?.get('date_of_birth')?.value,
      "meal_preference":  this.travellerDetailsForm?.get('meal_preference')?.value,
      "meal_included":  this.travellerDetailsForm?.get('meal_included')?.value
    }
    if(formData.name!=""&&formData.meal_preference!=""&&formData.meal_included!=""&&formData.id_proof!=""&&formData.date_of_birth!=""){
      this.passengerDet.push(new Passenger(formData.name,formData.id_proof,formData.date_of_birth,formData.meal_preference,formData.meal_included));
      console.log('passenger length'+this.passengerDet.length);
      console.log('passenger data '+JSON.stringify(this.passengerDet));
    }
    if(this.validateNoOfTravellers()){
      this.canActivate=true;
    }else{
      this.addActive=true;
      this.canActivate=false;
    }
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

}
