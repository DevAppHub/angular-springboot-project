package com.cts.flight.modal;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name="flight")
@Data
public class FlightModal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="flight_number", unique = true)
	@NotBlank(message = "Flight Number is mandatory and uni")
	@JsonProperty("flight_number")
	private String flightNumber;
	
	@Column(name="flight_name")
	@NotBlank(message = "Flight Name is mandatory")
	@JsonProperty("flight_name")
	private String flightName;
	
	@Lob
	@Column(name="logo")
	@JsonProperty("logo")
	private String flightLogo;
	
	@Column(name="manufactured_date")
	@JsonProperty("manufactured_date_time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "IST")
	private Date manufacturedDate;
	
	@Column(name="modal_no")
	@NotBlank(message = "Modal Number is mandatory")
	@JsonProperty("modal_no")
	private String modalNumber;
	
	@Column(name="no_of_seats")
	@NotBlank(message = "No Of Seats is mandatory")
	@JsonProperty("no_of_seats")
	private String noOfSeats;
	
	@Column(name="available_days")
	@JsonProperty("available_days")
	@ElementCollection
    @CollectionTable(name = "flight_available_on_days", joinColumns = @JoinColumn(name = "flight_id"))
	private Set<String> flightAvailableOnDays=new HashSet<String>();
	
	
	@JsonIgnore
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "IST")
	private Date createdDate;
	
	@JsonIgnore
	@JsonProperty("web_ref_id")
	
	
	private String webRefId;
	@PrePersist
	public void prePersistDetails() {
		this.createdDate=new Timestamp(System.currentTimeMillis());
		this.webRefId=UUID.randomUUID().toString().replaceAll("-", "");
	}
	public FlightModal() {
	}

	
	public FlightModal(Long id, @NotBlank(message = "Flight Number is mandatory and uni") String flightNumber,
			@NotBlank(message = "Flight Name is mandatory") String flightName, String flightLogo, Date manufacturedDate,
			@NotBlank(message = "Modal Number is mandatory") String modalNumber,
			@NotBlank(message = "No Of Seats is mandatory") String noOfSeats, Set<String> flightAvailableOnDays) {
		super();
		this.id = id;
		this.flightNumber = flightNumber;
		this.flightName = flightName;
		this.flightLogo = flightLogo;
		this.manufacturedDate = manufacturedDate;
		this.modalNumber = modalNumber;
		this.noOfSeats = noOfSeats;
		this.flightAvailableOnDays = flightAvailableOnDays;
	}
	
	

}
