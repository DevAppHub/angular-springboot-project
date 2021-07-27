package com.cts.admin.modal;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ScheduleSearchRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1015674932151974223L;

	@JsonProperty("flight_no")
	private String flightNumber;
	
	@JsonProperty("flight_name")
	private String flightName;
	
	@JsonProperty("from_place")
	private String fromPlace;
	
	@JsonProperty("to_place")
	private String toPlace;
}
