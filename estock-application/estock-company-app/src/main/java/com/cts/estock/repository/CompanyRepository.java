package com.cts.estock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.estock.modal.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	Company findByCompanyCodeAndCompanyName(String companyCode, String companyName);

}
