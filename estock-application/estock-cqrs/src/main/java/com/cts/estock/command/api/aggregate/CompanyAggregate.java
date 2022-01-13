package com.cts.estock.command.api.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.cts.estock.command.api.commands.CreateCompanyCommand;
import com.cts.estock.command.api.event.CompanyCreatedEvent;

@Aggregate
public class CompanyAggregate {

	@AggregateIdentifier
	private String companyId;
	private String companyCode;
	private String companyName;
	private String companyCEO;
	private int companyTurnover;
	private String companyWebsite;
	private String stockExchange;
	
	public CompanyAggregate() {
	}
	
	@CommandHandler
	public CompanyAggregate(CreateCompanyCommand createCompanyCommand) {
		CompanyCreatedEvent companyCreatedEvent=new CompanyCreatedEvent();
		BeanUtils.copyProperties(createCompanyCommand, companyCreatedEvent);
		AggregateLifecycle.apply(companyCreatedEvent);
	}
	
	@EventSourcingHandler
	public void on(CompanyCreatedEvent companyCreatedEvent) {
		this.companyId=companyCreatedEvent.getCompanyId();
		this.companyCEO=companyCreatedEvent.getCompanyCEO();
		this.companyCode=companyCreatedEvent.getCompanyCode();
		this.companyName=companyCreatedEvent.getCompanyName();
		this.companyTurnover=companyCreatedEvent.getCompanyTurnover();
		this.companyWebsite=companyCreatedEvent.getCompanyWebsite();
		this.stockExchange=companyCreatedEvent.getStockExchange();
	}
}
