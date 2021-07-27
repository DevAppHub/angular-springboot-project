package com.cts.admin.modal;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class FlightSearchRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8425080456460317596L;

	@JsonProperty("from_place")
	private String fromPlace;
	
	@JsonProperty("to_place")
	private String toPlace;
	
	@JsonProperty("date_of_journey")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "IST")
	private Date dateOfJourney;
	
}
