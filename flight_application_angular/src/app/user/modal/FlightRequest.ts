export class FlightRequest{
    constructor(public tripType: number, 
        public from_city: string,
        public to_city: string, 
        public depart_date: Date,
        public return_date: Date,
        public no_of_passenger: number
         ){

    }
}