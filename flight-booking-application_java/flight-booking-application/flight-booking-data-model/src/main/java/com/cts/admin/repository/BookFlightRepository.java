package com.cts.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cts.admin.modal.BookFlight;

public interface BookFlightRepository extends JpaRepository<BookFlight, Long>{

	@Query("select e from BookFlight e where e.emailId=:email")
	List<BookFlight> findByEmailId(@Param("email")String emailId);
	
	@Query("SELECT e FROM BookFlight e WHERE e.dateOfJourney > CURRENT_DATE and e.emailId =:email")
	List<BookFlight> getByEmailId(@Param("email")String emailId);

	BookFlight findByPnrNo(String pnrNumber);

	List<BookFlight> findByFlightNumber(String flightNo);
}

