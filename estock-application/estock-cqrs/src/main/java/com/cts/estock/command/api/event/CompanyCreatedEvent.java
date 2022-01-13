package com.cts.estock.command.api.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyCreatedEvent {

	private String companyId;
	private String companyCode;
	private String companyName;
	private String companyCEO;
	private int companyTurnover;
	private String companyWebsite;
	private String stockExchange;
}
