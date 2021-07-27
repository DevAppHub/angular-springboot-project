import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { FlightSearch } from './modal/FlightSearch';
 
@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(){
  }

  public ongoingFlight : FlightSearch[]=[];
  public returnFlight : FlightSearch[]=[];

 
  setOngoingFlight(ongoingFlight: FlightSearch[]) {
    if(ongoingFlight.length>0){
      for(var i=0; i<ongoingFlight.length;i++){
        this.ongoingFlight.push(ongoingFlight[i]);
      }
    }
    
  }
 
  getOngoingFlight() {
    return this.ongoingFlight;
  }
 
  setReturnFlight(returnFlight: any) {
    if(returnFlight.length>0){
      for(var i=0; i<returnFlight.length;i++){
        this.returnFlight.push(returnFlight[i]);
      }
    }
  }
 
  getReturnFlight() {
    return this.returnFlight;
  }

}