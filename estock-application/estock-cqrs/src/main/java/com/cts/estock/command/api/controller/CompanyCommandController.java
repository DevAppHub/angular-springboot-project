package com.cts.estock.command.api.controller;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.estock.command.api.commands.CreateCompanyCommand;
import com.cts.estock.command.api.main.CompanyModal;

@RestController
@RequestMapping("/company")
public class CompanyCommandController {

	public  final Logger logger=LoggerFactory.getLogger(this.getClass().getName());
	
	private CommandGateway commandGateway;
	
	public CompanyCommandController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}



	@PostMapping
	public String addCompany(@RequestBody CompanyModal company) {
		CreateCompanyCommand createCompanyCommand=CreateCompanyCommand.builder()
				.companyId(UUID.randomUUID().toString())
				.companyCEO(company.getCompanyCEO())
				.companyCode(company.getCompanyCode())
				.companyName(company.getCompanyName())
				.companyTurnover(company.getCompanyTurnover())
				.companyWebsite(company.getCompanyWebsite())
				.stockExchange(company.getStockExchange())
				.build();
		String result = commandGateway.sendAndWait(createCompanyCommand);
		return result;
	}
}
