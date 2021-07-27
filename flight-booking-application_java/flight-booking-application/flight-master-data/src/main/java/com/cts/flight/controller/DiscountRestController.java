package com.cts.flight.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.flight.modal.DiscountModal;
import com.cts.flight.service.DiscountService;

@RequestMapping("/api/v1")
@RestController
public class DiscountRestController {

	@Autowired
	DiscountService discountService;
	
	@PostMapping("/discount")
	public DiscountModal createDiscount( @Valid @RequestBody DiscountModal discountModal) throws Exception{
		return discountService.createDiscount(discountModal);
	}
	
	@PutMapping("/discount/{id}")
	public DiscountModal updateDiscountOffer(@PathVariable("id") Long id,@Valid @RequestBody DiscountModal discountModal) {
		return discountService.updateDiscountOffer(id,discountModal);
	}
	
	@GetMapping("/discount")
	public List<DiscountModal> getAllDiscount() {
		return discountService.getAllDiscount();
	}
	
	@GetMapping("/discount/{discountCode}")
	public DiscountModal getDiscountByDiscountCode(@PathVariable("discountCode") String discountCode) throws Exception {
		return discountService.getDiscountByDiscountCode(discountCode);
	}
	
	
	@DeleteMapping("/discount/{id}")
	public boolean deleteDiscountOffer(@PathVariable("id") Long id) {
		return discountService.deleteDiscountOffer(id);
	}

}
