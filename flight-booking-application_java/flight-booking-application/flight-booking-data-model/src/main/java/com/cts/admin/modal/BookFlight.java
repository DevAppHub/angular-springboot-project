package com.cts.admin.modal;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name="booked_flight")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookFlight implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5186703625073469388L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="email_id")
	@JsonProperty("email_id")
	private String emailId;
	
	@Column(name="mobile_no")
	@JsonProperty("mobile_number")
	private String phoneNumber;
	
	@Column(name="flight_no")
	@JsonProperty("flight_no")
	private String flightNumber;
	
	@Column(name="flight_name")
	@JsonProperty("flight_name")
	private String flightName;
	
	@Column(name="no_of_traveller")
	@JsonProperty("no_of_travelers")
	private Long noOfTraveller;
	
	@Column(name="discount_code")
	@JsonProperty("discount_code")
	private String discountCode;
	
	@Column(name="price")
	@JsonProperty("price")
	private double price;
	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name="flight_id", referencedColumnName = "id")
	private Set<PassengerDetails> passenger;
	
	@Column(name="date_of_journey")
	@JsonProperty("departure_date_time")
//	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd hh:mm")
	private Date dateOfJourney;
	
	@Column(name="from_place")
	@JsonProperty("from_place")
	private String fromPlace;
	
	@Column(name="to_place")
	@JsonProperty("to_place")
	private String toPlace;
	
	@JsonIgnore
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="pnr_no")
	@JsonProperty("pnr_no")
	private String pnrNo;
	
	@Column(name="payment_method")
	@JsonProperty("payment_method")
	private String paymentMethod;
	
	
	@PrePersist
	public void beforeSaving() {
		this.createdDate=new Date();
	}

	public BookFlight() {
	}

	public BookFlight(Long id, String emailId, String phoneNumber, String flightNumber, String flightName,
			Long noOfTraveller, String discountCode, double price, Set<PassengerDetails> passenger, Date dateOfJourney,
			String fromPlace, String toPlace, String pnrNo, String paymentMethod) {
		super();
		this.id = id;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.flightNumber = flightNumber;
		this.flightName = flightName;
		this.noOfTraveller = noOfTraveller;
		this.discountCode = discountCode;
		this.price = price;
		this.passenger = passenger;
		this.dateOfJourney = dateOfJourney;
		this.fromPlace = fromPlace;
		this.toPlace = toPlace;
		this.pnrNo = pnrNo;
		this.paymentMethod = paymentMethod;
	}
	
	
}
