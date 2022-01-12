package com.cts.estock.config;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.cts.estock.modal.Company;
import com.cts.estock.service.MasterDataService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MessageListener {
	
	@Autowired
	MasterDataService companyService;

	private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);

	public static final String GROUP_ID_STRING = "group-id-string-1";
	public static final String TOPIC_NAME = "estock_application";
	
	@KafkaListener(groupId = MessageListener.GROUP_ID_STRING, topics = MessageListener.TOPIC_NAME)
	public void receivedMessage(String message) throws JsonProcessingException {
//		logger.debug("Json message received using Kafka listener " + message);
		ObjectMapper mapper = new ObjectMapper();
		Map<String,String> req = mapper.readValue(message, Map.class);
		Company company=new Company();
		company.setCompanyCode(req.get("company_id"));
		company.setCompanyName(req.get("company_name"));
		companyService.createCompany(company);
	}
}
