package com.cts.flight.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.flight.modal.FlightModal;
import com.cts.flight.service.FlightService;

@RestController
@RequestMapping("/api/v1")
public class FlightRestController {
	
	@Autowired
	FlightService flightService;
	
	@PostMapping("/flight")
	public FlightModal createFlight(@RequestBody FlightModal flightModal) {
		return flightService.createFlight(flightModal);
	}
	
	@PutMapping("/flight/{id}")
	public FlightModal updateFlight(@PathVariable("id") Long id, @RequestBody FlightModal flightModal) {
		return flightService.updateFlight(id,flightModal);
	}
	
	@GetMapping("/flight")
	public List<FlightModal> getFlight() {
		return flightService.getFlight();
	}
	
	@DeleteMapping("/flight/{id}")
	public boolean deleteFlight(@PathVariable("id") Long id) {
		return flightService.deleteFlight(id);
	}

}
