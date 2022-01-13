package com.cts.estock.command.api.main;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class CompanyModal {

	@Id
	private String companyId;

	private String companyCode;
	private String companyName;
	private String companyCEO;
	private int companyTurnover;
	private String companyWebsite;
	private String stockExchange;
}
