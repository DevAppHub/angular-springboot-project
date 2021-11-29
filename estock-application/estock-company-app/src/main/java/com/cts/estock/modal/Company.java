package com.cts.estock.modal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name="company")
@Data
public class Company implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4239325335701916827L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="company_code", unique = true)
	@NotBlank(message = "Company code is mandatory and should be unique")
	@JsonProperty("company_id")
	private String companyCode;
	
	@Column(name="company_name")
	@NotBlank(message = "Company Name is mandatory")
	@JsonProperty("company_name")
	private String companyName;
	
	
}
