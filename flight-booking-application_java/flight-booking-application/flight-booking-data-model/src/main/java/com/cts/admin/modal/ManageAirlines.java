package com.cts.admin.modal;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name="manage_airlines")
public class ManageAirlines implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8987543509511628382L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="flight_number")
	@JsonProperty("flight_no")
	private String flightNumber;
	
	@Column(name="flight_name")
	@JsonProperty("flight_name")
	private String flightName;
	
	@Column(name="modal_no")
	@JsonProperty("model_no")
	private String modalNo;
	
	@Column(name="no_of_seats")
	@JsonProperty("no_of_seats")
	private String noOfSeats;
	
	@Column(name="from_place")
	@JsonProperty("from_place")
	private String fromPlace;
	
	@Column(name="to_place")
	@JsonProperty("to_place")
	private String toPlace;
	
	@Column(name="manufacutred_by")
	@JsonProperty("manufactured_by")
	private String manufaturedBy;
	
	@Column(name="manufactured_date")
	@JsonProperty("manufatured_date")
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "IST")
	private Date manufacturedDate;
	
	@Column(name="price")
	@JsonProperty("price")
	private double price;
	
	@Column(name="departure_date_time")
	@JsonProperty("departure_date_time")
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "IST")
	private Date departureDateTime;

	public ManageAirlines() {
	}
	
	public ManageAirlines(Long id, String flightNumber, String flightName, String modalNo, String noOfSeats,
			String fromPlace, String toPlace, String manufaturedBy, Date manufacturedDate, double price,
			Date departureDateTime) {
		super();
		this.id = id;
		this.flightNumber = flightNumber;
		this.flightName = flightName;
		this.modalNo = modalNo;
		this.noOfSeats = noOfSeats;
		this.fromPlace = fromPlace;
		this.toPlace = toPlace;
		this.manufaturedBy = manufaturedBy;
		this.manufacturedDate = manufacturedDate;
		this.price = price;
		this.departureDateTime = departureDateTime;
	}

	

}

