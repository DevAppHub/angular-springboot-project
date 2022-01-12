package com.cts.estock.modal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name="stock")
public class StockExchange implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -1939863072633316391L;
	
	@Id
	private long id;

	@JsonProperty("stock_code")
	@Column(name="stock_code")
	private String stockCode;
	
	@JsonProperty("stock_name")
	@Column(name="stock_name")
	private String stockName;
	
	public StockExchange() {
	}

	public StockExchange(long id, String stockCode, String stockName) {
		super();
		this.id = id;
		this.stockCode = stockCode;
		this.stockName = stockName;
	}
	
	
}
