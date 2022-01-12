package com.cts.estock.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Table(name = "stock_data")
@Data
public class StockModal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name="company_code")
	private String companyCode;

	@Column(name="company_name")
	private String companyName;

	@Column(name="stock_code")
	private String stockCode;

	@Column(name="stock_name")
	private String stockName;

	@Column(name="stock_price")
	private double stockPrice;

	@Column(name="created_date")
	private Date createdDate = new Date();

	@Column(name="valid_date")
	private Date validDate ;

}
