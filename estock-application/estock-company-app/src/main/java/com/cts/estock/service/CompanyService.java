package com.cts.estock.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.estock.modal.Company;
import com.cts.estock.repository.CompanyRepository;
import com.cts.estock.util.InvalidRequestException;

@Service
public class CompanyService {
	
	public final Logger logger=LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	CompanyRepository companyRepository;

	public Company createCompany(Company company) {
		logger.info("Company request {} ", company);
		Company modal=companyRepository.findByCompanyCodeAndCompanyName(company.getCompanyCode(), company.getCompanyName());
		if(modal==null) {
		return companyRepository.saveAndFlush(company);
		}
		throw new InvalidRequestException("Company Details Already Exist...");
	}

	public Company updateCompany(Long id, Company company) {
		logger.info("company details update request for id {}",id);
		 Optional<Company> findById = companyRepository.findById(id);
		 if(findById.isPresent()) {
			 Company company2 = findById.get();
			 BeanUtils.copyProperties(company, company2, "id");
			 return companyRepository.save(company2);
		 }else {
			 throw new InvalidRequestException("company id "+id+" not found for update company details ") ;
		 }
	}

	public List<Company> getCompany() {
		return companyRepository.findAll();
	}

	public boolean deleteCompany(Long id) {
		boolean isDelete=false;
		logger.info("flight update request for id {}",id);
		 Optional<Company> findById = companyRepository.findById(id);
		 if(findById.isPresent()) {
			 Company company2 = findById.get();
			 companyRepository.delete(company2);
			 isDelete=true;
		 }else {
			 throw new InvalidRequestException("Company id "+id+" not found for delete company details ") ;
		 }
		 return isDelete;
	}

}
