package com.cts.admin.modal;

import java.io.Serializable;
import java.time.LocalDate;
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

@Entity
@Table(name = "passenger_details")
@Data
public class PassengerDetails implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -5905797198062954861L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="name")
	@JsonProperty("name")
	private String name;
	
	@Column(name="date_of_birth")
	@JsonProperty("date_of_birth")
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	
	@Column(name="id_proof")
	@JsonProperty("id_proof")
	private String idProof;
	
	@Column(name="meal_preference")
	@JsonProperty("meal_preference")
	private String mealPreference;
	
	@Column(name="meal_type")
	@JsonProperty("meal_included")
	private String mealType;
	
}
