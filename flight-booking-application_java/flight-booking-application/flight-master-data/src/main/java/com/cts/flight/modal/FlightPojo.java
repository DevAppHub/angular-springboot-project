package com.cts.flight.modal;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FlightPojo {

	private Long id;
	
	@JsonProperty("flight_no")
	private String flightNumber;
	
	@JsonProperty("flight_name")
	private String flightName;
	
	@JsonProperty("model_no")
	private String modalNo;
	
	@JsonProperty("no_of_seats")
	private String noOfSeats;
	
	@JsonProperty("from_place")
	private String fromPlace;
	
	@JsonProperty("to_place")
	private String toPlace;
	
	@JsonProperty("manufactured_by")
	private String manufaturedBy;
	
	@JsonProperty("manufatured_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "IST")
	private Date manufacturedDate;
	
	@JsonProperty("price")
	private double price;
	
	@JsonProperty("departure_date_time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "IST")
	private Date departureDateTime;
}
