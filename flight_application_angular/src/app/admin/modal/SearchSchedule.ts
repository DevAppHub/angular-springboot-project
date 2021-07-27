
export class SearchSchedule {
    constructor(public id: number,
        public flight_name: string,
        public flight_no: string,
        public departure_date_time: Date,
        public no_of_passenger: number,
        public to_place: string,
        public from_place: string) { }
}