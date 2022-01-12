package com.cts.estock.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cts.estock.pojo.Company;
import com.cts.estock.pojo.StockModal;
import com.cts.estock.repository.CompanyRepository;
import com.cts.estock.repository.StockRepository;
import com.cts.estock.util.InvalidRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EStockUserService {
	
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	StockRepository stockRepository;

	
	@Value("${kafka.topic.name}")
	public String topicName;
	
	@Value("${master.url}")
	public String masterUrl;
	
	ObjectMapper mapper=new ObjectMapper();
	
	@Autowired
	RestTemplate restTemplate;

	public final Logger logger=LoggerFactory.getLogger(this.getClass().getName());

	public Company addCompany(Company company) {
		logger.info("request for add company details{}", company);
//		publishing kafka request
		Company modal=companyRepository.findByCompanyCodeAndCompanyName(company.getCompanyCode(), company.getCompanyName());
		if(modal==null) {
			if(company.getCompanyTurnover()<10) {
				throw new InvalidRequestException("Company Turnover should be greater than 10 Cr");
			}
			try {
				kafkaTemplate.send(topicName, new ObjectMapper().writeValueAsString(company));
			} catch (JsonProcessingException e) {
				logger.error("error in exchanging message ",e);
			}
			return companyRepository.save(company);
		}else {
			throw new InvalidRequestException("Company Details Already Exist...");
		}
	}

	public Company fetchCompanyBasedOnCompanyCode(String companyCode) {
		return companyRepository.findByCompanyCode(companyCode);
	}

	public List<Company> fetchAllCompany() {
		return companyRepository.findAll();
	}

	public boolean deleteCompanyDetails(String companyCode) {
		boolean isDelete=false;
		logger.info("request for id {}",companyCode);
		 Company findById = companyRepository.findByCompanyCode(companyCode);;
		 if(findById!=null) {
			 companyRepository.delete(findById);
			 isDelete=true;
		 }else {
			 throw new InvalidRequestException("Comapny Code "+companyCode+" not found for delete details ") ;
		 }
		 return isDelete;
	}

	public StockModal addStock(StockModal stockModal) {
			List<Map<String,Object>> stockList = getStockList();
			List<Map<String,Object>> companyList = getCompanyList();
			stockList.forEach(s->{
				if(s.get("stock_code").equals(stockModal.getStockCode())) {
					stockModal.setStockName((String)s.get("stock_name"));
				}
			});
			companyList.forEach(c-> {
				if(c.get("company_id").equals(stockModal.getCompanyCode())) {
					stockModal.setCompanyName((String)c.get("company_name"));
				}
			});
			return stockRepository.save(stockModal);
	}

	public List<StockModal> getStockDetails(String companyCode, Date startDate, Date enddate) {
		return stockRepository.findByCompanyCodeAndCreatedDateAndValidDate(companyCode, startDate, enddate);
	}

	public List<StockModal> getAllStockDetails() {
		return stockRepository.findAll();
	}

	public Map<String, Object> getDailyStatus() {
		Map<String, Object> status=new HashMap<>();
		status.put("min", 100.00);
		status.put("avg", 4500.78);
		status.put("max", 5000);
		return status;
	}

	public List<Map<String, Object>> getStockList() {
		List<Map<String, Object>> value = new ArrayList<Map<String, Object>>();
		try {
			String currentUrl = masterUrl + "/stock";
			ResponseEntity<String> responseEntity = restTemplate.getForEntity(currentUrl, String.class);
			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				TypeReference<List<Map<String, Object>>> type = new TypeReference<List<Map<String, Object>>>() {
				};
				value = mapper.readValue(responseEntity.getBody(), type);
			}
		} catch (RestClientException | JsonProcessingException e) {
			logger.error("error in calling get discount api", e);
		}
		return value;
	}

	public List<Map<String, Object>> getCompanyList() {
		List<Map<String, Object>> value = new ArrayList<Map<String, Object>>();
		try {
			String currentUrl = masterUrl + "/company";
			ResponseEntity<String> responseEntity = restTemplate.getForEntity(currentUrl, String.class);
			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				TypeReference<List<Map<String, Object>>> type = new TypeReference<List<Map<String, Object>>>() {
				};
				value = mapper.readValue(responseEntity.getBody(), type);
			}
		} catch (RestClientException | JsonProcessingException e) {
			logger.error("error in calling get discount api", e);
		}
		return value;
	}

	public boolean deleteStockDetails(Long id) {
		boolean isDelete=false;
		logger.info("request for id {}",id);
		 Optional<StockModal> findById = stockRepository.findById(id);;
		 if(findById.isPresent()) {
			 stockRepository.delete( findById.get());
			 isDelete=true;
		 }else {
			 throw new InvalidRequestException("Stock Id "+id+" not found for delete Operation ") ;
		 }
		 return isDelete;
	}

	public List<StockModal> getStockDetailsBasedOnCompanyCode(String companyCode) {
		return stockRepository.findByCompanyCode(companyCode);
	}

}
