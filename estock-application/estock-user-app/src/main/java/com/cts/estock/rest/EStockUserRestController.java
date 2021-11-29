package com.cts.estock.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.estock.pojo.Company;
import com.cts.estock.pojo.StockModal;
import com.cts.estock.service.EStockUserService;


@RestController
@RequestMapping
public class EStockUserRestController {

	@Autowired
	EStockUserService estockUserService;

	@PostMapping("/api/v1.0/market/company/register")
	public Company addCompany(@RequestBody Company company) {
		return estockUserService.addCompany(company);
	}

	@GetMapping("/api/v1.0/market/company/info/{companycode}")
	public Company fetchCompanyBasedOnCompanyCode(@PathVariable("companycode") String companyCode) {
		return estockUserService.fetchCompanyBasedOnCompanyCode(companyCode);
	}

	@GetMapping("/api/v1.0/market/company/getall")
	public List<Company> fetchAllCompany() {
		return estockUserService.fetchAllCompany();
	}

	@DeleteMapping("/api/v1.0/market/company/delete/{companycode}")
	public boolean deleteCompanyDetails(@PathVariable("companycode") String companyCode) {
		return estockUserService.deleteCompanyDetails(companyCode);
	}

	@PostMapping("/api/v1.0/market/stock/add/{companycode}")
	public StockModal addStock(@RequestBody StockModal stockModal) {
		return estockUserService.addStock(stockModal);
	}

	@GetMapping("/api/v1.0/market/stock/get/{companycode}/{startdate}/{enddate}")
	public List<StockModal> getStockDetails(@PathVariable("companycode") String companyCode,
			@PathVariable("startdate") Date startDate, @PathVariable("enddate") Date enddate) {
		return estockUserService.getStockDetails(companyCode, startDate, enddate);
	}
}
