package com.cts.estock.controller;

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

import com.cts.estock.modal.Company;
import com.cts.estock.modal.StockExchange;
import com.cts.estock.service.MasterDataService;


@RestController
@RequestMapping()
public class CompanyRestController {
	
	@Autowired
	MasterDataService companyService;
	
	
	
	@GetMapping("/stock")
	public List<StockExchange> getStockExchangeList() {
		return companyService.getStockExchangeList();
	}

	@PostMapping("/company")
	public Company createCompany(@RequestBody Company company) {
		return companyService.createCompany(company);
	}
	
	@PutMapping("/company/{id}")
	public Company updateCompany(@PathVariable("id") Long id, @RequestBody Company company) {
		return companyService.updateCompany(id,company);
	}
	
	@GetMapping("/company")
	public List<Company> getCompany() {
		return companyService.getCompany();
	}
	
	@DeleteMapping("/company/{id}")
	public boolean deleteCompany(@PathVariable("id") Long id) {
		return companyService.deleteCompany(id);
	}
}

