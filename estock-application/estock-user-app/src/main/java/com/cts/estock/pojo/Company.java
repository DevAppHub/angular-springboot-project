package com.cts.estock.pojo;

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
	
	@Column(name="company_ceo")
	@NotBlank(message = "Company CEO is mandatory")
	@JsonProperty("company_ceo")
	private String companyCEO;
	
	@Column(name="company_turnover")
	@JsonProperty("company_turnover")
	private int companyTurnover;
	
	@Column(name="company_website")
	@NotBlank(message="company website should not be blank")
	@JsonProperty("company_website")
	private String companyWebsite;
	
	@Column(name="stock_exchange")
	@JsonProperty("stock_exchange")
	private String stockExchange;

	public Company() {
	}

	public Company(Long id, @NotBlank(message = "Company code is mandatory and should be unique") String companyCode,
			@NotBlank(message = "Company Name is mandatory") String companyName,
			@NotBlank(message = "Company CEO is mandatory") String companyCEO,
			@NotBlank(message = "company turnover should be greater than 10Cr") int companyTurnover,
			@NotBlank(message = "company website should not be blank") String companyWebsite, String stockExchange) {
		super();
		this.id = id;
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.companyCEO = companyCEO;
		this.companyTurnover = companyTurnover;
		this.companyWebsite = companyWebsite;
		this.stockExchange=stockExchange;
	}
	
}
