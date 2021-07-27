package com.cts.user.rest;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.admin.modal.BookFlight;
import com.cts.admin.modal.FlightSearchRequest;
import com.cts.admin.modal.ManageAirlines;
import com.cts.admin.modal.PassengerDetails;
import com.cts.flight.pojo.Discount;
import com.cts.flight.pojo.Location;
import com.cts.user.service.BookFlightService;

@RestController
@CrossOrigin()
@RequestMapping("/api/v1")
public class BookFlightRestController {

	public final Logger logger=LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	BookFlightService bookFlightService;
	
	
	@GetMapping("/location")
	public List<Location> getListOfPlaces(){
		return bookFlightService.getLocationList();
	}
	
	@GetMapping("/discount")
	public List<Discount> getListOfDiscount(){
		return bookFlightService.getListOfDiscount();
	}
	
	@PostMapping("/flight/search")
	public List<ManageAirlines> searchFlight(@RequestBody FlightSearchRequest flightSearchRequest){
		return bookFlightService.searchFlight(flightSearchRequest);
	}
	
	@GetMapping("/flight/search")
	public List<ManageAirlines> searchFlight(){
		return bookFlightService.getAllFlight();
	}
	
	@PostMapping("/flight/book")
	public BookFlight bookFlight(@RequestBody BookFlight bookFlight) {
		return bookFlightService.saveBookFlightDetails(bookFlight);
	}
	
	@PutMapping("/add/passenger/{email_id}")
	public BookFlight updateTravellerDetails(@RequestBody Set<PassengerDetails> details, @PathVariable("email_id") String emailId) {
		return bookFlightService.updateTravellerDetails(details, emailId);
	}
	
	@PutMapping("/discount/{email_id}")
	public BookFlight applyDiscount(@RequestBody BookFlight bookFlight, @PathVariable("email_id") String emailId) {
		return bookFlightService.applyDiscount(bookFlight, emailId);
	}
	
	@PutMapping("/payment/{email_id}")
	public BookFlight doPayment(@RequestBody BookFlight bookFlight, @PathVariable("email_id") String emailId) {
		return bookFlightService.doPayment(bookFlight, emailId);
	}
	
	@GetMapping("/flight/{email_id}")
	public List<BookFlight> getflightBookingInformation(@PathVariable("email_id") String emailId){
		return bookFlightService.getFlightBookingInformation(emailId);
	}
	
	@GetMapping("/flight/pnr/{pnr}")
	public BookFlight getFlightBasedOnPnrNumber(@PathVariable("pnr") String pnrNumber){
		return bookFlightService.getFlightBasedOnPnrNumber(pnrNumber);
	}

	@GetMapping("/history/{email_id}")
	public List<BookFlight> getHistory(@PathVariable("email_id") String emailId){
		return bookFlightService.getHistory(emailId);
	}
	
	@DeleteMapping("/flight/{id}")
	public boolean deleteFlightBooking(@PathVariable("id") Long id){
		return bookFlightService.deleteFlightBooking(id);
	}
	
}
