package com.cts.estock.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.estock.pojo.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	Company findByCompanyCodeAndCompanyName(String companyCode, String companyName);

	Company findByCompanyCode(String companyCode);

	@Transactional
	Optional<Company> deleteByCompanyCode(String companyCode);

}
