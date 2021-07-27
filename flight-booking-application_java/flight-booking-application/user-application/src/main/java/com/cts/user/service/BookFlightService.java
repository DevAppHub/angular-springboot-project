package com.cts.user.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cts.admin.modal.BookFlight;
import com.cts.admin.modal.FlightSearchRequest;
import com.cts.admin.modal.ManageAirlines;
import com.cts.admin.modal.PassengerDetails;
import com.cts.admin.repository.BookFlightRepository;
import com.cts.admin.repository.ManageAirlinesRepository;
import com.cts.flight.pojo.Discount;
import com.cts.flight.pojo.Location;
import com.cts.flight.service.ExternalApiIntegrationService;
import com.cts.user.util.InvalidRequestException;

@Service
public class BookFlightService {

	public final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	ExternalApiIntegrationService apiIntegrationService;
	
	@Autowired
	ManageAirlinesRepository airlinesRepository;
	
	@Autowired
	BookFlightRepository bookFlightRepository;
	
	@Autowired
	EntityManager entityManager;

	public List<ManageAirlines> searchFlight(FlightSearchRequest flightSearchRequest) {
		logger.info("flight search request {} ", flightSearchRequest);
		List<ManageAirlines> airlines = airlinesRepository.findByFromPlaceAndToPlace(flightSearchRequest.getFromPlace(),
				flightSearchRequest.getToPlace());
		if (airlines == null) {
			return Collections.EMPTY_LIST;
		} else {
			return airlines;
		}
	}

	public BookFlight saveBookFlightDetails(BookFlight bookFlight) {
		logger.info("flight booking request for user id {} with payload {}", bookFlight.getEmailId(), bookFlight);
		return bookFlightRepository.save(bookFlight);
	}

	@Transactional
	public BookFlight updateTravellerDetails(Set<PassengerDetails> details, String emailId) {
		try {
			logger.info("passenger details {} ",details);
			List<BookFlight> findByEmailId = bookFlightRepository.findByEmailId(emailId);
			if(findByEmailId!=null && !findByEmailId.isEmpty()) {
				Optional<BookFlight> max = findByEmailId.stream().max(Comparator.comparing(BookFlight::getCreatedDate));
				if(max.isPresent()) {
					BookFlight existingData = max.get();
					existingData.setPassenger(details);
					BookFlight merge = entityManager.merge(existingData);
					return merge;
				}
				}else {
					throw new InvalidRequestException("No details found for update the record");
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public BookFlight applyDiscount(BookFlight bookFlight, String emailId) {
		List<BookFlight> findByEmailId = bookFlightRepository.findByEmailId(emailId);
		if(findByEmailId!=null && !findByEmailId.isEmpty()) {
			Optional<BookFlight> max = findByEmailId.stream().max(Comparator.comparing(BookFlight::getCreatedDate));
			if(max.isPresent()) {
				BookFlight existingData = max.get();
				existingData.setDiscountCode(bookFlight.getDiscountCode());
				Discount discount=apiIntegrationService.getDiscountBasedOnCode(bookFlight.getDiscountCode());
				if(discount!=null) {
					double price=bookFlight.getPrice()-(discount.getDiscountPrice()*existingData.getNoOfTraveller());
					existingData.setPrice(price);
				}
				BookFlight merge = entityManager.merge(existingData);
				return merge;
			}
		}
		
		return null;
	}

	@Cacheable("locationList")
	public List<Location> getLocationList() {
		return apiIntegrationService.getLocationList();
	}

	@Transactional
	public BookFlight doPayment(BookFlight bookFlight, String emailId) {
		List<BookFlight> findByEmailId = bookFlightRepository.findByEmailId(emailId);
		if(findByEmailId!=null && !findByEmailId.isEmpty()) {
			Optional<BookFlight> max = findByEmailId.stream().max(Comparator.comparing(BookFlight::getCreatedDate));
			if(max.isPresent()) {
				BookFlight existingData = max.get();
				existingData.setPaymentMethod(bookFlight.getPaymentMethod());
				existingData.setPnrNo(UUID.randomUUID().toString().replaceAll("-", ""));
				BookFlight merge = entityManager.merge(existingData);
				return merge;
			}
		}
		return null;
	}

	public List<BookFlight> getFlightBookingInformation(String emailId) {
		List<BookFlight> findByEmailId = bookFlightRepository.getByEmailId(emailId);
		if(!findByEmailId.isEmpty()) {
			List<BookFlight> collect=new ArrayList<BookFlight>();
			return findByEmailId;
		}
		return Collections.EMPTY_LIST;
	}

	public List<ManageAirlines> getAllFlight() {
		return airlinesRepository.findAll();
	}

	public List<Discount> getListOfDiscount() {
		return apiIntegrationService.getListOfDiscount();
	}

	public boolean deleteFlightBooking(Long id) {
		boolean isDelete=false;
		logger.info("flight delete request for id {}",id);
		 Optional<BookFlight> findById = bookFlightRepository.findById(id);
		 if(findById.isPresent()) {
			 BookFlight discountModal2 = findById.get();
			 bookFlightRepository.delete(discountModal2);
			 isDelete=true;
		 }else {
			 throw new InvalidRequestException("Flight id "+id+" not found for delete flight details ") ;
		 }
		 return isDelete;
	}

	public List<BookFlight> getHistory(String emailId) {
		return bookFlightRepository.findByEmailId(emailId);
	}

	public BookFlight getFlightBasedOnPnrNumber(String pnrNumber) {
		return bookFlightRepository.findByPnrNo(pnrNumber);
	}
}
