package com.cts.admin.modal;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Table(name = "airline_schedule")
@Entity
public class AirlineSchedules implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -2671371459095400732L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="flight_no")
	@JsonProperty("flight_no")
	private String flightNumber;
	
	@JsonProperty("flight_name")
	@Column(name="flight_name")
	private String flightName;
	
	@JsonProperty("departure_date_time")
	@Column(name="departure_date_time")
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private Date departureDateTime;
	
	@JsonProperty("from_place")
	@Column(name="from_place")
	private String fromPlace;
	
	@Column(name="to_place")
	@JsonProperty("to_place")
	private String toPlace;
	
	@Column(name="no_of_passenger")
	@JsonProperty("no_of_passenger")
	private Long noOfPassenger;
	
	@JsonIgnore
	private String webRefId;
	
	@JsonIgnore
	private Date createdDate;
	
	
	@PrePersist
	public void prePersitFunction() {
		this.createdDate=new Date();
		this.webRefId=UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public AirlineSchedules() {
	}


	public AirlineSchedules(Long id, String flightNumber, String flightName, Date departureDateTime, String fromPlace,
			String toPlace, Long noOfPassenger) {
		super();
		this.id = id;
		this.flightNumber = flightNumber;
		this.flightName = flightName;
		this.departureDateTime = departureDateTime;
		this.fromPlace = fromPlace;
		this.toPlace = toPlace;
		this.noOfPassenger = noOfPassenger;
	}
	
	
}
