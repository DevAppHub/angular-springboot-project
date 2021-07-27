package com.cts.flight.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.flight.modal.FlightModal;
import com.cts.flight.repository.FlightModalRepository;
import com.cts.flight.util.InvalidRequestException;

@Service
public class FlightService {
	
	public final Logger logger=LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	FlightModalRepository flightModalRepository;

	public FlightModal createFlight(FlightModal flightModal) {
		logger.info("flight request {} ", flightModal);
		FlightModal modal=flightModalRepository.findByFlightNumberAndFlightName(flightModal.getFlightNumber(), flightModal.getFlightName());
		if(modal==null) {
		return flightModalRepository.save(flightModal);
		}
		throw new InvalidRequestException("Flight Details Already Exist");
	}

	public FlightModal updateFlight(Long id, FlightModal flightModal) {
		logger.info("flight update request for id {}",id);
		 Optional<FlightModal> findById = flightModalRepository.findById(id);
		 if(findById.isPresent()) {
			 FlightModal flightModal2 = findById.get();
			 BeanUtils.copyProperties(flightModal, flightModal2, "id");
			 return flightModalRepository.save(flightModal2);
		 }else {
			 throw new InvalidRequestException("Flight id "+id+" not found for update flight details ") ;
		 }
	}

	public List<FlightModal> getFlight() {
		return flightModalRepository.findAll();
	}

	public boolean deleteFlight(Long id) {
		boolean isDelete=false;
		logger.info("flight update request for id {}",id);
		 Optional<FlightModal> findById = flightModalRepository.findById(id);
		 if(findById.isPresent()) {
			 FlightModal flightModal2 = findById.get();
			 flightModalRepository.delete(flightModal2);
			 isDelete=true;
		 }else {
			 throw new InvalidRequestException("Flight id "+id+" not found for delete flight details ") ;
		 }
		 return isDelete;
	}

}
