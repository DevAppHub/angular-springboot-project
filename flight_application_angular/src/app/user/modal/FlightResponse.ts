
export class FlightResponse{
    constructor(public id: number, public flight_number: string,
        public flight_name: string, public pnr_no: string,
        public departure_date_time: Date ,
        public no_of_travelers: number){}
}