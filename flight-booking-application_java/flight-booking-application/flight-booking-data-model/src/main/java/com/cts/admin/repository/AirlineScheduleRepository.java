package com.cts.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.admin.modal.AirlineSchedules;

public interface AirlineScheduleRepository extends JpaRepository<AirlineSchedules, Long> {

	
	List<AirlineSchedules> findByFlightNumberOrFlightNameOrFromPlaceOrToPlace(String flightNumber, String flightName,
			String fromPlace, String toPlace);

}
