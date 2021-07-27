package com.cts.flight.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cts.flight.pojo.Discount;
import com.cts.flight.pojo.Flight;
import com.cts.flight.pojo.Location;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ExternalApiIntegrationService {

	public final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Value("${master.url}")
	private String masterUrl;

	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	RestTemplate restTemplate;

	public List<Flight> getListOfFlight() {
		return null;
	}

	public List<Discount> getListOfDiscount() {
		List<Discount> value = new ArrayList<Discount>();
		try {
			String currentUrl = masterUrl + "/discount";
			ResponseEntity<String> responseEntity = restTemplate.getForEntity(currentUrl, String.class);
			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				TypeReference<List<Discount>> type = new TypeReference<List<Discount>>() {
				};
				value = mapper.readValue(responseEntity.getBody(), type);
			}
		} catch (RestClientException | JsonProcessingException e) {
			logger.error("error in calling get discount api", e);
		}
		return value;
	}

	public List<Location> getLocationList() {
		List<Location> value = new ArrayList<Location>();
		try {
			String currentUrl = masterUrl + "/location";
			ResponseEntity<String> responseEntity = restTemplate.getForEntity(currentUrl, String.class);
			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				TypeReference<List<Location>> type = new TypeReference<List<Location>>() {
				};
				value = mapper.readValue(responseEntity.getBody(), type);
			}
		} catch (RestClientException | JsonProcessingException e) {
			logger.error("error in calling get discount api", e);
		}
		return value;
	}

	public Flight addFlight(Flight flight) {
		// TODO Auto-generated method stub
		return null;
	}

	public Discount addDiscount(Discount discount) throws Exception {
		Discount value = new Discount();
		try {
			String currentUrl = masterUrl + "/discount";
			HttpEntity<Discount> request = new HttpEntity<Discount>(discount);
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(currentUrl, request, String.class);
			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				value = mapper.readValue(responseEntity.getBody(), Discount.class);
			}
		} catch (Exception e) {
			logger.error("error in calling get discount api", e);
			if (e instanceof HttpServerErrorException) {
				throw new RuntimeException(new String(((HttpServerErrorException) e).getResponseBodyAsString()));
			}
		}
		return value;
	}

	public Discount updateDiscount(Long id, Discount discount) throws Exception {
		Discount value = new Discount();
		try {
			String currentUrl = masterUrl + "/discount/" + id;
			HttpEntity<Discount> request = new HttpEntity<Discount>(discount);
			ResponseEntity<String> responseEntity = restTemplate.exchange(currentUrl, HttpMethod.PUT, request,
					String.class);
			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				value = mapper.readValue(responseEntity.getBody(), Discount.class);
			}
		} catch (Exception e) {
			if (e instanceof HttpServerErrorException) {
				throw new RuntimeException(new String(((HttpServerErrorException) e).getResponseBodyAsString()));
			}
			logger.error("error in calling get discount api", e);
		}
		return value;
	}

	public boolean deleteDiscountDetails(Long id) throws Exception {
		boolean delete = false;
		try {
			String currentUrl = masterUrl + "/discount/" + id;
			HttpHeaders headers = new HttpHeaders();
			HttpEntity request = new HttpEntity(headers);
			ResponseEntity<Boolean> responseEntity = restTemplate.exchange(currentUrl, HttpMethod.DELETE, request,
					Boolean.class);
			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				delete = responseEntity.getBody();
			}
		} catch (Exception e) {
			if (e instanceof HttpServerErrorException) {
				throw new RuntimeException(new String(((HttpServerErrorException) e).getResponseBodyAsString()));
			}
			logger.error("error in calling delete discount api", e);
		}
		return delete;
	}

	public Discount getDiscountBasedOnCode(String discountCode) {
		Discount value = new Discount();
		logger.info("discount code => {}", discountCode);
		try {
			String currentUrl = masterUrl + "/discount/" + discountCode;
			ResponseEntity<String> responseEntity = restTemplate.getForEntity(currentUrl, String.class);
			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				value = mapper.readValue(responseEntity.getBody(), Discount.class);
			}
		} catch (RestClientException | JsonProcessingException e) {
			logger.error("error in calling get discount api", e);
		}
		return value;
	}

}
