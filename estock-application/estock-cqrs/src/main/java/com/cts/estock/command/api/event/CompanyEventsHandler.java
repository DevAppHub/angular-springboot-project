package com.cts.estock.command.api.event;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.cts.estock.command.api.main.CompanyModal;
import com.cts.estock.command.api.main.CompanyModalRepository;

@Component
public class CompanyEventsHandler {
	
//	@Autowired
	private CompanyModalRepository companyRepo;
	
	public CompanyEventsHandler(CompanyModalRepository companyRepository) {
		this.companyRepo=companyRepository;
	}
	

	@EventHandler
	public void on(CompanyCreatedEvent event) {
		CompanyModal company=new CompanyModal();
		BeanUtils.copyProperties(event, company);
		companyRepo.save(company);
	}
}
