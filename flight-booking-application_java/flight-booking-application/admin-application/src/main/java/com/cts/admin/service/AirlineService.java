package com.cts.admin.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.cts.admin.modal.AirlineSchedules;
import com.cts.admin.modal.BookFlight;
import com.cts.admin.modal.ManageAirlines;
import com.cts.admin.modal.ScheduleSearchRequest;
import com.cts.admin.repository.AirlineScheduleRepository;
import com.cts.admin.repository.BookFlightRepository;
import com.cts.admin.repository.ManageAirlinesRepository;
import com.cts.admin.util.InvalidRequestException;
import com.cts.flight.pojo.Discount;
import com.cts.flight.pojo.Location;
import com.cts.flight.service.ExternalApiIntegrationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AirlineService {
	
	public final Logger logger=LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	ManageAirlinesRepository airlinesRepository;
	
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	@Autowired
	AirlineScheduleRepository airlineScheduleRepository;
	
	@Autowired
	ExternalApiIntegrationService apiIntegrationService;
	
	@Autowired
	BookFlightRepository bookFlightRepository;
	
	@Value("${kafka.topic.name}")
	public String topicName;
	
	public List<ManageAirlines> getAllAirlinesDetails() {
		return airlinesRepository.findAll();
	}

	public ManageAirlines addNewAirlinesDetails(ManageAirlines airlines) {
		logger.info("add airline details{}", airlines);
		try {
			kafkaTemplate.send(topicName, new ObjectMapper().writeValueAsString(airlines));
		} catch (JsonProcessingException e) {
			logger.error("error in exchanging message ",e);
		}
		return airlinesRepository.save(airlines);
		
	}

	public ManageAirlines updateAirlinesDetails(Long id, ManageAirlines airlines) {
		Optional<ManageAirlines> findById = airlinesRepository.findById(id);
		if(findById.isPresent()) {
			ManageAirlines manageAirlines = findById.get();
			BeanUtils.copyProperties(airlines, manageAirlines, "id");
			return airlinesRepository.save(manageAirlines);
		}
		throw new InvalidRequestException("id"+id+"not found for update airline details");
	}

	public List<Discount> getAllDiscountList() {
		return apiIntegrationService.getListOfDiscount();
	}

	public Discount addNewDiscount(Discount discount) throws Exception {
		return apiIntegrationService.addDiscount(discount);
	}

	public Discount updateDiscountDetails(Long id, Discount discount)  throws Exception{
		return apiIntegrationService.updateDiscount(id, discount);
	}

	public boolean deleteDiscountDetails(Long id) throws Exception{
		return apiIntegrationService.deleteDiscountDetails(id);
	}

	public AirlineSchedules addAirlineSchedule(AirlineSchedules airlineSchedules) {
		logger.info("add airline schedule{}", airlineSchedules);
		return airlineScheduleRepository.save(airlineSchedules);
	}

	public AirlineSchedules updateAirlineSchedule(Long id, AirlineSchedules airlineSchedules) {
		Optional<AirlineSchedules> findById = airlineScheduleRepository.findById(id);
		if(findById.isPresent()) {
			AirlineSchedules manageAirlines = findById.get();
			BeanUtils.copyProperties(airlineSchedules, manageAirlines, "id");
			return airlineScheduleRepository.save(manageAirlines);
		}
		throw new InvalidRequestException("id"+id+"not found for update airline schedule");
	}

	public boolean deleteAirlineSchedule(Long id) {
		boolean isDelete=false;
		logger.info("flight update request for id {}",id);
		 Optional<AirlineSchedules> findById = airlineScheduleRepository.findById(id);
		 if(findById.isPresent()) {
			 AirlineSchedules airlineScheduleObj = findById.get();
			 airlineScheduleRepository.delete(airlineScheduleObj);
			 isDelete=true;
		 }else {
			 throw new InvalidRequestException("Airline Schedule id "+id+" not found for delete details ") ;
		 }
		 return isDelete;
	}

	public List<AirlineSchedules> getAllAirlineSchedule() {
		return airlineScheduleRepository.findAll();
	}

	@Cacheable("locationList")
	public List<Location> getLocationList() {
		return apiIntegrationService.getLocationList();
	}

	public List<AirlineSchedules> searchAirlineSchedule(ScheduleSearchRequest scheduleSearchRequest) {
		logger.info("schedule search request {}", scheduleSearchRequest);
		List<AirlineSchedules> list=airlineScheduleRepository.findByFlightNumberOrFlightNameOrFromPlaceOrToPlace(scheduleSearchRequest.getFlightNumber(), scheduleSearchRequest.getFlightName(), scheduleSearchRequest.getFromPlace(), scheduleSearchRequest.getToPlace());
		if(list==null) {
			return Collections.EMPTY_LIST;
		}
		logger.info("response {}", list);
		return list;
	}

	public List<BookFlight> getFlightReports(String flightNo) {
		List<BookFlight> flightDetails=bookFlightRepository.findByFlightNumber(flightNo);
		if(flightDetails==null) {
			return Collections.EMPTY_LIST;
		}
		return flightDetails;
	}

}
