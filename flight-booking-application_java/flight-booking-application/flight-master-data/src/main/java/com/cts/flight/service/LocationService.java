package com.cts.flight.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.flight.modal.Location;
import com.cts.flight.repository.LocationRepository;

@Service
public class LocationService {

	public final Logger logger=LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	LocationRepository locationRepository;
	
	public List<Location> getAllLocation() {
		logger.info("request to get all airport location");
		return locationRepository.findAll();
	}
	
	public List<Location> getLocationBasedOnSearchType(String searchParam){
		return locationRepository.getLocationBasedOnSearchType(searchParam);
	}

}
