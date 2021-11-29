package com.cts.estock.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.cts.estock.pojo.Company;
import com.cts.estock.pojo.StockModal;
import com.cts.estock.repository.CompanyRepository;
import com.cts.estock.util.InvalidRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EStockUserService {
	
//	@Autowired
//	private KafkaTemplate<String, Object> kafkaTemplate;
	
	@Autowired
	CompanyRepository companyRepository;

	
//	@Value("${kafka.topic.name}")
//	public String topicName;
//	
	public final Logger logger=LoggerFactory.getLogger(this.getClass().getName());

	public Company addCompany(Company company) {
		logger.info("request for add company details{}", company);
		//publishing kafka request
//		try {
//			kafkaTemplate.send(topicName, new ObjectMapper().writeValueAsString(company));
//		} catch (JsonProcessingException e) {
//			logger.error("error in exchanging message ",e);
//		}
		Company modal=companyRepository.findByCompanyCodeAndCompanyName(company.getCompanyCode(), company.getCompanyName());
		if(modal==null) {
			if(company.getCompanyTurnover()<10) {
				throw new InvalidRequestException("Company Turnover should be greater than 10 Cr");
			}
			return companyRepository.save(company);
		}else {
			throw new InvalidRequestException("Company Details Already Exist...");
		}
	}

	public Company fetchCompanyBasedOnCompanyCode(String companyCode) {
		return null;
	}

	public List<Company> fetchAllCompany() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteCompanyDetails(String companyCode) {
		// TODO Auto-generated method stub
		return false;
	}

	public StockModal addStock(StockModal stockModal) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<StockModal> getStockDetails(String companyCode, Date startDate, Date enddate) {
		// TODO Auto-generated method stub
		return null;
	}

}
