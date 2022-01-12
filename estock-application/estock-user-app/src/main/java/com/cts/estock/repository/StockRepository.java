package com.cts.estock.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cts.estock.pojo.StockModal;

public interface StockRepository extends JpaRepository<StockModal, Long>{

	@Query(value = "SELECT s FROM StockModal s WHERE s.companyCode= :companyCode and  s.createdDate >= :startDate AND s.validDate <= :endDate", nativeQuery = true)
	List<StockModal> findByCompanyCodeAndCreatedDateAndValidDate(@Param("companyCode")String companyCode,@Param("startDate") Date startDate,@Param("endDate") Date enddate);

	List<StockModal> findByCompanyCode(String companyCode);

}
