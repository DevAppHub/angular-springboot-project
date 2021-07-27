package com.cts.flight.modal;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name="discount")
@Data
public class DiscountModal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="discount_number", unique = true)
	@JsonProperty("discount_number")
	@NotBlank(message = "Discount number is mandatory")
	private String discountNumber;
	
	@Column(name="discount_name")
	@JsonProperty("discount_name")
	@NotBlank(message = "Discount name is mandatory")
	private String discountName;
	
	@Column(name="price")
	@JsonProperty("discount_price")
//	@NotBlank(message = "discount price is mandatory")
	private double discountPrice;
	
	@Column(name="created_by")
	@JsonProperty("created_by")
	private String createdBy;
	
	@JsonIgnore
	@Column(name="created_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy",timezone = "IST")
	private Date createdDate;
	
	@JsonIgnore
	@Column(name="web_ref_id")
	private String webRefId;
	
	@Column(name="discount_validity")
	@JsonProperty("discount_validity")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone = "IST")
	private Date discountValidity;
	
	@PrePersist
	public void prePersistOperation() {
		this.createdDate=new Date();
		this.webRefId=UUID.randomUUID().toString().replaceAll("-", "");
	}

	public DiscountModal() {
	}
	
	public DiscountModal(Long id, @NotBlank(message = "Discount number is mandatory") String discountNumber,
			@NotBlank(message = "Discount name is mandatory") String discountName, double discountPrice, Date discountValidity) {
		super();
		this.id = id;
		this.discountNumber = discountNumber;
		this.discountName = discountName;
		this.discountPrice = discountPrice;
		this.discountValidity = discountValidity;
	}
	
	
}
