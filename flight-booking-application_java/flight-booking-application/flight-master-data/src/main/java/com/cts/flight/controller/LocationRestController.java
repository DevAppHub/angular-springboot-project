package com.cts.flight.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.flight.modal.Location;
import com.cts.flight.service.LocationService;

@RestController
@RequestMapping("/api/v1")
public class LocationRestController {

	@Autowired
	LocationService locationService;

	@GetMapping("/location")
	public ResponseEntity<List<Location>> getAllLocation() {
		List<Location> allLocation = locationService.getAllLocation();
		if (allLocation == null) {
			return new ResponseEntity<List<Location>>(Collections.EMPTY_LIST, HttpStatus.OK);
		}
		return new ResponseEntity<List<Location>>(allLocation, HttpStatus.OK);
	}
	
	@GetMapping("/location/{searchParam}")
	public ResponseEntity<List<Location>> getLocationDetailsBasedOnSearchParam(@PathVariable("searchParam")String searchParam) {
		List<Location> allLocation = locationService.getLocationBasedOnSearchType(searchParam);
		if (allLocation == null) {
			return new ResponseEntity<List<Location>>(Collections.EMPTY_LIST, HttpStatus.OK);
		}
		return new ResponseEntity<List<Location>>(allLocation, HttpStatus.OK);
	}
	
}
