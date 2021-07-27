package com.cts.flight.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.flight.pojo.Discount;
import com.cts.flight.pojo.Flight;
import com.cts.flight.pojo.Location;
import com.cts.flight.service.ExternalApiIntegrationService;

@RestController
public class ExternalApiIntegrationController {
	
	@Autowired
	ExternalApiIntegrationService apiIntegrationService;
	
	public List<Flight> getListOfFlight(){
		return apiIntegrationService.getListOfFlight();
	}
	
	public Flight addFlight(@RequestBody Flight flight){
		return apiIntegrationService.addFlight(flight);
	}
	
	
	public List<Discount> getListOfDiscount(){
		return apiIntegrationService.getListOfDiscount();
	}
	
	public Discount addDiscount(@RequestBody Discount discount)  throws Exception{
		return apiIntegrationService.addDiscount(discount);
	}
	
	public List<Location> getLocationList(){
		return apiIntegrationService.getLocationList();
	}
	

}
