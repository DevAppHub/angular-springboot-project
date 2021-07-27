import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbModal,ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { AdminToastService } from '../service/admin-toast.service';
import { AirlinesService } from '../service/airlines.service';

@Component({
  selector: 'app-manage-airlines',
  templateUrl: './manage-airlines.component.html',
  styleUrls: ['./manage-airlines.component.css']
})
export class ManageAirlinesComponent implements OnInit {

  modalcontent:any | undefined;
  closeResult = '';
  error: any;
  addAirlineForm: FormGroup;
  updateAirlieForm: FormGroup;
  locationObj = {
    "stateCode": "",
    "stateName": ""
  }
  locations = [this.locationObj];

  airline={
    id: "" ,
    flight_name: "" ,
    flight_no: "" ,
    model_no:"",
    manufactured_by: "",
    manufatured_date:"",
    departure_date_time: "",
    no_of_seats: "",
    to_place: "",
    from_place: "",
    price: ""

   }
  flightList= [this.airline];

  constructor(private admintoastService: AdminToastService,private airlineService: AirlinesService,private router: Router,private modalService: NgbModal, private httpClient: HttpClient) { 
    this.addAirlineForm = new FormGroup({

      "flight_no": new FormControl("", Validators.required),
      "flight_name": new FormControl("", Validators.required),
      "model_no": new FormControl("", Validators.required),
      "manufactured_by": new FormControl("", Validators.required),
      "no_of_seats": new FormControl("", Validators.required),
      "manufatured_date": new FormControl("", Validators.required),
      "departure_date_time": new FormControl("", Validators.required),
      "from_place": new FormControl("", Validators.required),
      "to_place": new FormControl("", Validators.required),
      "price": new FormControl("", Validators.required)

    })
    this.updateAirlieForm=new FormGroup({

      "flight_no": new FormControl("", Validators.required),
      "flight_name": new FormControl("", Validators.required),
      "model_no": new FormControl("", Validators.required),
      "manufactured_by": new FormControl("", Validators.required),
      "no_of_seats": new FormControl("", Validators.required),
      "manufatured_date": new FormControl("", Validators.required),
      "departure_date_time": new FormControl("", Validators.required),
      "from_place": new FormControl("", Validators.required),
      "to_place": new FormControl("", Validators.required),
      "price": new FormControl("", Validators.required)

    })
  }

  ngOnInit(): void {

    this.airlineService.getAllLocationList().subscribe((res: any) => {
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
     let errorMessage=this.error.error.error_message;
     window.alert(errorMessage);
    })

    this.airlineService.getAirlineDetails()
    .subscribe((res:any)=> {
      var obj=[];
      console.log('res lenth'+res.length);
      for(let i = 0; i < res.length; i++){
       var flight={
        id: res[i].id,
        flight_name: res[i].flight_name,
        flight_no: res[i].flight_no,
        model_no:res[i].model_no,
        manufactured_by: res[i].manufactured_by,
        manufatured_date:res[i].manufatured_date,
        no_of_seats: res[i].no_of_seats,
        to_place: res[i].to_place,
        from_place: res[i].from_place,
        price: res[i].price
       }
       obj.push(flight);
      }
      this.flightList=res;
      console.log(this.flightList);
    }, error => {
      this.error = error;
     let errorMessage=this.error.error.error_message;
     window.alert(errorMessage);
    })
  }
   

  update(){
    this.airline={
        id: this.modalcontent.id,
        flight_name: this.modalcontent.flight_name,
        flight_no: this.modalcontent.flight_no,
        model_no:this.modalcontent.model_no,
        manufactured_by: this.modalcontent.manufactured_by,
        manufatured_date:this.modalcontent.manufatured_date,
        departure_date_time:this.modalcontent.departure_date_time,
        no_of_seats: this.updateAirlieForm?.get('no_of_seats')?.value,
        to_place: this.updateAirlieForm?.get('to_place')?.value,
        from_place: this.updateAirlieForm?.get('from_place')?.value,
        price: this.updateAirlieForm?.get('price')?.value
    }
   
    if(this.airline.to_place==null || this.airline.to_place==""){
      this.airline.to_place=this.modalcontent.to_place;
    }
    if(this.airline.no_of_seats==null || this.airline.no_of_seats==""){
      this.airline.no_of_seats=this.modalcontent.no_of_seats;
    }
    if(this.airline.from_place==null || this.airline.from_place==""){
      this.airline.from_place=this.modalcontent.from_place;
    }
    if(this.airline.price==null || this.airline.price==""){
      this.airline.price=this.modalcontent.price;
    }
    // send request
    this.airlineService.updateAirlinesDetails(this.modalcontent.id, this.airline)
      .subscribe(res => {
        console.log(res);
        let response = res;
        if (response != null) {
          this.admintoastService.show("Airline Updated Successfully",{classname: 'bg-success text-light', delay: 15000 });
          this.router.routeReuseStrategy.shouldReuseRoute = () => false;
          this.router.onSameUrlNavigation = 'reload';
          let currentUrl = this.router.url;
          this.router.navigate([currentUrl]);
        }
      }, error => {
        this.error = error;
       let errorMessage=this.error.error.error_message;
       window.alert(errorMessage);
      })
  }

  remove(id: any) {
    this.airlineService.deleteAirlines(id).subscribe((res:any)=> {
      console.log('request to cancel the airline for id'+id);
      if(res!=null){
        this.admintoastService.show("Airline Removed Successfully",{classname: 'bg-success text-light', delay: 15000 });
        this.router.navigate(['/', 'admin', 'manage-schedules']);
      }
    }, error => {
      this.error = error;
     let errorMessage=this.error.error.error_message;
     window.alert(errorMessage);
    });
  }

  open(content: any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  openUpdateModal(updateAirlineModal: any,flight: any){
   this. modalcontent=flight ;
    this.modalService.open(updateAirlineModal, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
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

  addNewAirline(){
    console.log('adding new airline schedule');
    this.airline={
        id: "",
        flight_name: this.addAirlineForm?.get('flight_name')?.value,
        flight_no: this.addAirlineForm?.get('flight_no')?.value,
        model_no:this.addAirlineForm?.get('model_no')?.value,
        manufactured_by: this.addAirlineForm?.get('manufactured_by')?.value,
        manufatured_date:this.addAirlineForm?.get('manufatured_date')?.value,
        departure_date_time: this.addAirlineForm?.get('departure_date_time')?.value,
        no_of_seats: this.addAirlineForm?.get('no_of_seats')?.value,
        to_place: this.addAirlineForm?.get('to_place')?.value,
        from_place: this.addAirlineForm?.get('from_place')?.value,
        price: this.addAirlineForm?.get('price')?.value,
    }

    // send request
    this.airlineService.addNewAirline(this.airline)
      .subscribe(res => {
        console.log(res);
        let response = res;
        if (response != null) {
          this.admintoastService.show("Airline Added Successfully",{classname: 'bg-success text-light', delay: 1000 });
          this.router.routeReuseStrategy.shouldReuseRoute = () => false;
          this.router.onSameUrlNavigation = 'reload';
          let currentUrl = this.router.url;
          this.router.navigate([currentUrl]);
        }
      }, error => {
        this.error = error;
       let errorMessage=this.error.error.error_message;
       window.alert(errorMessage);
      })
  }

}
