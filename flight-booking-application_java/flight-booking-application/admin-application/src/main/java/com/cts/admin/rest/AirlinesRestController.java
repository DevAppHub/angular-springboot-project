package com.cts.admin.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.admin.modal.AirlineSchedules;
import com.cts.admin.modal.BookFlight;
import com.cts.admin.modal.ManageAirlines;
import com.cts.admin.modal.ScheduleSearchRequest;
import com.cts.admin.service.AirlineService;
import com.cts.flight.pojo.Discount;
import com.cts.flight.pojo.Location;


@RestController
@RequestMapping("/api/v1")
public class AirlinesRestController {

	@Autowired
	AirlineService airlineService; 
	
	@GetMapping("/location")
	public List<Location> getListOfPlaces(){
		return airlineService.getLocationList();
	}
	
	@GetMapping("/flight")
	public List<ManageAirlines> getAllAirlinesDetails(){
		return 	airlineService.getAllAirlinesDetails();
	}
	
	@PostMapping("/flight")
	public ManageAirlines addNewAirlinesDetails(@RequestBody ManageAirlines airlines){
		return 	airlineService.addNewAirlinesDetails(airlines);
	}
	
	@PutMapping("/flight/{id}")
	public ManageAirlines updateAirlinesDetails(@PathVariable("id")Long id, @RequestBody ManageAirlines airlines){
		return 	airlineService.updateAirlinesDetails(id,airlines);
	}
	
	@GetMapping("/discount")
	public List<Discount> getAllDiscountList(){
		return 	airlineService.getAllDiscountList();
	}
	
	@PostMapping("/discount")
	public Discount addNewDiscount(@RequestBody Discount discount)  throws Exception{
		return 	airlineService.addNewDiscount(discount);
	}
	
	@PutMapping("/discount/{id}")
	public Discount updateDiscountDetails(@PathVariable("id")Long id, @RequestBody Discount discount)  throws Exception{
		return 	airlineService.updateDiscountDetails(id,discount);
	}
	
	@DeleteMapping("/discount/{id}")
	public boolean deleteDiscountDetails(@PathVariable("id")Long id) throws Exception{
		return 	airlineService.deleteDiscountDetails(id);
	}
	
	@GetMapping("/flight/schedule")
	public List<AirlineSchedules> getAllAirlineSchedule(){
		return airlineService.getAllAirlineSchedule();
	}
	
	@PostMapping("/flight/schedule")
	public AirlineSchedules addAirlineSchedule(@RequestBody AirlineSchedules airlineSchedules) {
		return airlineService.addAirlineSchedule(airlineSchedules);
	}
	
	@PutMapping("/flight/schedule/{id}")
	public AirlineSchedules updateAirlineSchedule(@PathVariable("id") Long id, @RequestBody AirlineSchedules airlineSchedules) {
		return 	airlineService.updateAirlineSchedule(id,airlineSchedules);
	}
	
	@DeleteMapping("/flight/schedule/{id}")
	public boolean deleteAirlineSchedule(@PathVariable("id") Long id) {
		return 	airlineService.deleteAirlineSchedule(id);
	}
	
	@PostMapping("/flight/schedule/search")
	public List<AirlineSchedules> searchAirlineSchedule(@RequestBody ScheduleSearchRequest scheduleSearchRequest) {
		return 	airlineService.searchAirlineSchedule(scheduleSearchRequest);
	}
	
	@GetMapping("/flight/{flight_no}")
	public ResponseEntity<?> getFlightReports(@PathVariable("flight_no") String flightNo){
		List<BookFlight> flightReports = airlineService.getFlightReports(flightNo);
		if(flightReports.isEmpty()) {
			return new ResponseEntity(flightReports,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity(flightReports, HttpStatus.OK);
	}
}
