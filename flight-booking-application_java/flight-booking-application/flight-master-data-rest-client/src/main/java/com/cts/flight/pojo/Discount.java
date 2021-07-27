package com.cts.flight.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Discount {

	private Long id;
	
	@JsonProperty("discount_number")
	private String discountNumber;
	
	@JsonProperty("discount_name")
	private String discountName;
	
	@JsonProperty("discount_price")
	private double discountPrice;
	
	@JsonProperty("created_by")
	private String createdBy;
	
	@JsonIgnore
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy",timezone = "IST")
	private Date createdDate;
	
	@JsonIgnore
	private String webRefId;
	
	@JsonProperty("discount_validity")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone = "IST")
	private Date discountValidity;
}
