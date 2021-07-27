package com.cts.flight.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Flight {
	
	private Long id;
	
	@JsonProperty("flight_number")
	private String flightNumber;
	
	@JsonProperty("flight_name")
	private String flightName;
	
	@JsonProperty("logo")
	private String flightLogo;
	
	@JsonProperty("manufactured_date_time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "IST")
	private Date manufacturedDate;
	
	@JsonProperty("modal_no")
	private String modalNumber;
	
	@JsonProperty("no_of_seats")
	private String noOfSeats;
	
	@JsonProperty("available_days")
	private Set<String> flightAvailableOnDays=new HashSet<String>();
	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "IST")
	private Date createdDate;
	
	@JsonProperty("web_ref_id")
	private String webRefId;

}
