import { HttpClient } from '@angular/common/http';
import { Component, OnInit, TemplateRef } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SearchSchedule } from '../modal/SearchSchedule';
import { AdminToastService } from '../service/admin-toast.service';
import { ScheduleService } from '../service/schedule.service';

@Component({
  selector: 'app-admin-schedules',
  templateUrl: './admin-schedules.component.html',
  styleUrls: ['./admin-schedules.component.css']
})
export class AdminSchedulesComponent implements OnInit {

  editField: string;
  closeResult = '';
  airlineScheduleForm: FormGroup;
  modalcontent:any | undefined;
  searchScheduleForm: FormGroup;
  updateScheduleForm: FormGroup;
  appadmintoast: any;
  searchedResult: SearchSchedule[]=[];

  locationObj = {
    "stateCode": "",
    "stateName": ""
  }
  locations = [this.locationObj];
error: any;
  
  flightData: SearchSchedule[]=[];

  constructor(private scheduleService: ScheduleService,private router: Router,
    private modalService: NgbModal, private adminToastService: AdminToastService) { 
    this.editField="";
    
    this.airlineScheduleForm = new FormGroup({

      "flight_no": new FormControl("", Validators.required),
      "flight_name": new FormControl("", Validators.required),
      "departure_date_time": new FormControl("", Validators.required),
      "no_of_passenger": new FormControl("", Validators.required),
      "from_place": new FormControl("", Validators.required),
      "to_place": new FormControl("", Validators.required)

    })

    this.searchScheduleForm = new FormGroup({
      "flight_no": new FormControl("", Validators.required),
      "flight_name": new FormControl("", Validators.required),
      "from_place": new FormControl("", Validators.required),
      "to_place": new FormControl("", Validators.required)
    })

    this.updateScheduleForm = new FormGroup({

      "flight_no": new FormControl("", Validators.required),
      "flight_name": new FormControl("", Validators.required),
      "departure_date_time": new FormControl("", Validators.required),
      "no_of_passenger": new FormControl("", Validators.required),
      "from_place": new FormControl("", Validators.required),
      "to_place": new FormControl("", Validators.required)
    })
    
  }

  ngOnInit(): void {
    this.scheduleService.getAllLocationList().subscribe((res: any) => {
      var obj = [];
      for (let i = 0; i < res.length; i++) {
        var loc = {
          stateName: res[i].stateName,
          stateCode: res[i].stateCode
        }
        obj.push(loc);
      }
      this.locations = obj;
    }, error => {
      this.error = error;
     let errorMessage=this.error.error.error_message;
     window.alert(errorMessage);
    })

    this.scheduleService.getFlightData()
    .subscribe((res :any)=> {
      console.log('res lenth'+res.length);
      for(let i = 0; i < res.length; i++){
       this.flightData.push(new SearchSchedule(res[i].id,res[i].flight_name,res[i].flight_no,res[i].departure_date_time,
        res[i].no_of_passenger,res[i].to_place,res[i].from_place));
      }
    }, error => {
      this.error = error;
     let errorMessage=this.error.error.error_message;
     window.alert(errorMessage);
    })
  }
   
  //update airline schedule
  updateList(id: number,data: any) {
   this.scheduleService.updateList(id, data).subscribe((res:any)=>{
    if(res!=null){
      this.router.navigate(['/', 'admin', 'manage-schedules']);
    }
   })
  }

  update(){
    let flight={
      id: this.modalcontent.id,
      flight_name: this.modalcontent.flight_name,
      flight_no: this.modalcontent.flight_no,
      departure_date_time:this.updateScheduleForm?.get('departure_date_time')?.value,
      no_of_passenger: this.updateScheduleForm?.get('no_of_passenger')?.value,
      to_place: this.updateScheduleForm?.get('to_place')?.value,
      from_place: this.updateScheduleForm?.get('from_place')?.value,
  }
 console.log('flight data'+JSON.stringify(flight))
  if(flight.departure_date_time==null || flight.departure_date_time==""){
    flight.departure_date_time=this.modalcontent.departure_date_time;
  }
  if(flight.to_place==null || flight.to_place==""){
    flight.to_place=this.modalcontent.to_place;
  }
  if(flight.no_of_passenger==null || flight.no_of_passenger==""){
    flight.no_of_passenger=this.modalcontent.no_of_passenger;
  }
  if(flight.from_place==null || flight.from_place==""){
    flight.from_place=this.modalcontent.from_place;
  }
  // send request
  this.scheduleService.updateList(this.modalcontent.id,flight)
    .subscribe(res => {
      console.log(res);
      let response = res;
      if (response != null && res.ok) {
        this.adminToastService.show("Scheduled Updated Successfully",{classname: 'bg-success text-light', delay: 1000 });
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

  //remove airline schedule
  remove(id: any) {
    this.scheduleService.remove(id).subscribe((res:any)=> {
      console.log('request to cancel the airline for id'+id);
      if(res!=null){
        this.adminToastService.show("Scheduled Removed Successfully",{classname: 'bg-success text-light', delay: 15000 });
          this.router.routeReuseStrategy.shouldReuseRoute = () => false;
          this.router.onSameUrlNavigation = 'reload';
          let currentUrl = this.router.url;
          this.router.navigate([currentUrl]);
      }
    }, error => {
      this.error = error;
     let errorMessage=this.error.error.error_message;
     window.alert(errorMessage);
    });
  }

  //add new airline schedule
  addNewAirlineSchedule(){
    console.log('adding new airline schedule');
    let flight={
        id: "",
        flight_name: this.airlineScheduleForm?.get('flight_name')?.value,
        flight_no: this.airlineScheduleForm?.get('flight_no')?.value,
        departure_date_time:this.airlineScheduleForm?.get('departure_date_time')?.value,
        no_of_passenger: this.airlineScheduleForm?.get('no_of_passenger')?.value,
        to_place: this.airlineScheduleForm?.get('to_place')?.value,
        from_place: this.airlineScheduleForm?.get('from_place')?.value,
    }
    // send request
    this.scheduleService.add(flight)
      .subscribe(res => {
        console.log(res);
        let response = res;
        if (response != null) {
          this.adminToastService.show("Airline Scheduled Added Successfully",{classname: 'bg-success text-light', delay: 1000 });
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

  open(content: any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  openUpdateModal(updateScheduleModal: any,flight: any){
   this. modalcontent=flight ;
    this.modalService.open(updateScheduleModal, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
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

  //search schedules based on condition
  searchSchedule(){
    console.log('searching airline schedule');
    let flight={
        id: "",
        flight_name: this.searchScheduleForm?.get('flight_name')?.value,
        flight_no: this.searchScheduleForm?.get('flight_no')?.value,
        to_place: this.searchScheduleForm?.get('to_place')?.value,
        from_place: this.searchScheduleForm?.get('from_place')?.value,
    }
    // send request
    this.scheduleService.search(flight)
      .subscribe((res :any)=> {
        if (res != null) {
          for(var i=0;i<res.length;i++){
            this.searchedResult.push(new SearchSchedule(res[i].id,res[i].flight_name,res[i].flight_no,res[i].departure_date_time,
              res[i].no_of_passenger,res[i].to_place,res[i].from_place));
          }
          console.log('response---'+JSON.stringify(this.searchedResult));
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
